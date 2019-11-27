<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Book Shop</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <script src="http://code.jquery.com/jquery-1.11.0.min.js">
        integrity = "sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin = "anonymous" ></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
            integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
            crossorigin="anonymous"></script>
    <script type="text/javascript" src="/postrequest.js"></script>
    <style>
        #addingToCartSuccess {
            position: fixed;
            right: 0;
            top: 50px;
            margin-top: 10px;
            margin-right: 10px;
            display: none;
            z-index: 15;
            text-align: center;
        }

        .card-columns {
        @include media-breakpoint-only(lg) {
            column-count: 4;
        }
        @include media-breakpoint-only(xl) {
            column-count: 5;
        }
        }
    </style>
    <style type="text/scss">
        .card-columns {
        @include media-breakpoint-only(lg) {
            column-count: 4;
        }
        @include media-breakpoint-only(xl) {
            column-count: 5;
        }
        }
    </style>
</head>

<body>

<#include "parts/security.ftl">
<#include "parts/navbar.ftl">

<div class="container mt-3 ml-5">

    <#global sum=0>
    <#global currentId=0>
    <#if isAdmin>

        <div>
            <form method="post" enctype="multipart/form-data">
                <input type="number" step="0.01" name="price" placeholder="стоимость">
                <input type="text" name="titleRu" placeholder="название ру">
                <input type="text" name="titleEn" placeholder="название en">
                <input type="text" name="author_surname" placeholder="фамилия автора">
                <input type="text" name="author_name" placeholder="имя автора">
                <input type="file" name="book_image">
                <button type="submit">Добавить</button>
            </form>
        </div>
    </#if>
    <div>Список книг</div>
    <form method="get" action="/">
        <input type="text" name="filter" value="${filter!}">
        <button type="submit">Найти</button>
    </form>
</div>
<div class="ml-5 mr-5">
    <form id="basketAdd">
        <div class="card-columns">
            <#list books as book>
                <div class="card my-3">
                    <#if .lang=="en">
                        <b>${book.titleEn}</b>
                    <#elseif .lang=="ru">
                        <b>${book.titleRu}</b>
                    </#if>
                    <#if book.image??>
                        <br>
                        <img src="/img/book/${book.image.bookImage}" <#--class="card-img-top"--><#--class="img-thumbnail"-->>

                    </#if>
                    <div class="card-footer text-muted text-right ">
                        ${book.author.surname}
                        <b>${book.price}</b>
                        <#if name!="unknow">
                            <div class="ml-1">
                                <button type="submit" class="btn btn-primary" onclick=editCurrentId(${book.id})>add to
                                    basket
                                </button>
                            </div>
                        </#if>
                    </div>
                </div>
            </#list>
        </div>
        <input type="hidden" id="bookId" value="${currentId}">
    </form>
</div>
<div id="addingToCartSuccess" class="alert alert-success col-lg-2 col-md-3 col-sm-3 col-xs-4"
     role="alert">
    <strong>Success</strong> Book added to cart

    <script>
        function editCurrentId(id) {
            document.getElementById("bookId").value = id;
        }
    </script>
</body>
</html>
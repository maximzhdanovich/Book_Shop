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

        #addingToCartError {
            position: fixed;
            right: 0;
            top: 50px;
            margin-top: 10px;
            margin-right: 10px;
            display: none;
            z-index: 15;
            text-align: center;
        }
    </style>
    <style>
        .leftimg {
            float: left; /* Выравнивание по левому краю */
            margin: 7px 7px 7px 7px; /* Отступы вокруг картинки */
        }

        .leftText {
            float: left;
        }

    </style>
    <style>
        .cope_text {
            overflow: hidden;
            line-height: 20px;
        }

        .line-clamp {
            display: -webkit-box;
            -webkit-line-clamp: 3;
            -webkit-box-orient: vertical;
        }
    </style>

</head>
<body>
<#include "parts/security.ftl">
<#include "parts/navbar.ftl">
<#include "locale/locale.ftl">

<#--<#assign spring=JspTaglibs["http://www.springframework.org/tags"]>-->

<div class="container mt-3">

    <#assign sum=0>
    <#assign currentId=0>
    <h5 align="center"> <#if categoryPage??>
        ${book_page_category}
        <#--        <@spring.message code="book.page.category"/>-->
        <#if .lang=="en">
            ${category.titleEn}
        <#elseif .lang=="ru">
            ${category.titleRu}
        </#if>
        <#elseif authorPage??>
            ${book_page_author}
        <#--        <@spring.message code="book.page.author"/>-->
            ${author.name} ${author.surname}
        <#else >
        <div>${book_list}</div>
    </h5>
    <#if isAdmin>
        <div>
            <form method="post" action="/book/admin/create" enctype="multipart/form-data">
                <input type="number" step="0.01" name="price" placeholder=${book_price}>
                <input type="text" name="titleRu" placeholder=${book_title_ru}>
                <input type="text" name="titleEn" placeholder=${book_title_en}>
                <input type="text" name="authorSurname" placeholder=${author_surname}>
                <input type="text" name="authorName" placeholder=${author_surname}>
                <textarea class="mt-1" maxlength="1000" rows="10" cols="90" name="description"></textarea>
                <input type="file" name="image">
                <button type="submit">${book_add}</button>
            </form>
        </div>
    </#if>
    </#if>
</div>
<div class="ml-5 mr-5">

    <form id="basketAdd">
        <div class="card-columns">
            <#list books as book>

                <div class="card my-3">


                    <#if book.image??>
                    <p><img src="/img/book/${book.image.bookImage}" class="leftimg" width="96" height="125">
                        <#else>
                    <p><img src="/img/bookNot/bookImageNotFound.jpg" class="leftimg" width="96" height="125">
                        </#if>
                        ${book_author}: ${book.author.name} ${book.author.surname}
                        <br>
                        ${book_title}
                        <#if .lang=="en">
                            ${book.titleEn}
                        <#elseif .lang=="ru">
                            ${book.titleRu}
                        </#if>
                    <div class="cope_text line-clamp">
                        <#if book.description??>
                            ${book.description}
                            <#if book.description?length<55>
                                <br>
                                <br>
                                <br>
                            <#elseif  book.description?length<110>
                                <br>
                                <br>
                            </#if>
                        </#if>
                    </div>
                    </p>
                    <div class="card-footer text-muted text-right">
                        <a href="/book/${book.id}"
                           class="btn btn-primary ml-2 leftText">${book_view}</a>
                        <#if isAdmin>
                            <a href="/book/admin/${book.id}"
                               class="btn btn-primary ml-2 leftText">${book_edit}</a>
                        </#if>

                        <b class="mr-2">${book_price}: ${book.price} Br</b>


                        <button type="submit" class="btn btn-primary" onclick=editCurrentId(${book.id})
                                <#if name="unknown">disabled="disabled"</#if>>
                            ${book_basket_add}
                        </button>


                    </div>
                </div>
            </#list>
        </div>
        <input type="hidden" id="bookId" value="${currentId}">
    </form>
</div>
<div id="addingToCartSuccess" class="alert alert-success col-lg-2 col-md-3 col-sm-3 col-xs-4"
     role="alert">
    <strong>Success</strong> ${book_basket_add_alert}</div>

<script>
    function editCurrentId(id) {
        document.getElementById("bookId").value = id;
    }
</script>
</body>
</html>
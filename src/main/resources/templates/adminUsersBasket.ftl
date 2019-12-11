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
    <script type="text/javascript" src="/approvedBook.js"></script>
    <style>
        #AddOnProcessing {
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
</head>
<body>
<#include "parts/navbar.ftl">
<#assign price = 0>
<#global bookId=0>
<#assign processingPrice = 0>
<div class="container center mt-5">
    Book in Cart
    <form id="approvedBook">
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>Цена</th>
                <th>Название</th>
                <th>Author</th>
            </thead>
            <tbody>
            <#list books as book>
                <tr>
                    <td>#{book.price}</td>
                    <td><#if .lang=="en">
                            ${book.titleEn}
                        <#elseif .lang=="ru">
                            ${book.titleRu}
                        </#if></td>
                    <td>${book.author.surname} ${book.author.name}</td>

                </tr>
                <#assign price += book.price>
            </#list>
            </tbody>
        </table>
        <br>
        <a>Total price ${price}</a>
        <br>
        <br>
        Book in processing
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>Цена</th>
                <th>Название</th>
                <th>Author</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <#list booksinprocessing as bookinprocessing>
                <tr>
                    <td>#{bookinprocessing.price}</td>
                    <td><#if .lang=="en">
                            ${bookinprocessing.titleEn}
                        <#elseif .lang=="ru">
                            ${bookinprocessing.titleRu}
                        </#if></td>
                    <td>${bookinprocessing.author.surname} ${bookinprocessing.author.name}</td>
                    <td>
                        <button type="submit" id="${bookinprocessing.id}" class="btn btn-primary"
                                onclick=editCurrentId(${bookinprocessing.id})>
                            approved
                        </button>
                    </td>
                </tr>
                <#assign processingPrice += bookinprocessing.price>
            </#list>
            </tbody>
        </table>
    </form>

    <br>
    <a>Total price ${processingPrice}</a>

    <input type="hidden" id="userId" value="${user.id}">
    <input type="hidden" id="bookId" value="${bookId}">
    <div id="AddOnProcessing" class="alert alert-success col-lg-2 col-md-3 col-sm-3 col-xs-4"
         role="alert">
        <strong>Success</strong> Book on processing
    </div>
    <script>
        function editCurrentId(id) {
            document.getElementById("bookId").value = id;
        }
    </script>
</body>
</html>

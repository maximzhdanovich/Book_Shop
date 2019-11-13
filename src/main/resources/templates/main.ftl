<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">
<@c.page>
<#global sum=0>

<div>
    <form method="post" enctype="multipart/form-data">
        <input type="number" step="0.01" name="price" placeholder="стоимость">
        <input type="text" name="titleRu" placeholder="название ру">
        <input type="text" name="titleEn" placeholder="название en">
        <input type="text" name="author_surname" placeholder="фамилия автора">
        <input type="text" name="author_name" placeholder="имя автора">
        <input type="file" name="image">

        <button type="submit">Добавить</button>
    </form>
</div>
<div>Список книг</div>
<form method="get" action="/">
    <input type="text" name="filter" value="${filter!}">
    <button type="submit">Найти</button>
</form>
<form method="post" action="/addtobasket">
    <#list books as book>
        <div>
            <b>${book.price}</b>
            <b>${book.titleEn}</b>
            <b>${book.titleRu}</b>
            <b>${book.author.surname}</b>
            <#if name!="unknow">
            <input type="checkbox" name="${book}" id="${book.id}" onclick="myFunction(${book.id},${book.price})">
            </#if>
        </div>
    </#list>
    <#if name!="unknow">
    <button type="submit">add to basket</button>
    </#if>
</form>
<a>Cтоимость</a>
<input type="number" id="sum" value="${sum}" readonly="readonly">

<script>
    function myFunction(bookId,price) {
        var checkBox = document.getElementById(bookId);
        var truePrice = price;
        if (checkBox.checked === true){
            document.getElementById("sum").value-=-truePrice;
        } else {
            document.getElementById("sum").value-=truePrice;
        }
    }
</script>
</@c.page>
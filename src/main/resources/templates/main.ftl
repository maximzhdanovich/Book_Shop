<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Book_Shop</title>
</head>
<body>
${currentUser.username}
${currentUser.role.title}
<#global sum=0>
<div>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="{{_csrf.token}}"/>
        <input type="submit" value="Sign Out"/>
    </form>
</div>
<a href="/book">book list</a>
<div>
    <form method="post">
        <input type="number" step="0.01" name="price" placeholder="стоимость">
        <input type="text" name="titleRu" placeholder="название ру">
        <input type="text" name="titleEn" placeholder="название en">

        <input type="text" name="author_surname" placeholder="фамилия автора">
        <input type="text" name="author_name" placeholder="имя автора">
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
            <input type="checkbox" name="${book}" id="${book.id}" onclick="myFunction(${book.id},${book.price})">
        </div>
    </#list>

    <button type="submit">add to basket</button>
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

</body>
</html>
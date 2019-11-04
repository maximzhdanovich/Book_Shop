<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Book_Shop</title>
</head>
<body>
<div>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="{{_csrf.token}}" />
        <input type="submit" value="Sign Out"/>
    </form>
</div>
<a href="/book">book list</a>
<div>
    <form method="post">
        <input type="number" name="price" placeholder="стоимость">
        <input type="text" name="titleRu" placeholder="название ру">
        <input type="text" name="titleEn" placeholder="название en">

        <input type="text" name="author_surname" placeholder="фамилия автора">
        <input type="text" name="author_name" placeholder="имя автора">
        <button type="submit">Добавить</button>
    </form>
</div>
<div>Список книг</div>
<form method="get" action="/">
    <input type="text" name="filter" value="${filter!}" >
    <button type="submit">Найти</button>
</form>
<#list books as book>
    <div>
        <b>${book.price}</b>
        <b>${book.titleEn}</b>
        <b>${book.titleRu}</b>
        <b>${book.author.surname}</b>
    </div>
</#list>
</body>
</html>
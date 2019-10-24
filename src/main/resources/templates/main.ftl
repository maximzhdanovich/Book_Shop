<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Book_Shop</title>
</head>
<div>
    <form method="post">
        <input type="number" name="price" placeholder="стоимость">
        <input type="text" name="titleRu" placeholder="название ру">
        <input type="text" name="titleEn" placeholder="название en">
        <input type="text">
        <#--           <input type="hidden" name="_csrf" value="${_csrf.token}" />-->
        <button type="submit">Добавить</button>
    </form>
</div>
<div>Список книг</div>
<form method="get" action="/main">
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
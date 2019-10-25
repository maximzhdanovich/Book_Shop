<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Book_Edit</title>
</head>
<body>
Book Edit
<form action="/book" method="post">
    <input type="text" name="titleRu" value="${book.titleRu}">
    <#list categories as category>
        <div>
            <label><input type="checkbox" name="{category}"
                        ${book.categories?seq_contains(category)?string("checked", "")}>${category.titleRu}</label>
        </div>

    </#list>
    <button type="submit">Save</button>
</body>
</html>
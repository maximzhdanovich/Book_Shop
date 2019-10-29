<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Author_Edit</title>
</head>
<body>
Author Edit
<form action="/author/{authorId}" method="post">
    <input type="text" name="surname" value="${author.surname}">
    <input type="text" name="name" value="${author.name}">
    <input type="hidden" value="${author.id}" name="authorId">
    <button type="submit">Save</button>
</body>
</html>
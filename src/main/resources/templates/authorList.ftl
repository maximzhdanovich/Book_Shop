<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Author_List</title>
</head>
<body>
List of Authors<br>
<a href="/">main</a>
<div>
    <form method="post">
        <input type="text" name="surname" placeholder="фамилия автора">
        <input type="text" name="name" placeholder="имя автора">
        <button type="submit">Добавить</button>
    </form>
</div>
<table>
    <tbody>
    <#list authors as author>
        <tr>
            <td>${author.surname} ${author.name}</td>
            <td><a href="/author/${author.id}">edit</a></td>
        </tr>
    </#list>
    </tbody>
</table>
</body>
</html>
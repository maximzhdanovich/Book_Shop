<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Book_List</title>
</head>
<body>
List of Books<br>
<a href="/">main</a>
<table>
    <thead>
    <tr>
        <th>UserName</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <#list users as user>
        <tr>
            <td>${user.username}</td>
            <td><a href="/user/${user.id}">edit</a></td>
        </tr>
    </#list>
    </tbody>
</table>
</body>
</html>
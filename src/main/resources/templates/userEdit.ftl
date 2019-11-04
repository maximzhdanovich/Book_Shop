<#--<!DOCTYPE html>-->
<#--<html lang="en">-->
<#--<head>-->
<#--    <meta charset="UTF-8">-->
<#--    <title>User_Edit</title>-->
<#--</head>-->
<#--<body>-->
<#--User Edit-->
<#--<form action="/user" method="post">-->
<#--    <input type="text" name="username" value="${user.username}">-->
<#--    <input type="text" name="email" value="${user.email}">-->
<#--    <input type="text" name="password" value="${user.password}">-->

<#--    <form action="/action_page.php">-->
<#--    </form>-->
<#--    <input type="hidden" value="${user.id}" name="userId">-->
<#--    <button type="submit">Save</button>-->

<#--</body>-->
<#--</html>-->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Book_Edit</title>
</head>
<body>
Book Edit
<form action="/user" method="post">
    <input type="text" name="username" value="${user.username}">
    <input type="text" name="password" value="${user.password}">
    <input type="text" name="email" value="${user.email}">
    <input disabled type="text" name="trueRole" value="${user.role.title}">
    <select name="role">
        <#list roles as role>
            <option value="${role.title}">${role.title}</option>
        </#list>
    </select>
    <input type="submit">
    <br><br>
    <input type="hidden" value="${user.id}" name="userId">
    <button type="submit">Save</button>
</body>
</html>
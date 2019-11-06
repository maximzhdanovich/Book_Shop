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
    <select name="role">
        <option value="${user.role.title}">${user.role.title}</option>
        <#list roles as role>
            <#if user.role.title!=role.title>
            <option value="${role.title}">${role.title}</option>
            </#if>
        </#list>
    </select>
    <br><br>
    <input type="hidden" value="${user.id}" name="userId">
    <button type="submit">Save</button>
</body>
</html>
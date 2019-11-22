<#import "parts/common.ftl" as c>
<@c.page>
Book Edit
<form action="/user" method="post">
    <input type="text" name="username" value="${user.username}">
    <input type="text" name="password" value="${user.password}">
    <input type="email" name="email" value="${user.email}">
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
</@c.page>  
<#import "parts/common.ftl" as c>
<@c.page>
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
            <td><a href="/user/${user.id}" class="btn btn-primary">edit</a></td>
        </tr>
    </#list>
    </tbody>
</table>
</@c.page>
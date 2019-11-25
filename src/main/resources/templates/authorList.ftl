<#import "parts/common.ftl" as c>
<@c.page>
List of Authors<br>
<a href="/">main</a>
<div>
    <form method="post" enctype="multipart/form-data">
        <input type="text" name="surname" placeholder="фамилия автора">
        <input type="text" name="name" placeholder="имя автора">
        <input type="file" name="image">
        <button type="submit">Добавить</button>
    </form>
</div>
<table>
    <tbody>
    <#list authors as author>
        <tr>
            <td>${author.surname} ${author.name} </td>
            <#if author.image??>
            <td><img src="/img/${author.image.authorImage}"></td>
            </#if>
            <td><a href="/author/${author.id}">edit</a></td>
            <td><a href="/author/${author.id}/books">список книг</a></td>
        </tr>

    </#list>
    </tbody>
</table>
<#--    <#list images as image>-->
<#--        <img src="/img/${image.authorImage}">-->
<#--    </#list>-->
</@c.page>
<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">
<@c.page>
List of Books<br>
<table>
    <thead>
    <tr>
        <th>НазваниеRU</th>
        <th>НазваниеEN</th>
        <th>Author</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <#list books as book>
        <tr>
            <td>${book.titleRu}</td>
            <td>${book.titleEn}</td>
            <td>${book.author.surname} ${book.author.name}</td>
            <#if isAdmin>
            <td><a href="/book/${book.id}" class="btn btn-primary">edit</a></td>
            </#if>
        </tr>
    </#list>
    </tbody>
</table>
</@c.page>
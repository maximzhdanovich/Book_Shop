<#import "parts/common.ftl" as c>
<@c.page>
Author Edit
<form action="/author/{authorId}" method="post">
    <input type="text" name="surname" value="${author.surname}">
    <input type="text" name="name" value="${author.name}">
    <input type="hidden" value="${author.id}" name="authorId">
    <button type="submit">Save</button>
</@c.page>
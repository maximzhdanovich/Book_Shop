<#import "parts/common.ftl" as c>
<@c.page>
    Author Edit
    <form action="/author/admin/{authorId}" method="post" enctype="multipart/form-data">
    <div><label>Author surname<input type="text" name="surname" value="${author.surname}"></label></div>
    <div><label>Author name <input type="text" name="name" value="${author.name}"></label></div>
    <div><label><input type="file" name="image"></label></div>
    <#if author.image??>
        <img src="/img/author/${author.image.authorImage}">
    </#if>
    <br>
    <br>
    <input type="hidden" value="${author.id}" name="authorId">
    <button type="submit">Save</button>
</@c.page>
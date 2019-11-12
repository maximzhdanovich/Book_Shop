<#import "parts/common.ftl" as c>
<@c.page>
Book Edit
<form action="/book" method="post">
    <input type="text" name="titleRu" value="${book.titleRu}">
    <input type="text" name="titleEn" value="${book.titleEn}">
    <input type="text" name="authorSurname" value="${book.author.surname}">
    <input type="text" name="authorName" value="${book.author.name}">
    <#list categories as category>
        <div>
            <label><input type="checkbox" name="${category}"
                        ${book.categories?seq_contains(category)?string("checked", "")}>${category.titleRu}</label>
        </div>
    </#list>
    <input type="hidden" value="${book.id}" name="bookId">
    <button type="submit">Save</button>
</form>
<form method="get" action="/book/delete/${book.id}">
    <button type="submit">Delete</button>
</form>
</@c.page>
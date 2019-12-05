<#import "parts/common.ftl" as c>
<@c.page>
    Book Edit
    <#assign index=0>
    <form action="save" method="post" enctype="multipart/form-data">
        <input type="text" name="titleRu" value="${book.titleRu}">
        <input type="text" name="titleEn" value="${book.titleEn}">
        <input type="text" name="authorSurname" value="${book.author.surname}">
        <input type="text" name="authorName" value="${book.author.name}">
        <input type="file" name="image">
        <#if book.image??>
            <img src="/img/book/${book.image.bookImage}">
        </#if>

        <textarea maxlength="1000" rows="10" cols="90"
                  name="description"><#if book.description??>${book.description}</#if></textarea>
        <div class="card-columns">

            <#list categories as category>
                <div class="card">
                    <label><input type="checkbox" name="${category}" class="ml-3"
                                ${book.categories?seq_contains(category)?string("checked", "")}>
                        <#if .lang=="en">
                            ${category.titleEn}
                        <#elseif .lang=="ru">
                            ${category.titleRu}
                        </#if></label>
                </div>

            </#list>

        </div>
        <input type="hidden" value="${book.id}" name="bookId">
        <button type="submit">Save</button>
    </form>
    <br>
    <form method="get" action="/book/admin/delete/${book.id}">
        <button type="submit">Delete</button>
    </form>
</@c.page>
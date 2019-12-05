<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">
<@c.page>
    List of Authors<br>
    <#if isAdmin>
        <div>
            <form method="post" enctype="multipart/form-data">
                <input type="text" name="surname" placeholder="фамилия автора">
                <input type="text" name="name" placeholder="имя автора">
                <input type="file" name="image">
                <button type="submit">Добавить</button>
            </form>
        </div>
    </#if>
    <#list authors as author>
        <div class="row">
            <div class="col-sm m-1">
                ${author.surname} ${author.name}
            </div>
            <div class="col-sm m-1">
                <#if author.image??>
                    <img src="/img/author/${author.image.authorImage}">
                </#if>
            </div>
            <div>
                <a href="/author/${author.id}/books" class="btn btn-primary mt-2">список книг</a>
            </div>
            <#if isAdmin>
                <div class="col-sm m-1">
                    <a href="/author/admin/${author.id}" class="btn btn-primary">edit</a>
                </div>
            </#if>
        </div>
    </#list>
</@c.page>
<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">
<@c.page>
    List of Authors<br>
    <#if isAdmin>
        <#if authorError??>
            ${authorError}
        </#if>
        <div>
            <form method="post" enctype="multipart/form-data">
                <input type="text" name="surname" placeholder="фамилия автора">
                <input type="text" name="name" placeholder="имя автора">
                <input type="file" name="image">
                <button type="submit">Добавить</button>
            </form>
        </div>
    </#if>
    <form>
        <div class="card-columns">
            <#list authors as author>

                <div class="card m-2" <#--style="width: 20rem;"-->>
                    <#if author.image??>
                        <img class="card-img-top" src="/img/author/${author.image.authorImage}" height="480">
                    </#if>
                    <div class="card-body">
                        <a class="card-titlem-2">${author.surname} ${author.name}</a>
                        <br>
                        <div class="card-text">
                            <a href="/author/${author.id}/books" class="btn btn-primary m-2">список книг</a>
                            <br>
                            <#if isAdmin>
                                <a href="/author/admin/${author.id}" class="btn btn-primary m-2">edit</a>
                            </#if>
                        </div>
                    </div>
                </div>
            </#list>
        </div>
    </form>
</@c.page>
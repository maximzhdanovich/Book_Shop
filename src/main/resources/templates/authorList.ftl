<#import "parts/common.ftl" as c>
<@c.page>
    List of Authors<br>
    <div>
        <form method="post" enctype="multipart/form-data">
            <input type="text" name="surname" placeholder="фамилия автора">
            <input type="text" name="name" placeholder="имя автора">
            <input type="file" name="image">
            <button type="submit">Добавить</button>
        </form>
    </div>
<#--<table>-->
<#--    <tbody>-->
<#--    <#list authors as author>-->
<#--        <tr>-->
<#--            <td>${author.surname} ${author.name} </td>-->
<#--            <#if author.image??>-->
<#--            <td><img src="/img/${author.image.authorImage}"></td>-->
<#--            </#if>-->
<#--            <td><a href="/author/${author.id}">edit</a></td>-->
<#--            <td><a href="/author/${author.id}/books">список книг</a></td>-->
<#--        </tr>-->

<#--    </#list>-->
<#--    </tbody>-->
<#--</table>-->
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
            <div class="col-sm m-1">
                <a href="/author/admin/${author.id}" class="btn btn-primary">edit</a>
            </div>
            <div>
                <a href="/author/${author.id}/books" class="btn btn-primary">список книг</a>
            </div>
        </div>
    </#list>
</@c.page>
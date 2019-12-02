<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">
<@c.page>
    <#assign bookIndex=0>
    <#assign authorIndex=0>
    <div class="card-deck m-5">
        <#list books as book>
            <#if bookIndex<5>
                <div class="card">
                    <#assign bookIndex++>
                    <#if book.image??>
                    <img class="card-img-top" src="/img/book/${book.image.bookImage}" <#--class="leftimg" width="96" height="125"--> alt="Card image cap">
                        <#else>
                    <img class="card-img-top" src="/img/bookNot/bookImageNotFound.jpg" <#--class="leftimg" width="96" height="125"--> alt="Card image cap">
                        </#if>
                    <div class="card-body">
                    <h5  class="card-title">${book.titleEn}</h5>
                        <p class="card-text">${book.price}</p>
                    </div>
                </div>
            </#if>
        </#list>
    </div>
    Author
    <div class="card-deck m-5">
        <#list authors as author>
            <#if authorIndex<5>
                <div class="card">
                    <#assign authorIndex++>
<#--                    <#if book.image??>-->
<#--                        <img class="card-img-top" src="/img/book/${book.image.bookImage}" &lt;#&ndash;class="leftimg" width="96" height="125"&ndash;&gt; alt="Card image cap">-->
<#--                    <#else>-->
<#--                        <img class="card-img-top" src="/img/bookNot/bookImageNotFound.jpg" &lt;#&ndash;class="leftimg" width="96" height="125"&ndash;&gt; alt="Card image cap">-->
<#--                    </#if>-->
                    <#if author.image??>
                        <img src="/img/author/${author.image.authorImage}">
                    </#if>
                    <div class="card-body">
                        <h5  class="card-title">${author.surname} ${author.name} </h5>
                        <a href="/author/${author.id}/books" class="card-text">Книги</a>
                    </div>
                </div>
            </#if>
        </#list>
    </div>
<#--List of Books<br>-->
<#--    <#if isAdmin>-->
<#--        <div>-->
<#--            <form method="post" enctype="multipart/form-data">-->
<#--                <input type="number" step="0.01" name="price" placeholder="стоимость">-->
<#--                <input type="text" name="titleRu" placeholder="название ру">-->
<#--                <input type="text" name="titleEn" placeholder="название en">-->
<#--                <input type="text" name="author_surname" placeholder="фамилия автора">-->
<#--                <input type="text" name="author_name" placeholder="имя автора">-->
<#--                <input type="file" name="book_image">-->
<#--                <button type="submit">Добавить</button>-->
<#--            </form>-->
<#--        </div>-->
<#--    </#if>-->
<#--<table>-->
<#--    <thead>-->
<#--    <tr>-->
<#--        <th>НазваниеRU</th>-->
<#--        <th>НазваниеEN</th>-->
<#--        <th>Author</th>-->
<#--        <th></th>-->
<#--    </tr>-->
<#--    </thead>-->
<#--    <tbody>-->
<#--    <#list books as book>-->
<#--        <tr>-->
<#--            <td class="ml-2">${book.titleRu}</td>-->
<#--            <td class="ml-2">${book.titleEn}</td>-->
<#--            <td class="ml-2">${book.author.surname} ${book.author.name}</td>-->
<#--            <#if isAdmin>-->
<#--            <td class="ml-2"><a href="/book/admin/${book.id}" class="btn btn-primary">edit</a></td>-->
<#--            </#if>-->
<#--        </tr>-->
<#--    </#list>-->
<#--    </tbody>-->
<#--</table>-->
</@c.page>
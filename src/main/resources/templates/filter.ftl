<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">
<#include "locale/locale.ftl">

<style>
    .leftimg {
        float: left; /* Выравнивание по левому краю */
        margin: 7px 7px 7px 7px; /* Отступы вокруг картинки */
    }

    .leftText {
        float: left;
    }

</style>
<style>
    .cope_text {
        overflow: hidden;
        line-height: 20px;
    }

    .line-clamp {
        display: -webkit-box;
        -webkit-line-clamp: 3;
        -webkit-box-orient: vertical;
    }
</style>

<@c.page>

    <#assign index=0>
<#--    <#assign spring=JspTaglibs["http://www.springframework.org/tags"]>-->
    <#assign currentId=0>
    ${filter_books}
<#--    <@spring.message code="filter.books"/>-->
    <#if books?? && books?size!=0>
        <div class="ml-5 mr-5">
            <form id="basketAdd">
                <div class="card-columns">
                    <#list books as book>
                        <div class="card my-3">

<#--                            <#if book.image??>-->
<#--                            <p><img src="/img/book/${book.image.bookImage}" class="leftimg" width="96" height="125">-->
<#--                                <#else>-->
<#--                            <p><img src="/img/bookNot/bookImageNotFound.jpg" class="leftimg" width="96" height="125">-->
<#--                                </#if>-->
<#--                                ${book_author}: ${book.author.name} ${book.author.surname}-->
                                <br>
                                ${book_title}
                                <#if .lang=="en">
                                    ${book.title_En}
                                <#elseif .lang=="ru">
                                    ${book.title_Ru}
                                </#if>
                            <div class="cope_text line-clamp">
                                <#if book.description??>
                                    ${book.description}
                                </#if>
                                <br>
                                <br>
                                <br>
                            </div>
                            </p>
                            <div class="card-footer text-muted text-right">
                                <a href="/book/${book.book_Id}"
                                   class="btn btn-primary ml-2 leftText">${book_view}</a>
                                <#if isAdmin>
                                    <a href="/book/admin/${book.book_Id}"
                                       class="btn btn-primary ml-2 leftText">${book_edit}</a>
                                </#if>
                                <br>
                                <b class="mr-2">${book_price}: ${book.price} BYN</b>
                                <#if name!="unknown">
                                </#if>
                            </div>
                        </div>
                    </#list>
                </div>
                <input type="hidden" id="book_Id" value="${currentId}">
            </form>
        </div>
    <#else>
        <h5>${filter_books_not}</h5>
    </#if>
    ${filter_authors}
    <#if authors?? && authors?size!=0>
        <div class="card-columns">
            <#list authors as author>

                <div class="card m-2" <#--style="width: 20rem;"-->>
<#--                    <#if author.image??>-->
<#--                        <img class="card-img-top" src="/img/author/${author.image.authorImage}" height="400">-->
<#--                    </#if>-->
                    <div class="card-body">
                        <a class="card-titlem-2">${author.surname} ${author.name}</a>
                        <br>
                        <div class="card-text">
                            <a href="/author/${author.author_Id}/books" class="btn btn-primary m-2">список книг</a>
                            <br>
                            <#if isAdmin>
                                <a href="/author/admin/${author.author_Id}" class="btn btn-primary m-2">edit</a>
                            </#if>
                        </div>
                    </div>
                </div>
            </#list>
        </div>
    <#else>
        <h5>${filter_authors_not}</h5>
    </#if>
    ${filter_categories}
    <#if categories??>
    <#list categories as category>
        <div class="card-columns">
            <div id="accordion">
<#--                <#assign books = category.getBooks()>-->
                <div class="card my-3">
                    <div class="card-header" id="heading${category.Category_Id}">
                        <h5 class="mb-0">
                            <button class="btn btn-link collapsed" data-toggle="collapse"
                                    data-target="#collapse${category.Category_Id}" aria-expanded="true"
                                    aria-controls="collapse${category.Category_Id}">
                                <#if .lang=="en">
                                    ${category.category_Title_En}
                                <#elseif .lang=="ru">
                                    ${category.category_Title_Ru}
                                </#if>
                            </button>
                        </h5>
                    </div>
                    <div id="collapse${category.Category_Id}" class="collapse" aria-labelledby="heading${category.Category_Id}"
                         data-parent="#accordion">
                        <div class="card-body">
<#--                            <#list books as book>-->
<#--                                <#if index<5>-->
<#--                                    <div class="card">-->
<#--                                        Author: ${book.author.surname} ${book.author.surname}<br>-->
<#--                                        Title:-->
<#--                                        <#if .lang=="en">-->
<#--                                            ${book.titleEn}-->
<#--                                        <#elseif .lang=="ru">-->
<#--                                            ${book.titleRu}-->
<#--                                        </#if>-->
<#--                                        <br>-->
<#--                                        <a href="/book/${book.bookId}" class="right">Book</a>-->
<#--                                    </div>-->
<#--                                    <#assign index++>-->
<#--                                </#if>-->
<#--                            </#list>-->
                            <#assign index=0>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </#list>
    <#else>
        <h5>${filter_categories_not}</h5>
    </#if>

</@c.page>
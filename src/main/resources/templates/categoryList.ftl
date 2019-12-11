<#import "parts/common.ftl" as c>

<@c.page>
    CATEGORIES
    <#assign index=0>
    <div class="card-columns">
        <div id="accordion">

            <#list categories as category>
                <#assign books=category.getBooks()>

                <div class="card my-3">
                    <div class="card-header" id="heading${category.id}">
                        <a class="mb-0">
                            <button class="btn btn-link collapsed" data-toggle="collapse"
                                    data-target="#collapse${category.id}" aria-expanded="true"
                                    aria-controls="collapse${category.id}">
                                <#if .lang=="en">
                                    ${category.titleEn}
                                <#elseif .lang=="ru">
                                    ${category.titleRu}
                                </#if>
                            </button>
                        </a>
                    </div>
                    <div id="collapse${category.id}" class="collapse" aria-labelledby="heading${category.id}"
                         data-parent="#accordion">
                        <div class="card-body">
                            <#if books?size!=0>
                                <#list books as book>
                                    <#if index<5>
                                        <div class="card">
                                            Author: ${book.author.surname} ${book.author.surname}<br>
                                            Title:
                                            <#if .lang=="en">
                                                ${book.titleEn}
                                            <#elseif .lang=="ru">
                                                ${book.titleRu}
                                            </#if>
                                            <br>
                                            <a href="/book/${book.id}" class="right">Book</a>
                                        </div>
                                        <#assign index++>
                                    </#if>
                                </#list>
                                <a href="/category/${category.id}" methods="get">All books</a>
                            <#else>
                                Books not found
                            </#if>
                            <#assign index=0>
                        </div>
                    </div>
                </div>

            </#list>
        </div>
    </div>
</@c.page>
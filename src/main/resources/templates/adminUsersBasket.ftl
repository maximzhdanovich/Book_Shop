<#import "parts/common.ftl" as c>
<@c.page>
    <#assign price = 0>
    <#assign processingPrice = 0>
    <div class="container center mt-5">
    Book in Cart
    <table class="table table-bordered ">
        <thead>
        <tr>
            <th>Цена</th>
            <th>Название</th>
            <th>Author</th>
        </tr>
        </thead>
        <tbody>
        <#list books as book>
            <tr>
                <td>#{book.price}</td>
                <td><#if .lang=="en">
                        ${book.titleEn}
                    <#elseif .lang=="ru">
                        ${book.titleRu}
                    </#if></td>
                <td>${book.author.surname} ${book.author.name}</td>

            </tr>
            <#assign price += book.price>
        </#list>
        </tbody>
    </table>
    <br>
    <a>Total price ${price}</a>
    <br>
    Book in processing
    <table class="table table-bordered ">
        <thead>
        <tr>
            <th>Цена</th>
            <th>Название</th>
            <th>Author</th>
        </tr>
        </thead>
        <tbody>
        <#list booksinprocessing as bookinprocessing>
            <tr>
                <td>#{bookinprocessing.price}</td>
                <td><#if .lang=="en">
                        ${bookinprocessing.titleEn}
                    <#elseif .lang=="ru">
                        ${bookinprocessing.titleRu}
                    </#if></td>
                <td>${bookinprocessing.author.surname} ${bookinprocessing.author.name}</td>
            </tr>
            <#assign processingPrice += bookinprocessing.price>
        </#list>
        </tbody>
    </table>
    <br>
    <a>Total price ${processingPrice}</a>
</@c.page>
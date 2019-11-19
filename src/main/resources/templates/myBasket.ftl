<#import "parts/common.ftl" as c>
<@c.page>
    <table>
        <thead>
        <tr>
            <th>НазваниеRU</th>
            <th>НазваниеEN</th>
            <th>Author</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <form id="qwerty">
            <#list books as book>

                <tr>
                    <td>${book.titleRu}</td>
                    <td>${book.titleEn}</td>
                    <td>${book.author.surname} ${book.author.name}</td>
                </tr>
            </#list>
        </form>
        </tbody>
    </table>
</@c.page>
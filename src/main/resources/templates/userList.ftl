<#import "parts/common.ftl" as c>
<@c.page>
    List of Books
        <table class="table table-bordered center">
            <thead>
            <tr>
                <th>UserName</th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <#list users as user>
                <tr>
                    <td>${user.username}</td>
                    <td class="text-center"><a href="/user/${user.id}" class="btn btn-primary">edit</a></td>
                    <td class="text-center"><a href="/user/${user.id}/basket" class="btn btn-primary">basket</a></td>
                </tr>
            </#list>
            </tbody>
        </table>

</@c.page>
<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">
<@c.page>
<#--    List of Authors-->
<#--    <br>-->
<#--    <br>-->
    <#if isAdmin>
        <#if authorError??>
            ${authorError}
        </#if>
        <div>
            <form method="post" enctype="multipart/form-data">
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="basic-addon1">фамилия автора</span>
                    </div>
                    <input type="text" name="surname" class="form-control" placeholder="фамилия автора"
                           aria-label="фамилия автора" aria-describedby="basic-addon1">
                </div>
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="basic-addon1">имя автора</span>
                    </div>
                    <input type="text" name="name" class="form-control" placeholder="имя автора" aria-label="имя автора"
                           aria-describedby="basic-addon1">
                </div>

                <#--                <input type="text" name="surname" placeholder="фамилия автора">-->
                <#--                <input type="text" name="name" placeholder="имя автора">-->
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="inputGroupFileAddon01">Image</span>
                    </div>
                    <div class="custom-file">
                        <input type="file" name="image" class="custom-file-input" id="inputGroupFile01"
                               aria-describedby="inputGroupFileAddon01">
                        <label class="custom-file-label" for="inputGroupFile01">Choose file</label>
                    </div>
                </div>
                <#--                <input type="file" name="image">-->
                <button type="submit" class="btn btn-primary m-2">Добавить</button>
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
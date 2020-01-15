<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">
<#include "locale/locale.ftl">
<@c.page>
    <style>.card-columns {

            column-count: 4;
        }
    </style>
    <#if isAdmin>
        <#if authorError??>
            ${authorError}
        </#if>
        <div>
            <form method="post" enctype="multipart/form-data">
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="basic-addon1">${author_list_author_surname}</span>
                    </div>
                    <input type="text" name="surname" class="form-control" placeholder="${author_list_author_surname}"
                           aria-label="surname" aria-describedby="basic-addon1">
                </div>
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="basic-addon1">${author_list_author_name}</span>
                    </div>
                    <input type="text" name="name" class="form-control" placeholder="${author_list_author_name}"
                           aria-label="name" aria-describedby="basic-addon1">
                </div>

                <#--                <input type="text" name="surname" placeholder="фамилия автора">-->
                <#--                <input type="text" name="name" placeholder="имя автора">-->
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="inputGroupFileAddon01">${author_list_image}</span>
                    </div>
                    <div class="custom-file">
                        <input type="file" name="image" class="custom-file-input" id="inputGroupFile01"
                               aria-describedby="inputGroupFileAddon01">
                        <label class="custom-file-label" for="inputGroupFile01">${author_list_choose_file}</label>
                    </div>
                </div>
                <#--                <input type="file" name="image">-->
                <button type="submit" class="btn btn-primary m-2">${author_list_add}</button>
            </form>
        </div>
    </#if>
    <form>
        <div class="card-columns">
            <#list authors as author>

                <div class="card m-2" <#--style="width: 20rem;"-->>
                    <#if author.image??>
                        <img class="card-img-top" src="/img/author/${author.image.authorImage}" height="370">
                    <#else >
                        <img src="/img/bookNot/authorImageNotFound.png" width="260.5" height="370">
                    </#if>
                    <div class="card-body">
                        <a class="card-title m-2">${author.surname} ${author.name}</a>
                        <br>
                        <div class="card-text">
                            <a href="/author/${author.id}/books" class="btn btn-primary m-2">${author_list_author_book_list}</a>
                            <br>
                            <#if isAdmin>
                                <a href="/author/admin/${author.id}" class="btn btn-primary m-2">${author_list_edit}</a>
                            </#if>
                        </div>
                    </div>
                </div>
            </#list>
        </div>
    </form>
</@c.page>
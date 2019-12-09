<#import "parts/common.ftl" as c>
<#include "locale/locale.ftl">
<@c.page>
<#--    <#assign spring=JspTaglibs["http://www.springframework.org/tags"]>-->
    <a href="/account/basket">${account_basket}</a>
    <br><br>
    <a href="/account/edit">${account_configuration}</a>
    <br><br>
    <a href="/account/delete">${account_delete}</a>
</@c.page>
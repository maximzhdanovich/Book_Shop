<#import "parts/common.ftl" as c>
<@c.page>
    <#assign spring=JspTaglibs["http://www.springframework.org/tags"]>
    <a href="/account/basket"><@spring.message code="account.basket"/></a>
    <br><br>
    <a href="/account/edit"><@spring.message code="account.configuration"/></a>
    <br><br>
    <a href="/account/delete"><@spring.message code="account.delete"/></a>
</@c.page>
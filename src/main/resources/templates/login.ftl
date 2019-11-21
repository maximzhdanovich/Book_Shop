<#import "parts/common.ftl" as c>
<#--<#include "locale/messages.properties" >-->

<@c.page>
    <#assign spring=JspTaglibs["http://www.springframework.org/tags"]>
Login page
<form action="/login" method="post">
    <div><label> User Name : <input type="text" name="username"/> </label></div>
    <div><label> Password: <input type="password" name="password"/> </label></div>
    <input type="hidden" name="_csrf" value="{{_csrf.token}}" />
    <div><input type="submit" value="Sign In"/></div>
</form>
<a href="/registration"><@spring.message code="title.page.login"/></a>
</@c.page>

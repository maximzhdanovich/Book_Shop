<#import "parts/common.ftl" as c>

<@c.page>
    <#assign spring=JspTaglibs["http://www.springframework.org/tags"]>
    <div class="m-2 ml-3">Login page</div>
    <form action="/login" method="post">
        <div class="form-group row ml-1">
            <label class="col-sm-2 col-form-label"><@spring.message code="login.username"/> :</label>
            <div class="col-sm-6">
                <input type="text" name="username"
                       class="form-control"
                       placeholder="User name"/>
            </div>
        </div>
        <div class="form-group row ml-1">
            <label class="col-sm-2 col-form-label"><@spring.message code="login.password"/> :</label>
            <div class="col-sm-6">
                <input type="password" name="password"
                       class="form-control"
                       placeholder="Password"/>
            </div>
        </div>
        <input type="hidden" name="_csrf" value="{{_csrf.token}}"/>
        <div>
            <button class="btn btn-primary m-3" type="submit"> <@spring.message code="login.sign.in"/></button>
        </div>
    </form>
    <a class="btn btn-primary m-3" href="/registration"><@spring.message code="login.registration"/></a>
</@c.page>

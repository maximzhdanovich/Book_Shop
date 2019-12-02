<#import "parts/common.ftl" as c>
<@c.page>

    <#assign spring=JspTaglibs["http://www.springframework.org/tags"]>
    <form action="/registration" method="post">
        <div class="form-group row">
            <label class="col-sm-2 col-form-label"><@spring.message code="registration.username"/>:</label>
            <div class="col-sm-6">
                <input type="text" name="username" value="<#if user??>${user.username}</#if>"
                       class="form-control ${(usernameError??)?string('is-invalid', '')}"
                       placeholder=<@spring.message code="registration.username"/> />
                <#if usernameError??>
                    <div class="invalid-feedback">
                        ${usernameError}
                    </div>
                </#if>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label"><@spring.message code="registration.password"/>:</label>
            <div class="col-sm-6">
                <input type="password" name="password"
                       class="form-control ${(passwordError??)?string('is-invalid', '')}"
                       placeholder=<@spring.message code="registration.password"/> />
                <#if passwordError??>
                    <div class="invalid-feedback">
                        ${passwordError}
                    </div>
                </#if>
            </div>
        </div>

            <div class="form-group row">
                <label class="col-sm-2 col-form-label"><@spring.message code="registration.password.repeat"/>:</label>
                <div class="col-sm-6">
                    <input type="password" name="password1"
                           class="form-control ${(password1Error??)?string('is-invalid', '')}"
                           placeholder="<@spring.message code="registration.password"/>" />
                    <#if password1Error??>
                        <div class="invalid-feedback">
                            ${password1Error}
                        </div>
                    </#if>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label"><@spring.message code="registration.email"/>:</label>
                <div class="col-sm-6">
                    <input type="email" name="email" value="<#if user??>${user.email}</#if>"
                           class="form-control ${(emailError??)?string('is-invalid', '')}"
                           placeholder="some@some.com" />
                    <#if emailError??>
                        <div class="invalid-feedback">
                            ${emailError}
                        </div>
                    </#if>
                </div>
            </div>
        <button class="btn btn-primary" type="submit">Create</button>
    </form>
</@c.page>
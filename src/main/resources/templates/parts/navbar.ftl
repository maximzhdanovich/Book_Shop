<#include "security.ftl">
<#assign spring=JspTaglibs["http://www.springframework.org/tags"]>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/">Book Shop</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/"><@spring.message code="navbar.home"/></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/book"><@spring.message code="navbar.books"/></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/author"><@spring.message code="navbar.authors"/></a>
            </li>
            <#if isAdmin>
                <li class="nav-item">
                    <a class="nav-link" href="/user"><@spring.message code="navbar.users"/></a>
                </li>
            </#if>
        </ul>
        <div class="mr-2">
            <form id="langEdit">
                <div class="input-group mr-3">
                    <select id="lang" class="custom-select" name="lang" onchange="langEdit()">
                        <option><@spring.message code="navbar.lang"/></option>
                        <option value="ru" <#if .lang=="ru"> disabled</#if>><@spring.message code="navbar.lang.ru"/></option>
                        <option value="en" <#if .lang=="en"> disabled</#if>><@spring.message code="navbar.lang.en"/></option>
                    </select>
                </div>
                <button id="langSubmit" type="submit" hidden> ok</button>
            </form>
        </div>

        <#if name=="unknown">
            <div class="mr-3">
                <b>${name}</b></div>
            <a href="/login"><@spring.message code="navbar.log.in"/></a>
        <#else >
            <div class="mr-2">
                <form action="/logout" method="post">
                    <input type="hidden" name="_csrf" value="{{_csrf.token}}"/>
                    <button type="submit"><@spring.message code="navbar.log.out"/></button>
                </form>
            </div>
            <a href="/account">${name}</a>
        </#if>
    </div>
    <script>
        function langEdit() {
            document.getElementById("langSubmit").click();
        }
    </script>
</nav>

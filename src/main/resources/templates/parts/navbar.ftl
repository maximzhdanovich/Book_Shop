<#include "security.ftl">

<#include "*/locale/locale.ftl">
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/">Book Shop</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/">${navbar_home}</a>
                <#--                <a class="nav-link" href="/"><@spring.message code="navbar.home"/></a>-->
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/book">${navbar_books}</a>
                <#--                <a class="nav-link" href="/book"><@spring.message code="navbar.books"/></a>-->
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/author">${navbar_authors}</a>
                <#--                <a class="nav-link" href="/author"><@spring.message code="navbar.authors"/></a>-->
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/category">${navbar_categories}</a>
                <#--                <a class="nav-link" href="/category"><@spring.message code="navbar.categories"/></a>-->
            </li>
            <#if isAdmin>
                <li class="nav-item">
                    <a class="nav-link" href="/user">${navbar_users}</a>
                    <#--                    <a class="nav-link" href="/user"><@spring.message code="navbar.users"/></a>-->
                </li>
            </#if>
        </ul>
        <div>
            <form method="get" action="/filter" class="mr-2">
                <input type="text" name="filter" class="form-control"
                       placeholder=${filter_search} value="${filter!}">
                <#--                       placeholder=<@spring.message code="filter.search"/> value="${filter!}">-->
            </form>
        </div>
        <div class="mr-2">
            <form id="langEdit">
                <div class="input-group mr-3">
                    <select id="lang" class="custom-select" name="lang" onchange="langEdit()">
                        <option>${navbar_lang}</option>
                        <option value="ru" <#if .lang=="ru"> disabled</#if>>${navbar_lang_ru}</option>
                        <option value="en" <#if .lang=="en"> disabled</#if>>${navbar_lang_en}</option>
                    </select>
                </div>
                <button id="langSubmit" type="submit" hidden>ok</button>
            </form>
        </div>

        <#if name=="unknown">
<#--            <div class="mr-3">-->
<#--                <b>${name}</b></div>-->
            <a href="/login" class="btn btn-primary">${navbar_log_in}</a>
        <#--            <a href="/login"><@spring.message code="navbar.log.in"/></a>-->
        <#else >

            <div class="mr-4">
                <form action="/logout" method="post">
                    <input type="hidden" name="_csrf" value="{{_csrf.token}}"/>

                    <#--                    <button type="submit"><@spring.message code="navbar.log.out"/></button>-->
                    <a href="/account/basket" class="btn btn-primary">${account_basket}</a>
                    <a href="/account"  class="btn btn-primary">${name}</a>
                    <button type="submit" class="btn btn-primary">${navbar_log_out}</button>
                </form>
            </div>
        </#if>
    </div>
    <script>
        function langEdit() {
            document.getElementById("langSubmit").click();
        }
        function account() {
            document.getElementById("account123").click();
        }
    </script>
</nav>

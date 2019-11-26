<#include "security.ftl">
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/">Book Shop</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/">Home </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/book">Book </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/author">Author </a>
            </li>
            <#if isAdmin>
                <li class="nav-item">
                    <a class="nav-link" href="/user">User </a>
                </li>
            </#if>
        </ul>
        <div class="mr-2">
            <form id="langEdit">
                <div class="input-group mr-3">
                    <select id="lang" class="custom-select" name="lang" onchange="langEdit()">
                        <option>Language</option>
                        <option value="ru" <#if .lang=="ru"> disabled</#if>>Russian</option>
                        <option value="en" <#if .lang=="en"> disabled</#if>>English</option>
                    </select>
                </div>
                <button id="langSubmit" type="submit" hidden> ok</button>
            </form>
        </div>

        <#if name=="unknown">
            <div class="mr-3">
                <b>${name}</b></div>
            <a href="/login">login</a>
        <#else >
            <div class="mr-2">
                <form action="/logout" method="post">
                    <input type="hidden" name="_csrf" value="{{_csrf.token}}"/>
                    <input type="submit" value="Sign Out"/>
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

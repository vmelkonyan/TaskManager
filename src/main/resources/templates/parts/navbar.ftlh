<#import "login_p.ftlh" as l>
<#include "security.ftlh">
<nav class="navbar navbar-expand-lg  navbar-dark bg-dark">
    <a class="navbar-brand" href="#">Task Manager</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="/">Home</a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="/main">Tasks</a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="/user">User List</a>
            </li>
        </ul>
        <div>
            <@l.logout >
            </@l.logout>
        </div>
        <#--        <form method="post" action="/logout">-->
        <#--            <input type="hidden" name="_csrf" value="${_csrf.token}"/>-->
        <#--            <button type="submit">logout</button>-->
        <#--        </form>-->
        <div class="navbar-text"> ${name}</div>
    </div>
</nav>
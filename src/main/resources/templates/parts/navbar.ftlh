<#import "login_p.ftlh" as l>
<#include "security.ftlh">
<nav class=" navbar-expand-lg  navbar navbar-dark bg-primary">
    <a class="navbar-brand" href="#">Task Manager</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <#if !isActive>
                <li class="nav-item active">
                    <a class="nav-link" href="/login">Login</a>
                </li>
            </#if>
            <#if isActive>
                <li class="nav-item active">
                    <a class="nav-link" href="/main">Tasks</a>
                </li>
            </#if>
            <#if isManager>
                <li class="nav-item active">
                    <a class="nav-link" href="/user">User List</a>
                </li>
            </#if>
        </ul>
        <#if isActive>

            <form method="get" action="/main" class="form-inline my-2 my-lg-0">
                <input class="form-control mr-sm-2" type="search" id="filter" name="filter" placeholder="Search by task name" value="${filter?ifExists}" required aria-label="Search">
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <button class="btn btn-outline-primary bg-light my-2 my-sm-0" type="submit">Search</button>
            </form>

<#--            <form method="get" action="/main" class="form-row">-->
<#--                <input type="text" class="form-control col-sm-2 ml-3" id="filter" name="filter"-->
<#--                       placeholder="Search by task name" value="${filter?ifExists}" required>-->
<#--                <input type="hidden" name="_csrf" value="${_csrf.token}"/>-->
<#--                <button type="submit" class="button ml-2">Search</button>-->
<#--            </form>-->
        </#if>
        <div>
            <@l.logout />
        </div>
        <div class="navbar-text ml-3"> ${name}</div>
    </div>
</nav>
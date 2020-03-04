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
                <li class="nav-item active">
                    <a class="nav-link" href="/user/add">Add New User</a>
                </li>
            </#if>
        </ul>
        <#if isActive>
            <form method="get" action="/main" class="form-inline my-2 my-lg-0">
                <input class="form-control mr-sm-2" type="search" id="filter" name="filter" placeholder="Search by task name" value="${filter?ifExists}"  aria-label="Search">
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <button class="btn btn-outline-primary bg-light my-2 my-sm-0" type="button" href="/search">Search</button>
            </form>
        </#if>
        <div>
            <@l.logout />
        </div>
        <div class="navbar-text ml-3"> <span style="color: white; "> ${name}</div>
    </div>
</nav>
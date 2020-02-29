<#import "parts/common.ftlh" as c>
<#include "parts/security.ftlh">
<@c.page>
    <form action="/user" method="post">
        <#if isManager>
            <#list userRoles as userRol>
                <div class="custom-control custom-radio">
                    <input type="radio" class="custom-control-input" id="${userRol}" name="${userRol}">
                    <label class="custom-control-label" for="${userRol}">${userRol}</label>
                </div>
            </#list>
        </#if>
        <input class="card-title mb-2 text-muted" type="text" value="${user.username}" name="username">
        <input class="card-title mb-2 text-muted"  type="hidden" value="${user.id}" name="userId">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit">Save</button>
    </form>
</@c.page>
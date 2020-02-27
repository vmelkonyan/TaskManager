<#import "parts/common.ftlh" as c>
<#import "parts/login_p.ftlh" as l>
<@c.page>

    <@l.login "/register" >
    </@l.login>
    <#list userRoles as userRol>
        <div>
            <label> <input type="checkbox" name="${userRol}" >${userRol}</label>
        </div>
    </#list>
</@c.page>
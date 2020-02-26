<#import "parts/common.ftlh" as c>
<#import "parts/login_p.ftlh" as l>
<@c.page>
    <#list userRoles as userRol>
        <div>
            <label> <input type="checkbox" name="${userRol}">${userRol}</label>
        </div>
    </#list>
    <@l.login "/register"/>
</@c.page>
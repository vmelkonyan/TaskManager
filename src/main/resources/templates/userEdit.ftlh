<#import "parts/common.ftlh" as c>
<@c.page>
    <form action="/user" method="post">
        <#list userRoles as userRol>
            <div>
               <label> <input type="checkbox" name="${userRol}" ${user.userRoles?seq_contains(userRol)?string("chacked","")}>${userRol}</label>
            </div>
        </#list>
        <input type="text" value="${user.username}" name = "username">
        <input type="hidden" value="${user.id}" name = "userId">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit">Save</button>
    </form>
</@c.page>
<#import "parts/common.ftlh" as c>
<@c.page>
    <form action="/editTask" method="post">
<#--        <#list userRoles as userRol>-->
<#--            <div>-->
<#--               <label> <input type="checkbox" name="${userRol}" ${user.userRoles?seq_contains(userRol)?string("chacked","")}>${userRol}</label>-->
<#--            </div>-->
<#--        </#list>-->
        <input type="text" value="${task.taskName}" name = "taskName">
        <input type="text" value="${task.taskDescription}" name = "taskDescription">
        <input type="hidden" value="${task.id}" name = "taskId">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit">Save</button>
    </form>
</@c.page>
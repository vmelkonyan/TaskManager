<#import "parts/common.ftlh" as c>
<@c.page>
    <table>
        <thead>
        <tr>
            <th>Name</th>
            <th>Role</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <#list users as user>
        <div class="card  mt-2" style="width: 18rem;">
            <div class="card-body">
                <h5 class="card-title">${user.username} <label> User Name</label></h5>
                 <#list user.userRoles as userRol>${userRol} <#sep >, </#list>
                <a href="/user/${user.id}" class="btn btn-primary">Edit user</a>
            </div>
            <#--            <tr>-->
            <#--                <td>${user.username}</td>-->
            <#--                <td><#list user.userRoles as userRol>${userRol} <#sep >, </#list></td>-->
            <#--                <td><a href="/user/${user.id}">edit </a></td>-->
            <#--            </tr>-->
            </#list>
        </tbody>
    </table>
</@c.page>
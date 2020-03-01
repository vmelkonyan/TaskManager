<#import "parts/common.ftlh" as c>
<@c.page>
    <div class="card-columns">
        <#list users as user>
            <div class="card  mt-2" style="width: 18rem;">
                <div class="card-body">
                    <h5 class="card-title"><label>User Name :</label> ${user.username}</h5>
                    <#list user.userRoles as userRol>
                        <h5 class="card-title"><label>User Role : </label>${userRol}</h5>
                    </#list>
                    <a href="/user/${user.id}" class="btn btn-primary">Edit User</a>
                </div>
            </div>
        <#else >
            No User
        </#list>
    </div>

</@c.page>
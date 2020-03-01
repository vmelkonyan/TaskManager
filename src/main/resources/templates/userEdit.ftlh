<#import "parts/common.ftlh" as c>
<#include "parts/security.ftlh">
<@c.page>
    <form action="/user" method="post">

        <div class="form-group">
            <label for="exampleInputEmail1">User Name</label>
            <input type="text" class="form-control col-sm-3" value="${editUser.username}" name="username" required>
        </div>
        <div class="form-group">
            <input type="hidden" class="form-control col-sm-3" value="${editUser.id}" name="userId">
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit" class="btn btn-primary">Save Changes</button>
        <#list userRoles as userRole>

            <div class="custom-control custom-radio mt-2">
                <input type="radio" class="custom-control-input" id="${userRole}" name="userRol" value="${userRole}"
                        <#if userRole == editUser.currentUserRole>
                            checked
                        </#if>
                >
                <label class="custom-control-label" for="${userRole}">${userRole}</label>
            </div>
        </#list>
    </form>
</@c.page>
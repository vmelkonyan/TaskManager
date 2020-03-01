<#import "parts/common.ftlh" as c>
<#include "parts/security.ftlh">
<@c.page>
    <form action="/user" method="post">

        <div class="form-group">
            <label for="exampleInputEmail1">User Name</label>
            <input type="text" class="form-control col-sm-3" value="${user.username}" name="username" required>
        </div>
        <div class="form-group">
            <input type="hidden" class="form-control col-sm-3"  value="${user.id}" name="userId">
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit" class="btn btn-primary">Save Changes</button>
        <#list userRoles as userRol>
            <div class="custom-control custom-radio mt-2">
                <input type="radio" class="custom-control-input" id="${userRol}" name="userRol" value="${userRol}" required>
                <label class="custom-control-label" for="${userRol}">${userRol}</label>
            </div>
        </#list>
    </form>
</@c.page>
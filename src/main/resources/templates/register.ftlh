<#import "parts/common.ftlh" as c>
<#import "parts/login_p.ftlh" as l>
<@c.page>
    ${messageaa}
    <form action="/register" method="post">
        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> User Name : <input type="text" name="username" required/> </label>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> Password: <input type="password" name="password" required/> </label>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <div><input type="submit" value="Register"/></div>
        <#list userRoles as userRol>
            <div class="custom-control custom-radio">
                <input type="radio" class="custom-control-input" id="${userRol}" name="${userRol}">
                <label class="custom-control-label" for="${userRol}">${userRol}</label>
            </div>
        </#list>
    </form>
</@c.page>
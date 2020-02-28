<#import "parts/common.ftlh" as c>
<#import "parts/login_p.ftlh" as l>
<@c.page>

<#--    <@l.login "/register" >-->
<#--    </@l.login>-->
    <form action="/register" method="post">
        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> User Name : <input type="text" name="username"/> </label></div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> Password: <input type="password" name="password"/> </label></div>
        <#--        <div>-->
        <#--            <label><input type="hidden" name="userRol"/> </label></div>-->
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <div><input type="submit" value="Register"/></div>

        <#list userRoles as userRol>
            <div>
                <label> <input type="checkbox" name="${userRol}" >${userRol}</label>
            </div>
        </#list>
    </form>
</@c.page>
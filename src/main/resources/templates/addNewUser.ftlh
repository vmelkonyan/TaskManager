<#import "parts/common.ftlh" as c>
<#import "parts/login_p.ftlh" as l>
<@c.page>
    ${usernameError?ifExists}
    <form action="/user/add" method="post">

        <div class="form-group">
            <label for="exampleInputEmail1">User Name</label>
            <input type="text" class="form-control col-sm-3" id="username" name="username" placeholder="User Name" required>
        </div>
        <div class="form-group">
            <label for="exampleInputPassword1">Password</label>
            <input type="password" class="form-control col-sm-3" id="password" name="password" placeholder="Password" required>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit" class="btn btn-primary">Register</button>
        <#list userRoles as userRol>
            <div class="custom-control custom-radio mt-2">
                <input type="radio" class="custom-control-input" id="${userRol}" name="userRol" value="${userRol}" required>
                <label class="custom-control-label" for="${userRol}">${userRol}</label>
            </div>
        </#list>
    </form>
</@c.page>
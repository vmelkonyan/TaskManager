<#macro login path>
    <form action="${path}" method="post">
        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> User Name : <input type="text" name="username" placeholder=""/> </label></div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> Password: <input type="password" name="password" placeholder=""/> </label></div>
<#--        <div>-->
<#--            <label><input type="hidden" name="userRol"/> </label></div>-->
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <div><input type="submit" value="Login"/></div>
    </form>
</#macro>
<#macro logout>
    <form method="post" action="/logout">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit">logout</button>
    </form>
</#macro>
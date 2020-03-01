<#macro login path>


    <form  action="${path}" method="post" class = "container mt-1">
        ${errorMessage?ifExists}
        <div class="form-group">
            <label for="exampleInputEmail1">User Name</label>
            <input type="text" class="form-control col-sm-3" id="username"  name="username" placeholder="User Name" required>
        </div>
        <div class="form-group">
            <label for="exampleInputPassword1">Password</label>
            <input type="password" class="form-control col-sm-3" id="password" name="password" placeholder="Password" required>
        </div>

        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit" class="btn btn-primary">Login</button>
    </form>

</#macro>
<#macro logout>
    <form method="post" action="/logout">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button  class="btn btn-outline-primary bg-light" type="submit">Logout</button>
<#--        <button type="submit">logout</button>-->
    </form>
</#macro>
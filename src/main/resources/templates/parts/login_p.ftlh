<#macro login path>


    <form  action="${path}" method="post" class = "container mt-1">
        <div class="form-group">
            <label for="exampleInputEmail1">User Name</label>
            <input type="text" class="form-control col-sm-3" id="username"  name="username" placeholder="User Name">
        </div>
        <div class="form-group">
            <label for="exampleInputPassword1">Password</label>
            <input type="password" class="form-control col-sm-3" id="password" name="password" placeholder="Password">
        </div>

        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit" class="btn btn-primary">Login</button>
    </form>

</#macro>
<#macro logout>
    <form method="post" action="/logout">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit">logout</button>
    </form>
</#macro>
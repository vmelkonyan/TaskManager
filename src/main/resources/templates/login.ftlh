<#import "parts/common.ftlh" as c>
<#import "parts/login_p.ftlh" as l>
<@c.page>
    <@l.login "/login" />
    <div class="container mt-2">
        <a href="/register">Add New User</a>
    </div>
</@c.page>
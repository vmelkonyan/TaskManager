<#import "parts/common.ftlh" as c>
<#import "parts/login_p.ftlh" as l>
<@c.page>
    <div>
        <@l.logout/>
        <span><a href="/user">User List </a> </span>
    </div>

    <div>
        <form method="post">
            <input type="text" name="taskName" placeholder="add name"/>
            <input type="date" name="taskCreateDate" placeholder="set create date"/>
            <input type="date" name="taskUpdateDate" placeholder="set update date"/>
            <input type="text" name="taskDescription" placeholder="set description"/>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit">Add</button>
        </form>
    </div>
    <div>Hollo all</div>
    <form method="post" action="filter">
        <input type="text" name="filter" placeholder="filter"/>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit">Filter</button>
    </form>

    <#list tasks as task>
        <div>
            <b>${task.id}</b>
            <span>${task.taskName}</span>
            <span>${task.taskCreateDate?date}</span>
            <span>${task.taskUpdateDate?date}</span>
            <span>${task.taskDescription}</span>
            <strong>${task.autorName}</strong>
        </div>
        <#else >
        No Mesaege
    </#list>
</@c.page>
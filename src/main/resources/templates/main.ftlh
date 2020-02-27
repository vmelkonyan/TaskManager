<#import "parts/common.ftlh" as c>
<#import "parts/login_p.ftlh" as l>
<@c.page>
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
            <div class="card" style="width: 18rem;">
                <div class="card-body">
                    <h5 class="card-title">${task.taskName}</h5>
                    <h6 class="card-subtitle mb-2 text-muted">${task.autorName}</h6>
                    <p class="card-text">${task.taskDescription}</p>

                </div>
                <div class="card-body">
                    <h5 class="card-title">${task.taskCreateDate?date}</h5>
                    <h6 class="card-subtitle mb-2 text-muted">${task.taskUpdateDate?date}</h6>
                    <a href="/editTask/${task.id}" class="btn btn-primary">edit task</a>
                </div>
            </div>
            <#--            <b>${task.id}</b>-->
            <#--            <span>${task.taskName}</span>-->
            <#--            <span>${task.taskCreateDate?date}</span>-->
            <#--            <span>${task.taskUpdateDate?date}</span>-->
            <#--            <span>${task.taskDescription}</span>-->
            <#--            <strong>${task.autorName}</strong>-->
        </div>
    <#else >
        No Mesaege
    </#list>
</@c.page>
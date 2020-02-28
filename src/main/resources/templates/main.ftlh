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

    <#list tasks as task>
        <div>
            <div class="card" style="width: 18rem;">
                <div class="card-body">
                    <h5 class="card-title">${task.taskName} <label>Task Name</label></h5>
                    <h5 class="card-title mb-2 text-muted">${task.autorName}<label>Task Author</label></h5>
                    <h5 class="card-title mb-2 text-muted">${task.taskStatus}<label>Task Author</label></h5>
                    <p class="card-text">${task.taskDescription} <label>Task Description</label></p>

                </div>
                <div class="card-body">
                    <h5 class="card-title">${task.taskCreateDate}</h5>
                    <h6 class="card-subtitle mb-2 text-muted">${task.taskUpdateDate}</h6>
                    <a href="/editTask/${task.id}" class="btn btn-primary">edit task</a>
                </div>
            </div>
        </div>
    <#else >
        No Messaege
    </#list>
</@c.page>
<#import "parts/common.ftlh" as c>
<#import "parts/login_p.ftlh" as l>
<@c.page>
    <div>
        <form method="post">
            <input type="text" name="taskName" placeholder="add name"/>
<#--            <input type="date" name="taskCreateDate" placeholder="set create date"/>-->
<#--            <input type="date" name="taskUpdateDate" placeholder="set update date"/>-->
            <input type="text" name="taskDescription" placeholder="set description"/>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit">Add Task</button>
        </form>
    </div>
<div class = "card-columns">
    <#list tasks as task>
<#--        <div>-->
            <div class="card  mt-2" style="width: 18rem;">
                <div class="card-body">
                    <h5 class="card-title"> <label>Task Name :</label> ${task.taskName}</h5>
                    <h5 class="card-title"><label>Task Author : </label>${task.autorName}</h5>
                    <h5 class="card-title"><label>Task Status : </label> ${task.taskStatus}</h5>
                    <h5 class="card-text"> <label>Task Description : </label> ${task.taskDescription} </h5>

                    <h5 class=<label>Cr. Date : </label> ${task.taskCreateDate}</h5>
                    <h5 class="card-title "> <label>Update Date : </label> ${task.taskUpdateDate}</h5>
                    <a href="/editTask/${task.id}" class="btn btn-primary">edit task</a>
                </div>
        </div>
    <#else >
        No Task
    </#list>
</div>
</@c.page>
<#import "parts/common.ftlh" as c>
<#import "parts/login_p.ftlh" as l>
<@c.page>
    <div>
        <form method="post" class="form-row">
            <input type="text" class="form-control col-sm-3 ml-3" id="taskName" name="taskName"
                   placeholder="Add task name" required>
            <input type="text" class="form-control col-sm-3 ml-3" id="taskDescription" name="taskDescription"
                   placeholder="Set description" required>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit" class="btn btn-primary ml-3">Add Task</button>
        </form>
    </div>
    <div class="card-columns">
        <#list tasks as task>
            <div class="card  mt-2" style="width: 18rem;">
                <div class="card-body">
                    <h5 class="card-title"><label>Task Name :</label> ${task.taskName}</h5>
                    <h5 class="card-title"><label>Task Author : </label>${task.autorName}</h5>
                    <h5 class="card-title"><label>Task Status : </label> ${task.taskStatus}</h5>
                    <h5 class="card-text"><label>Task Description : </label> ${task.taskDescription} </h5>
                    <h5 class=<label>Cr. Date : </label> ${task.taskCreateDate}</h5>
                    <h5 class="card-title "><label>Update Date : </label> ${task.taskUpdateDate}</h5>
                    <a href="/editTask/${task.id}" class="btn btn-primary">edit task</a>
                </div>
            </div>
        <#else >
            No Task
        </#list>
    </div>
</@c.page>
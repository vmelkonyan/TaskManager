<#import "parts/common.ftlh" as c>
<#include "parts/security.ftlh">
<@c.page>
    <form action="/editTask" method="post">


        <div class="form-group mt-3">
            <label for="exampleInputEmail1">Task Name</label>
            <input type="text" class="form-control col-sm-3" value="${task.taskName}" name="taskName" required>
        </div>
        <div class="form-group mt-3">
            <label for="exampleInputEmail1">Task description</label>
            <input type="text" class="form-control col-sm-3" value="${task.taskDescription}" name="taskDescription">
        </div>

        <input type="hidden" value="${task.id}" name="taskId">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>

        <div class="form-group mt-2">
            <label class="col-form-label"> Task Status </label>
            <select class="custom-select  col-sm-3" name="taskStatus">
                <#list taskStatuss as taskStatus>
                    <#if task.taskStatus == taskStatus>
                        <option selected value="${taskStatus}" name="${taskStatus}">${taskStatus}</option>
                    <#else >
                        <option value="${taskStatus}" name="${taskStatus}">${taskStatus}</option>
                    </#if>
                </#list>
            </select>
        </div>
        <#if isManager>
            <div class="form-group mt-2">
                <label> Task Assigned </label>
                <select class="custom-select col-sm-3" name="taskOwner">
                    <#list usersList as user>
                        <#if task.assigneeName == user.username>
                        <option selected value="${user.username}" name="${user.username}">${user.username}</option>
                        <#else >
                         <option value="${user.username}" name="${user.username}">${user.username}</option>
                        </#if>
                    </#list>
                </select>
            </div>
        </#if>
        <button type="submit" class="btn btn-primary">Save Changes</button>

    </form>
</@c.page>
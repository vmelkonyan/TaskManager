<#import "parts/common.ftlh" as c>
<#include "parts/security.ftlh">
<@c.page>
    <form action="/editTask" method="post">
        <input type="text" value="${task.taskName}" name="taskName">
        <input type="text" value="${task.taskDescription}" name="taskDescription">
        <input type="hidden" value="${task.id}" name="taskId">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <div class="input-group mt-3">
            <label class="col-sm-2 col-form-label"> Task Status </label>
            <select
                    class="custom-select"  name="taskStatus">
                <#list taskStatuss as taskStatus>
                    <option value="${taskStatus}" name="${taskStatus}">${taskStatus}</option>
                </#list>
                <option selected>${task.taskStatus}</option>
            </select>
        </div>
        <#if isManager>
            <div class="input-group mt-3" >
                <label class="col-sm-2 col-form-label"> Task Assigned </label>
                <select

                        class="custom-select"  name="taskOwner">
                    <#list usersList as user>
                        <option value="${user.username}" name="${user.username}">${user.username}</option>
                    </#list>
                    <option selected>${task.author.username}</option>
                </select>
            </div>
        </#if>
        <button type="submit">Update</button>
    </form>
</@c.page>
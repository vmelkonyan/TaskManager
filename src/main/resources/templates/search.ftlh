<#import "parts/common.ftlh" as c>
<@c.page>
<div>
    <form method="post" class="form-row">

        <input class="form-control mr-sm-2" type="search" id="filter" name="filter" placeholder="Search by task name" value="${filter?ifExists}" required aria-label="Search">
        <input class="form-control mr-sm-2" type="assigned" id="assigned" name="assigned" placeholder="Search by Assigned" required aria-label="Search">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>

        <input type="text" class="form-control col-sm-3 ml-3" id="taskName" name="taskName"
               placeholder="Add task name" required>
        <input type="text" class="form-control col-sm-3 ml-3" id="taskDescription" name="taskDescription"
               placeholder="Set description" required>

        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit" class="btn btn-primary ml-3">Search</button>
    </form>
</div>
</@c.page>
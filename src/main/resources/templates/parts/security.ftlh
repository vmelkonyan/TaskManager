<#assign
known = Session.SPRING_SECURITY_CONTEXT?? >
<#if known>
    <#assign
    user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
    name = user.getUsername()
    isManager = user.isManager()>
    <#else >
        <#assign
    name = "Unknown"
    isManager = false>
</#if>
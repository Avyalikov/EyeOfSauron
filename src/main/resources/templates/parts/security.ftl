<#assign
    known = Session.SPRING_SECURITY_CONTEXT??
>

<#if known>
    <#assign
        user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
        name = user.getFullName()
        isAdmin = user.getAuthorities()?seq_contains('ADMIN')
        isRegistry = user.getAuthorities()?seq_contains('REGISTRY')
        isUser = user.getAuthorities()?seq_contains('USER')
    >
<#else>
    <#assign
        name = "Инкогнито"
        isAdmin = false
        isRegistry = false
        isUser = false
    >
</#if>
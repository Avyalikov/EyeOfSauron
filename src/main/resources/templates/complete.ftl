<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">

<@c.page>
<#if isUser>
<h5><div class="mb-1">Сообщение отправлено!</div></h5>
</#if>
<a class="btn btn-success" href="/" role="button">Вернуться на главную</a>
</@c.page>
<#include "parts/security.ftl">
<#import "parts/common.ftl" as c>

<@c.page>
<div class="py-5 text-center">
    <h3><div class="mb-1">Список пользователей</div></h3>
    <img src="https://img.icons8.com/cotton/80/000000/business-group.png"/>
</div>
<table class="table table-bordered">
    <thead>
    <tr>
        <th>Логин</th>
        <th>Роли пользователя</th>
        <th>Полное имя</th>
        <th>ID семьи</th>
        <#if isAdmin><th></th></#if>
    </tr>
    </thead>
    <tbody>
    <#list users as user>
        <tr>
            <td>${user.username}</td>
            <td><#list user.roles as role>${role}<#sep>, </#list></td>
            <td>${user.fullName}</td>
            <td>${user.familyId}</td>
            <#if isAdmin><td><a class="btn btn-success" href="/user/${user.id}">Изменить роль</a></td></#if>
        </tr>
    </#list>
    </tbody>
</table>
</@c.page>
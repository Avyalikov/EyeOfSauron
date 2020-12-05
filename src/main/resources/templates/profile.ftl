<#include "parts/security.ftl">
<#import "parts/common.ftl" as c>

<@c.page>
<div class="py-5 text-center">
<h3><div class="mb-5">Личный кабинет пользователя</div></h3>
<h5><div class="mt-1">${fullName}</div></h5>
<img src="https://img.icons8.com/ios-filled/80/000000/couple-man-woman.png"/>
<form method="post">
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Пароль:</label>
        <div class="col-sm-6">
            <input type="password" name="password" class="form-control" placeholder="Пароль" />
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Электронная почта:</label>
        <div class="col-sm-6">
            <input type="email" name="email" class="form-control" placeholder="email@example.com" value="${email!''}" />
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">ФИО:</label>
        <div class="col-sm-6">
            <input type="text" name="fullName" class="form-control" placeholder="Иванов Иван Иванович" value="${fullName!''}" />
        </div>
    </div>
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <button class="btn btn-success" type="submit">Сохранить изменения</button>
</form>
<#if isRegistry>
<div class="form-group row">
    <a class="btn btn-success" href="/divorce" role="button">Оформить развод</a>
    <a class="btn btn-success" href="/marry" role="button">Оформить брак</a>
</div>
</#if>
<#if isUser>
<a class="btn btn-success" href="/archives" role="button">Посмотреть архив</a>
</#if>
</div>
</@c.page>
<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
<div class="py-5 text-center">
    <h3><div class="mb-1">Регистрация</div></h3>
    <img src="https://img.icons8.com/ios/80/000000/add-user-male.png"/>
</div>
${message?ifExists}
<@l.login "/registration" true />
</@c.page>
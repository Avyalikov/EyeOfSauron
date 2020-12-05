<#import "parts/common.ftl" as c>

<@c.page>
${timeError?ifExists}
${familyError?ifExists}
<table class="table table-striped table-bordered">
    <thead>
    <tr>
        <th>Имя отправителя</th>
        <th>Сообщения</th>
        <th>Время отправки</th>
        <th>Последнее время</th>
    </tr>
    </thead>
    <tbody>
    <#list message as msg>
        <tr>
            <td>${msg.userName}</td>
            <td>${msg.message}</td>
            <td>${msg.messageTime}</td>
            <td>${lmt}</td>
        </tr>
    </#list>
    </tbody>
</table>
<form action="${"/messages"}" method="post">
    <div class="py-5 text-center">
        <textarea name="message" class="form-control" rows="5" placeholder="Введите свое сообщение"></textarea>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button class="btn btn-success" type="submit">Отправить</button>
    </div>
</form>
</@c.page>
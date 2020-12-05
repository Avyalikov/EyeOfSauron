<#import "parts/common.ftl" as c>

<@c.page>
${error?ifExists}
<form action="${"/marry"}" method="post">
    <div class="py-5 text-center">
        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> Номер паспорта 1го супруга:</label>
            <div class="col-sm-6">
                <input type="text" name="username1" class="form-control" placeholder="Введите номер паспорта" />
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> Номер паспорта 2го супруга:</label>
            <div class="col-sm-6">
                <input type="text" name="username2" class="form-control" placeholder="Введите номер паспорта" />
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> ID будущей семьи:</label>
            <div class="col-sm-6">
                <input type="text" name="familyId" class="form-control" placeholder="Введите ID семьи" />
            </div>
        </div>
    </div>
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <button class="btn btn-success" type="submit">Зарегистрировать брак</button>
</form>
</@c.page>
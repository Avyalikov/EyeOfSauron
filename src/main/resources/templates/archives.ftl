<#import "parts/common.ftl" as c>

<@c.page>
<table class="table table-striped table-bordered">
    <thead>
    <tr>
        <th>ID семьи</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <#list archive as arch>
        <tr>
            <td>${arch.familyId}</td>
            <td><a class="btn btn-success" href="/archives/${arch.archiveFile}" role="button">Скачать</a></td>
        </tr>
    </#list>
    </tbody>
</table>
</@c.page>
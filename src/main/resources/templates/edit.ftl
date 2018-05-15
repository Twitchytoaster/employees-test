<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body onload="loadDepartmentNames()">
    <div class="edit-form">
        <input type="text" placeholder="EmpName" class="add-block-input" id="empName" value="${employee.name}">

        <#if employee.active>
            <input type="checkbox" class="add-block-checkbox" id="isActive" checked>
        <#else>
            <input type="checkbox" class="add-block-checkbox" id="isActive">
        </#if>

        <select class="add-block-select" id="departmentSelect">
        </select>

        <input type="hidden" value="${employee.id}" id="empId">

        <button class="add-block-saveBtn" onclick="updateEmployee()">Save</button>
        <button onclick="javascript:history.back(1)">Back</button>
    </div>
    <script src="/js/script.js"></script>
</body>
</html>
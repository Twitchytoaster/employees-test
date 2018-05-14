<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <link rel="stylesheet" href="/css/styles.css">
</head>
<body onload="loadDepartmentNames()">
<div class="container">
    <div class="table-container">
        <body>
        <table>
            <tr>
                <th></th>
                <th></th>
                <th>empId</th>
                <th>empName</th>
                <th>empActive</th>
                <th>emdDepartment</th>
                <th></th>
            </tr>
        <#list employees as employee>
            <tr>
                <td><span class="view-employee" empId="${employee.id}"><a href="/employees/view?empId=${employee.id}">View</span>
                </td>
                <td><span class="edit-employee" empId="${employee.id}"><a href="/employees/update?empId=${employee.id}">Edit</a></span>
                </td>
                <td>${employee.id}</td>
                <td>${employee.name}</td>
                <td>${employee.active?c}</td>
                <td>${employee.departmentName}</td>
                <td><span class="delete-employee" empId="${employee.id}"
                          onclick="deleteEmployee(${employee.id})">Delete</span></td>
            </tr>
        </#list>

        </table>
        </body>
    </div>

    <div class="pagination">

    <#if leftDots>
        <div class="page-button">...</div>
    </#if>

    <#list pages as page>
        <div class="page-button"><a href="/employees?page=${page}">${page}<a/></div>
    </#list>

    <#if rightDots>
        <div class="page-button">...</div>
    </#if>
    </div>

    <div class="add-block">
        <input type="text" placeholder="EmpName" class="add-block-input" id="empName">

        <input type="checkbox" class="add-block-checkbox" id="isActive">

        <select class="add-block-select" id="departmentSelect"></select>

        <button class="add-block-saveBtn" onclick="sendNewEmployee()">Save</button>
    </div>
</div>

<script src="/js/script.js"></script>
</body>
</html>
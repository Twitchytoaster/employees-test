function sendNewEmployee() {
    var empName = document.querySelector("#empName").value;
    var isActive = document.querySelector("#isActive").checked;
    var options = document.querySelector("#departmentSelect");
    var selectedOption = options.options[options.selectedIndex].value;

    var http = new XMLHttpRequest();

    var url = "http://localhost:8080/employees/insert";

    console.log(options);
    http.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            window.location.href = "http://localhost:8080/employees";
        }
    };

    http.open("POST", url, true);
    http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    http.send("empName=" + empName + "&isActive=" + isActive + "&departmentName=" + selectedOption);
}

function deleteEmployee(id) {
    var http = new XMLHttpRequest();
    var url = "http://localhost:8080/employees/delete";

    http.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            window.location.href = "http://localhost:8080/employees";
        }
    };

    http.open("POST", url, true);
    http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    http.send("empId=" + id);
}

function updateEmployee() {
    var empName = document.querySelector("#empName").value;
    var isActive = document.querySelector("#isActive").checked;
    var options = document.querySelector("#departmentSelect");
    var empId = document.querySelector("#empId").value;
    var selectedOption = options.options[options.selectedIndex].value;

    var http = new XMLHttpRequest();

    var url = "http://localhost:8080/employees/update";

    console.log(options);
    http.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            window.location.href = "http://localhost:8080/employees";
        }
    };

    http.open("POST", url, true);
    http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    http.send("empName=" + empName + "&active=" + isActive + "&departmentName=" + selectedOption + "&empId=" + empId);
}

function loadDepartmentNames() {
    var http = new XMLHttpRequest();
    var url = "http://localhost:8080/employees/departments";

    var select = document.querySelector(".add-block-select");

    http.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            var departmentNames = JSON.parse(this.responseText);
            for (var i = 0; i < departmentNames.length; i++) {
                var option = document.createElement("option");
                option.value = departmentNames[i].name;
                option.innerHTML = departmentNames[i].name;

                select.appendChild(option);
            }
        }
    };

    http.open("GET", url, true);
    http.send();
}
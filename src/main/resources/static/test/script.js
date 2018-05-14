function sendNewEmployee() {
    var empName = document.querySelector("#empName").value;
    var isActive = document.querySelector("#isActive").checked;
    var options = document.querySelector("#departmentName");
    var selectedOption = options.options[options.selectedIndex].value;


    var http = new XMLHttpRequest();

    var url = "http://localhost:8080/employees/insert";

    http.onreadystatechange = function() {
        if(this.readyState === 4 && this.status === 200) {
            console.log("sooqa");
        }
    };

    http.open("POST", url, true);
    http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    http.send("empName=" + empName + "&isActive=" + isActive + "&departmentName=" + selectedOption);
}
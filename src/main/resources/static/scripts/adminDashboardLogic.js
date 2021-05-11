function transactionsHistoryButton(){
    unselectAll();
    document.getElementById("trans-hist-button").className = "selected";
    fetch('/contents/adminDashboardContents/transHistoryContent.html')
        .then(data => data.text())
        .then(html => document.getElementById('main-content').innerHTML = html);
}

function allocateButton(){
    unselectAll();
    document.getElementById("allocate-button").className = "selected";
    fetch('/contents/adminDashboardContents/allocationContent.html')
        .then(data => data.text())
        .then(html => document.getElementById('main-content').innerHTML = html);
}

function empListButton(){
    unselectAll();
    document.getElementById("empl-list-button").className = "selected";
    fetch('/contents/adminDashboardContents/employeeListContent.html')
        .then(data => data.text())
        .then(html => document.getElementById('main-content').innerHTML = html);
}

function unselectAll() {
    document.getElementById("dash-button").className = "";
    document.getElementById("trans-hist-button").className = "";
    document.getElementById("allocate-button").className = "";
    document.getElementById("empl-list-button").className = "";
}
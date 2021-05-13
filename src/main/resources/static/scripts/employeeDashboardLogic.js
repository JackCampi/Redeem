function transactionsHistoryButton(){
    unselectAll();
    document.getElementById("trans-hist-button").className = "selected";
    fetch('/contents/employeeDashboardContents/transHistoryContent.html')
        .then(data => data.text())
        .then(html => document.getElementById('main-content').innerHTML = html);
}

function transferButton(){
    unselectAll();
    document.getElementById("transfer-button").className = "selected";
    fetch('/contents/employeeDashboardContents/transferContent.html')
        .then(data => data.text())
        .then(html => document.getElementById('main-content').innerHTML = html);
}

function unselectAll() {
    document.getElementById("dash-button").className = "";
    document.getElementById("trans-hist-button").className = "";
    document.getElementById("transfer-button").className = "";
}

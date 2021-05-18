
var modal = document.getElementsByClassName("modal")[0];

var btn = document.getElementsByClassName("btn-submit")[0];

var accept = document.getElementsByClassName("acceptbtn")[0];

var cancel = document.getElementsByClassName("cancelbtn")[0];

var form = document.getElementById("doc-form");

var errorElement = document.getElementById('error');

var beneficiary = document.getElementById("beneficiary");

var amount = document.getElementById("amount");

btn.onclick = function(){
    modal.style.display="block";
    var benValue = beneficiary.value;
    var amValue = amount.value;
    var confText = document.getElementsByClassName("confirm")[0];
    confText.innerHTML = "Estás seguro de que deseas transferir "+amValue+" a "+benValue+"??";
}


form.addEventListener('submit', (e) => {
    let messages = []


    if (messages.length > 0){

        errorElement.innerText = messages.join('/')
        modal.style.display = "none";
        e.preventDefault()
    }else{
        modal.style.display = "none";
        form.submit();
    }
})


cancel.onclick = function(){
    modal.style.display="none";
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}



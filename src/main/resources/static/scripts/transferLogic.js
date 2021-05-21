
var modal = document.getElementsByClassName("modal")[0];

var btn = document.getElementsByClassName("btn-submit")[0];

var accept = document.getElementsByClassName("acceptbtn")[0];

var cancel = document.getElementsByClassName("cancelbtn")[0];

var form = document.getElementById("doc-form");

var beneficiary = document.getElementById("beneficiary");

var amount = document.getElementById("amount");

var invalidUser = document.getElementById("email_error");

btn.onclick = function(){
    modal.style.display="block";
    var benValue = beneficiary.value;
    var amValue = amount.value;
    var confText = document.getElementsByClassName("confirm")[0];
    confText.innerHTML = "Estás seguro de que deseas transferir "+amValue+" a "+benValue+"??";
}


form.addEventListener('submit', (e) => {

    if (!(/^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/.test(beneficiary.value))){
        beneficiary.style.border = '1px solid firebrick';
        beneficiary.focus();
        modal.style.display = "none";
        invalidUser.style.display = "block";
        invalidUser.style.color = "firebrick";
        e.preventDefault()
    }else{
        modal.style.display = "none";
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




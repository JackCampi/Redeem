
var modal = document.getElementsByClassName("modal")[0];

var btn = document.getElementsByClassName("btn-submit")[0];

var accept = document.getElementsByClassName("acceptbtn")[0];

var cancel = document.getElementsByClassName("cancelbtn")[0];

var form = document.getElementById("doc-form");

var beneficiary = document.getElementById("beneficiary");

var amount = document.getElementById("amount");

var invalidUser = document.getElementById("email_error");

var invalidAmount = document.getElementById("amount_error");

var benValue;
var amountValue;
btn.onclick = function(){
    benValue = beneficiary.value;
    amountValue = amount.value;
    invalidUser.style.display = "none";
    invalidAmount.style.display = "none";

    if(benValue.length==0 || amount.length==0){
        invalidAmount.innerText = "Debe rellenar todos los campos"
        amount.style.border = '1px solid firebrick';
        beneficiary.style.border = '1px solid firebrick';
        amount.focus();
        modal.style.display = "none";
        invalidAmount.style.display = "block";
        invalidAmount.style.color = "firebrick";
    }else if (!(/^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/.test(benValue)) && !(/^\d+$/.test(benValue))){
        beneficiary.style.border = '1px solid firebrick';
        beneficiary.focus();
        modal.style.display = "none";
        invalidUser.innerText = "Usuario no válido"
        invalidUser.style.display = "block";
        invalidUser.style.color = "firebrick";

    }else if(parseInt(amountValue) < 10000){
        invalidAmount.innerText = "La cantidad mínima que se puede transferir es $10.000";
        amount.style.border = '1px solid firebrick';
        amount.focus();
        modal.style.display = "none";
        invalidAmount.style.display = "block";
        invalidAmount.style.color = "firebrick";
    }else if(parseInt(amountValue) > 10000000){
        invalidAmount.innerText = "La cantidad máxima que se puede transferir es $10'000.000"
        amount.style.border = '1px solid firebrick';
        amount.focus();
        modal.style.display = "none";
        invalidAmount.style.display = "block";
        invalidAmount.style.color = "firebrick";
    }else if(!(/^\d+$/.test(amountValue))){
        invalidAmount.innerText = "Se deben ingresar solo valores numéricos";
        amount.style.border = '1px solid firebrick';
        amount.focus();
        modal.style.display = "none";
        invalidAmount.style.display = "block";
        invalidAmount.style.color = "firebrick";
    } else{
        modal.style.display="block";
        var benValue = beneficiary.value;
        var amValue = amount.value;
        var confText = document.getElementsByClassName("confirm")[0];
        confText.innerHTML = "Estás seguro de que deseas transferir $"+amValue+" a "+benValue+"??";
    }
}


form.addEventListener('submit', (e) => {
    modal.style.display = "none";
})


cancel.onclick = function(){
    modal.style.display="none";
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target === modal) {
        modal.style.display = "none";
    }
}




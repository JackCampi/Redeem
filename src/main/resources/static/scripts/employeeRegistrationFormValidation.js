const email = document.forms['reg-form']['email'];
const area = document.forms['reg-form']['area'];
const password = document.forms['reg-form']['password'];
const password_confirm = document.forms['reg-form']['password_confirm'];

const form = document.getElementById('reg-form');
const email_error = document.getElementById('email_error');
const password_error = document.getElementById('pass_error');
const password_confirm_error = document.getElementById('password_confirm_error');

setTimeout(function() { 
    document.getElementById("reg-success").style.display = "none";
  }, 4500);

form.addEventListener( 'submit', (event) => {
    password_confirm.style.border = 'none';
    password.style.border = 'none';
    email.style.border = 'none';
    password_confirm_error.style.display = "none";
    email_error.style.display = "none";
    password_error.style.display = "none";

    if (!(/^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/.test(email.value))){
        email.style.border = '1px solid firebrick';
        email_error.style.display = "block";
        email.focus();
        event.preventDefault();
        return false;
    }

    if (!(/(?=.{8,20}$)(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*\W).*/.test(password.value))){ 
        password.style.border = '1px solid firebrick';
        password_error.style.display = "block";
        password.focus();
        event.preventDefault();
        return false;
    }

    if(!(password.value == password_confirm.value)){
        password_confirm.style.border = '1px solid firebrick';
        password_confirm_error.style.display = "block";
        password_confirm.focus();
        event.preventDefault();
        return false;
    }

    if(password.value == "Gerencia (Admin)"){
        if(confirm("Atención: al registrar a un empleado en el área de Gerencia, le está concediendo los permisos de administrados.\n ¿Desea Continuar?")){
            return true;
        } else {
            event.preventDefault();
        } 
    }
        

})
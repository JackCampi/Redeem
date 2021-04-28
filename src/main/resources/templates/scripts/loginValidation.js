var email = document.forms['login-form']['email'];

var email_error2 = document.getElementById('email_error2');

function validation(){

    if (/^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/.test(email.value)){
        return true;
    } else{
        email.style.border = '1px solid firebrick';
        email_error2.style.display = "block";
        email.focus();
        return false;
    }

}
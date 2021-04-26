var email = document.forms['login-form']['email'];
var password = document.forms['login-form']['password'];

var email_error1 = document.getElementById('email_error1');
var email_error2 = document.getElementById('email_error2');
var pass_error = document.getElementById('pass_id');

email.addEventListener('textInput', email_verify);
password.addEventListener('textInput', pass_verify);

function validation(){
    if (email.value.length <= 0){
        email.style.border = '1px solid firebrick';
        email_error1.style.display = "block";
        email.focus();
        return false;
    }
    if (password.value.length <= 0){
        password.style.border = '1px solid firebrick';
        pass_error.style.display = "block";
        password.focus();
        return false;
    }
    
    if (/^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/.test(email.value)){
        return true;
    } else{
        email.style.border = '1px solid firebrick';
        email_error2.style.display = "block";
        email.focus();
        return false;
    }

    //Pasword Regex: (?=.{8,20}$)(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*\W).*
}

function email_verify(){
    if (email.value.length > 0){
        email.style.border = '1px solid grey';
        email_error1.style.display = "none";
        email_error2.style.display = "none";
        return true;
    }
}

function pass_verify(){
    if (password.value.length > 0){
        password.style.border = '1px solid grey';
        pass_error.style.display = "none";
        return true;
    }
}
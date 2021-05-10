
var email = document.forms['reg-form']['email'];
var password = document.forms['reg-form']['password'];
var password_confirm = document.forms['reg-form']['password_confirm'];


var email_error = document.getElementById('email_error');
var password_error = document.getElementById('pass_error');
var password_confirm_error = document.getElementById('password_confirm_error');


function validation(){
    password_confirm.style.border = 'none';
    password.style.border = 'none';
    email.style.border = 'none';
    password_confirm_error.style.display = "none";
    email_error.style.display = "none";
    password_error.style.display = "none";
    if (/^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/.test(email.value)){
        if (/(?=.{8,20}$)(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*\W).*/.test(password.value)){ 
            if(password.value == password_confirm.value){
            } else {
                password_confirm.style.border = '1px solid firebrick';
                password_confirm_error.style.display = "block";
                password_confirm.focus();
                return false;
            }
        } else {
            password.style.border = '1px solid firebrick';
            password_error.style.display = "block";
            password.focus();
            return false;
        }
    } else{
        email.style.border = '1px solid firebrick';
        email_error.style.display = "block";
        email.focus();
        return false;
    }
}
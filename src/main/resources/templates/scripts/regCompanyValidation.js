var companyName = document.forms['login-form']['companyName']

var name_error1 = doccument.getElementById('name_error1')

function validation(){
    if (companyName.value.length <= 0){
        companyName.style.border = '1px solid firebrick';
        name_error1.style.display = "block";
        companyName.focus();
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
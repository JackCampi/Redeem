function changePasswordFields() {
	var item1 = document.getElementById("item1");
	var item2 = document.getElementById("item2");
	var item3 = document.getElementById("item3");
	var oldPassword = document.getElementById("oldPassword");
	var newPassword = document.getElementById("newPassword");
	var confirmPassword = document.getElementById("confirmPassword");

	var mainButton = document.getElementById("showInputs");
	var inputButtons = document.getElementById("inputButtons");

	item1.style.display = "block";
	item2.style.display = "block";
	item3.style.display = "block";
	oldPassword.style.display = "block";
	newPassword.style.display = "block";
	confirmPassword.style.display = "block";
	mainButton.style.display = "none";
	inputButtons.style.display = "block";
	
}

function testingMessage() {
    
     /* Métodos de interacción con el navegador
        alert();
        confirm();
        var value = prompt();
    */
    var valor = prompt('Esto no es un game');
    alert(valor);
}

function closeChangePassword() {
	var item1 = document.getElementById("item1");
	var item2 = document.getElementById("item2");
	var item3 = document.getElementById("item3");
	var oldPassword = document.getElementById("oldPassword");
	var newPassword = document.getElementById("newPassword");
	var confirmPassword = document.getElementById("confirmPassword");

	var mainButton = document.getElementById("showInputs");
	var inputButtons = document.getElementById("inputButtons");
	var cancelChange = document.getElementById("cancelChange");

	item1.style.display = "none";
	item2.style.display = "none";
	item3.style.display = "none";
	oldPassword.style.display = "none";
	newPassword.style.display = "none";
	confirmPassword.style.display = "none";
	mainButton.style.display = "block";
	inputButtons.style.display = "none";
	cancelChange.style.display = "none";
}
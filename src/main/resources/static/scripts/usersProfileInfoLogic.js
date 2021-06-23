const item1 = document.getElementById("item1");
const item2 = document.getElementById("item2");
const item3 = document.getElementById("item3");
const oldPassword = document.getElementById("oldPassword");
const newPassword = document.getElementById("newPassword");
const confirmPassword = document.getElementById("confirmPassword");

const mainButton = document.getElementById("showInputs");
const inputButtons = document.getElementById("inputButtons");

const form = document.getElementById("form1")
const error = document.getElementById("error-message")

form.addEventListener("submit", (e) => {
	newPassword.style.border = "none"
	oldPassword.style.border = "none"
	confirmPassword.style.border = "none"
	let messages = []

	if (/(?=.{8,20}$)(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*\W).*/.test(newPassword.value) === false) {
		messages.push("La nueva contraseña debe tener por lo menos un símbolo, una minúscula, una mayúscula y 8 caracteres.")
		newPassword.style.border = "1px solid firebrick"
		newPassword.value = ""
		confirmPassword.value = ""
		newPassword.focus()
	}

	if (newPassword.value !== confirmPassword.value) {
		messages.push("Las contraseñas no coinciden")
		newPassword.style.border = "1px solid firebrick"
		confirmPassword.style.border = "1px solid firebrick"
		confirmPassword.value = "";
	}

	if (messages.length > 0) {
		e.preventDefault()
		error.innerText = messages.join("\n ")
	}
})


function changePasswordFields() {
	item1.style.display = "block";
	item2.style.display = "block";
	item3.style.display = "block";
	oldPassword.style.display = "block";
	newPassword.style.display = "block";
	confirmPassword.style.display = "block";
	mainButton.style.display = "none";
	inputButtons.style.display = "block";

}


function closeChangePassword() {
	error.innerText = ""
	oldPassword.style.border = "none"
	newPassword.style.border = "none"
	confirmPassword.style.border = "none"

	item1.style.display = "none";
	item2.style.display = "none";
	item3.style.display = "none";
	oldPassword.style.display = "none";
	newPassword.style.display = "none";
	confirmPassword.style.display = "none";
	mainButton.style.display = "block";
	inputButtons.style.display = "none";
	oldPassword.value = "";
	newPassword.value = "";
	confirmPassword.value = "";
}
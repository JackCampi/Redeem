const editModalElement = document.getElementById("editModal");
const removeModalElement = document.getElementById("removeModal");
const form = document.getElementById('edit-form');
const error = document.getElementById("email-error");
const email = document.getElementById("email");

form.addEventListener( 'submit', (event) => {
  
  if (!(/^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/.test(email.value))){
      email.style.border = '1px solid firebrick';
      error.innerText = "Introduzca un correo válido.";
      email.focus();
      event.preventDefault();
      return false;
  }

  if(confirm("¿Confirma editar la información del empleado?")){
    return true;
  } else {
    event.preventDefault();
  }
});

function editModal(employee) {
  document.getElementById("id").value = employee.id;
  document.getElementById("name").value = employee.name;
  document.getElementById("lastName").value = employee.lastName;
  document.getElementById("email").value = employee.email;
  document.getElementById("tel").value = employee.cellphone;

  /* TODO: format the date and the area */
  document.getElementById("date").value = employee.birthday;
  document.getElementById("area").value = employee.area;
  editModalElement.style.display = "block";
}

function removeModal(employee) {
  document.getElementById("removeName").value = employee.name;
  document.getElementById("removeLastName").value = employee.lastName;
  document.getElementById("removeAmount").value = employee.balance;
  document.getElementById("removeId").value = employee.id;
  removeModalElement.style.display = "block";
}

function closeModal(){
  editModalElement.style.display = "none";
  removeModalElement.style.display = "none";
  document.getElementById("testxd").innerText = "jlaksdj";
  return false;
}

window.onclick = function(event) {
  if (event.target == editModalElement) {
    editModalElement.style.display = "none";
  }
  if (event.target == removeModalElement) {
    removeModalElement.style.display = "none";
  }
}


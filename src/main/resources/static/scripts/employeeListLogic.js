const editModalElement = document.getElementById("editModal");
const removeModalElement = document.getElementById("removeModal");
const form = document.getElementById('edit-form');
const error = document.getElementById("email-error");
const email = document.getElementById("email");

setTimeout(function() { 
  document.getElementById("success-message").style.display = "none";
  document.getElementById("error-message").style.display = "none";
}, 4500);

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

function editModal(id, name, lastName, email, cellphone, area, birthday) {
  document.getElementById("id").value = id;
  document.getElementById("name").value = name;
  document.getElementById("lastName").value = lastName;
  document.getElementById("old-email").value = email;
  document.getElementById("email").value = email;
  document.getElementById("tel").value = cellphone;
  document.getElementById("date").value = birthday;
  document.getElementById("area").value = area;
  editModalElement.style.display = "block";
}

function removeModal(id, name, lastName, balance) {
  document.getElementById("removeName").innerText = name;
  document.getElementById("removeLastName").innerText = lastName;
  document.getElementById("removeAmount").innerText = balance;
  document.getElementById("removeId").value = id;
  removeModalElement.style.display = "block";
}

function closeModal(){
  editModalElement.style.display = "none";
  removeModalElement.style.display = "none";
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


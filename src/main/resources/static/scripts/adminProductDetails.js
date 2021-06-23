const editModalElement = document.getElementById("editModal");
const removeModalElement = document.getElementById("removeModal");

function editModal() {
    editModalElement.style.display = "block";
}
  
function removeModal() {
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

function readURL(obj){
    document.getElementById("prod-img").src = obj.value;
}
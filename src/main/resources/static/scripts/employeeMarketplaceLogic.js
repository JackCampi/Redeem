const detailsModalElement = document.getElementById("details-modal");
const addOneModalElement = document.getElementById("add-one-modal");

function detailsModal() {
    detailsModalElement.style.display = "block";
}

function addOneModal() {
    addOneModalElement.style.display = "block";
}
  
function closeModal(){
    detailsModalElement.style.display = "none";
    addOneModalElement.style.display = "none";
    return false;
}
  
window.onclick = function(event) {
    if (event.target == detailsModalElement) {
        detailsModalElement.style.display = "none";
    }
    if (event.target == addOneModalElement) {
        addOneModalElement.style.display = "none";
    }
}

function addToCart(){
    alert(document.getElementById("quantity").value)
}

function increase(limit){
    var amount = document.getElementById("quantity").value;
    if (amount < limit){
        amount++;
        document.getElementById("quantity").value = amount;
    }
}

function decrease(){
    var amount = document.getElementById("quantity").value;
    if (amount > 0){
        amount--;
        document.getElementById("quantity").value = amount;
    }
}
  
  
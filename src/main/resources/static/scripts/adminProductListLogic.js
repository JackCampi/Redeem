const addProductModal = document.getElementById("addProductModal");

function addModal() {
    addProductModal.style.display = "block";
}
 
function closeModal(){
    addProductModal.style.display = "none";
    return false;
}
  
window.onclick = function(event) {
    if (event.target === addProductModal) {
        addProductModal.style.display = "none";
    }
}

function readURL(obj){
    document.getElementById("new-prod-img").src = obj.value;
}

function selectProduct(element){
    var newURL = window.location.protocol + "//" + window.location.host + window.location.pathname + "/details" + "?id=" + element.getAttribute('product-id');
    window.location = newURL;
}

function increase(){
    document.getElementById("quantity").value++;
}

function decrease(){
    var amount = document.getElementById("quantity").value;
    if (amount > 0){
        amount--;
        document.getElementById("quantity").value = amount;
    }
}
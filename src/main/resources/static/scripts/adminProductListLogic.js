const addProductModal = document.getElementById("addProductModal");

function addModal() {
    addProductModal.style.display = "block";
}
 
function closeModal(){
    addProductModal.style.display = "none";
    return false;
}
  
window.onclick = function(event) {
    if (event.target == addProductModal) {
        addProductModal.style.display = "none";
    }
}

function readURL(obj){
    document.getElementById("new-prod-img").src = obj.value;
}

function selectProduct(id){
    document.getElementById("product-id").vaule = id;
}

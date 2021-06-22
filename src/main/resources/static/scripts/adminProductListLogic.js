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

function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
            document.getElementById("new-prod-img").src = e.target.result;
        };

        reader.readAsDataURL(input.files[0]);
    }
}

function selectProduct(id){
    document.getElementById("product-id").vaule = id;
}

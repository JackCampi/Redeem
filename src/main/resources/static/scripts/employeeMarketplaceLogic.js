const detailsModalElement = document.getElementById("details-modal");
const addOneModalElement = document.getElementById("add-one-modal");

function detailsModal(element){
    loadElement(element);
    document.getElementById("quantity").value = 0;
    document.getElementById("product-type").innerText = element.getAttribute("product-type");
    document.getElementById("product-description").innerText = element.getAttribute("product-description");
    detailsModalElement.style.display = "block";
}

function addOneModal(element) {
    loadElement(element);
    document.getElementById("one-product-name").innerText = element.getAttribute("product-name");
    addOneModalElement.style.display = "block";
}

function loadElement(element){
    limit = parseInt(element.getAttribute("product-units"));
    productId = element.getAttribute("product-id");
    document.getElementById("product-img").src = element.getAttribute("product-imgURL");
    document.getElementById("product-name").innerText = element.getAttribute("product-name");
    document.getElementById("product-price").innerText = element.getAttribute("product-price");
    document.getElementById("product-units").innerText = element.getAttribute("product-units");
}
  
function closeModal(){
    detailsModalElement.style.display = "none";
    addOneModalElement.style.display = "none";
    document.getElementById("add-error-1").style.display = "none";
    document.getElementById("add-error-2").style.display = "none";
    return false;
}
  
window.onclick = function(event) {
    if (event.target == detailsModalElement || event.target == addOneModalElement) {
        closeModal();
    }
}

function addToCart(){
    var amount = parseInt(document.getElementById("quantity").value);
    addProcess(amount);
}

function addOneToCart(){
    addProcess(1);
}

function addProcess(amount){
    var cart = JSON.parse(window.localStorage.getItem('cart'));

    if(cart == null){
        cart = {};
    }

    if (cart[productId] != null) {
        amount += cart[productId]["quantity"];
    }

    if(amount > 10){
        document.getElementById("error-message-text").innerText = "No puedes añadir más de 10 productos";
        if (cart[productId] != null) {
            document.getElementById("error-message-text").innerText += " (Tienes: "+ cart[productId]["quantity"].toString() +")";
        }
        document.getElementById("error-message").style.display = "block";
        setTimeout(function() { 
            document.getElementById("error-message").style.display = "none";
         }, 4500);

    }else if (amount > limit){

        document.getElementById("error-message-text").innerText = "No puedes superar el límite en stock";
        if (cart[productId] != null) {
            document.getElementById("error-message-text").innerText += " (Tienes: "+ cart[productId]["quantity"].toString() +")";
        }
        
        document.getElementById("error-message").style.display = "block";
        setTimeout(function() { 
            document.getElementById("error-message").style.display = "none";
         }, 4500);
    } else {
        cart[productId] = {"name": document.getElementById("product-name").innerText,
                        "image" : document.getElementById("product-img").src,
                        "price" : document.getElementById("product-price").innerText,
                        "quantity" : amount};
        window.localStorage.setItem('cart', JSON.stringify(cart));
        closeModal();
        document.getElementById("success-message-text").innerText = "Ahora tienes "+ amount.toString() + " unidades en el carrito.";
        document.getElementById("success-message").style.display = "block";
        setTimeout(function() { 
            document.getElementById("success-message").style.display = "none";
         }, 4500);
    }

}

function increase(){
    var amount = document.getElementById("quantity").value;
    if (amount < limit && amount < 10){
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


  
  
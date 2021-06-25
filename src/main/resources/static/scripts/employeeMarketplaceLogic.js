const detailsModalElement = document.getElementById("details-modal");
const addOneModalElement = document.getElementById("add-one-modal");

function detailsModal(element){
    productId = element.getAttribute("product-id");
    document.getElementById("product-limit").setAttribute("limit", element.getAttribute("product-units"));
    document.getElementById("product-img").src = element.getAttribute("product-imgURL");
    document.getElementById("product-name").innerText = element.getAttribute("product-name");
    document.getElementById("product-type").innerText = element.getAttribute("product-type");
    document.getElementById("product-price").innerText = "$" + element.getAttribute("product-price");
    document.getElementById("product-units").innerText = element.getAttribute("product-units") + " unidades disponibles";
    document.getElementById("product-description").innerText = element.getAttribute("product-description");
    detailsModalElement.style.display = "block";
}

function addOneModal(element) {
    productId = element.getAttribute("product-id");
    document.getElementById("one-product-name").innerText = element.getAttribute("product-name");
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
    var amount = parseInt(document.getElementById("quantity").value)
    var cart = JSON.parse(window.localStorage.getItem('cart'));

    if(cart == null){
        cart = {};
    }

    if (cart[productId] != null) {
        amount += cart[productId]["quantity"];
    }

    if(amount > 10){
        alert("No se puede jaja");

    } else {
        cart[productId] = {"name": "nombre",
                        "image" : "url",
                        "price" : "1000000000",
                        "quantity" : amount};
        window.localStorage.setItem('cart', JSON.stringify(cart));
    }
    alert(JSON.stringify(cart));

}

function addOneToCart(){
    var amount = 1
    var cart = JSON.parse(window.localStorage.getItem('cart'));

    if(cart == null){
        cart = {};
    }
    
    if (cart[productId] != null) {
        amount += cart[productId];
    }

    if(amount > 10){
        alert("pues no mi ciela");
    } else {
            cart[productId] = amount;
            window.localStorage.setItem('cart', JSON.stringify(cart));
    }
    alert(JSON.stringify(cart));
}

function increase(limit){
    var amount = document.getElementById("quantity").value;
    if (amount < parseInt(limit)){
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
  
  
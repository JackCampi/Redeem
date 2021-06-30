/* SHOPPING CART LOGIC */
/* 1) El sistema toma la llave o key 'cart' del localStorage, en donde
       se encuentra el String que contiene la información del carrito.
   2) Dicho string es un JSON, por lo tanto lo convertimos a dicho formato.
   3) Mediante un ciclo for, obtendremos todos los elementos del JSON con los
      productos del carrito y los 'pusheamos' en un arreglo, del tal manera se
      puede acceder a dichos productos por medio de indexación.
   4)  Este arreglo se llevará a una lista Bootstrap del HTML, donde ya conocemos
*/


let tablaProducto = document.getElementById('items')
let template = document.getElementById('template-products').content
let templateFooter = document.getElementById('template-footer').content
let fragment = document.createDocumentFragment()
let footer = document.getElementById('footer')
let myArray = []
let Jotason = {}
let arrayFromJSON = []
let value = 0;
let productsAndQuantities = ""

// El evento DOMContentLoaded es disparado cuando el documento HTML ha sido completamente cargado y parseado
document.addEventListener('DOMContentLoaded', e => {
    refreshData(window.localStorage.getItem('cart'));
    createArrayfromJSON()
    asignValuestoSend()
    // refreshData("{}")
})

tablaProducto.addEventListener('click', e => {
    btnAction(e)
})

function refreshData(p) {
    Jotason = JSON.parse(p)

    for (var i in Jotason)
        myArray.push([i,Jotason[i]]);
    showCartElements()
}

function showCartElements() {
    tablaProducto.innerHTML = ''
    myArray.forEach(p => {
        template.querySelectorAll('td')[0].querySelector('img').setAttribute('src', p[1].image)
        template.querySelectorAll('td')[1].textContent = p[1].name
        template.querySelectorAll('td')[2].textContent = "$ " + p[1].price
        template.querySelectorAll('td')[3].textContent = p[1].quantity
        let total = parseInt(p[1].price)*parseInt(p[1].quantity)
        template.querySelectorAll('td')[4].textContent = "$ " + total
        template.querySelectorAll('button')[0].setAttribute('data-id', p[0])
        template.querySelectorAll('button')[1].setAttribute('data-id', p[0])
        template.querySelector('span').setAttribute('data-id', p[0])

        const clone = template.cloneNode(true)
        fragment.appendChild(clone)

    })
    tablaProducto.appendChild(fragment)
    showFooter()
}

function showFooter() {
    footer.innerHTML = ''
    if (myArray.length === 0){
        footer.innerHTML = '<th class="empty-cart-message" colspan="6"> ¡El carrito está vacío! Añade productos y empieza a redimir tus puntos</th>'
        return
    }

    let cantidad = 0
    let precioTotal = 0
    myArray.forEach(p => {
        cantidad = cantidad + p[1].quantity
        precioTotal = precioTotal + parseInt(p[1].price)*parseInt(p[1].quantity)
    })

    value = precioTotal
    console.log("Cantidades: " + cantidad.toString())
    console.log("Total compra: " + precioTotal.toString())

    templateFooter.querySelectorAll('td')[0].textContent = cantidad
    templateFooter.querySelector('span').textContent = precioTotal

    const clone = templateFooter.cloneNode(true)
    fragment.appendChild(clone)
    footer.appendChild(fragment)

}

function emptyShoppingCart() {
    alert('Borrando el carrito')
    tablaProducto.innerHTML = ''
    myArray = []
    createNewJSON()
    refreshData("{}")
}

function btnAction(e) {
    // console.log(e.target.classList.contains('aumentar-button'))
    if (e.target.classList.contains('aumentar-button')) {
        let index = myArray.findIndex(element => element[0] === e.target.dataset.id)
        if (myArray[index][1].quantity === 10) {
            alert('No se pueden agregar más de 10 unidades de un mismo producto')
        } else {
            myArray[index][1].quantity++
        }
    }

    if (e.target.classList.contains('disminuir-button')) {
        let index = myArray.findIndex(element => element[0] === e.target.dataset.id)
        if (myArray[index][1].quantity === 1) {
            myArray.splice(index, 1)
        } else {
            myArray[index][1].quantity--
        }
    }

    if (e.target.classList.contains('delete-element')) {
        let index = myArray.findIndex(element => element[0] === e.target.dataset.id)
        myArray.splice(index, 1)
    }

    createNewJSON()
    createArrayfromJSON()
    showCartElements()
}

function createNewJSON() {
    Jotason = {}
    myArray.forEach(p => {
        Jotason[p[0]] = p[1]
    })

    window.localStorage.setItem('cart', JSON.stringify(Jotason))
}

function createArrayfromJSON() {
    arrayFromJSON = []
    for(var i in Jotason)
        arrayFromJSON.push([parseInt(i), Jotason[i]['quantity']]);
    productsAndQuantities = arrayFromJSON.toString()
    console.log(arrayFromJSON.toString())
}

function asignValuestoSend() {
    let inputEmployeeIdSource = document.getElementById('employeeIdSource').innerText
    let inputEmployeeId = document.getElementById('employeeId').value
    let inputValue = document.getElementById('value').value
    let inputProductsAndQuantities = document.getElementById('productsAndQuantities').value

    inputEmployeeId = parseInt(inputEmployeeIdSource)
    inputValue = value
    inputProductsAndQuantities = productsAndQuantities
    console.log(inputEmployeeId)
    console.log(inputValue)
    console.log(inputProductsAndQuantities)

}
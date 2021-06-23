const error = document.getElementById("errorMessage")
const form1 = document.getElementById("form1")
const form2 = document.getElementById("form2")
const form3 = document.getElementById("form3")

// Used in: changeSelection()
const transferOption = document.getElementById("transferOption")

const empresaInput = document.getElementById("empresaInput")
const areaInput = document.getElementById("areaInput")
const individualInput = document.getElementById("individualInput")

// Used in: changeAreaSelection()
const mainElement = document.getElementById("areaSelection")

const element1 = document.getElementById("areaInput2")
const element2 = document.getElementById("moneyAmount2")
const element3 = document.getElementById("allocateButton")

// Input money labels
const moneyAmount = document.getElementById("moneyAmount")
const moneyAmount2 = document.getElementById("moneyAmount2")
const moneyAmount3 = document.getElementById("moneyAmount3")


form1.addEventListener('submit', (e) => {
    let messages = []

    if (moneyAmount.value.charAt(0) === '0' ){
        messages.push("Introduzca un valor que no empiece con 0")
        moneyAmount.style.border = '1px solid firebrick'
    }

    if (messages.length > 0){
        e.preventDefault()
        error.innerText = messages.join(', ')
    }
})

form2.addEventListener('submit', (e) => {
    let messages = []

    if (moneyAmount2.value.charAt(0) === '0' ){
        messages.push("Introduzca un valor que no empiece con 0")
        moneyAmount2.style.border = '1px solid firebrick'
    }

    if (messages.length > 0){
        e.preventDefault()
        error.innerText = messages.join(', ')
    }
})

form3.addEventListener('submit', (e) => {
    let messages = []

    if (moneyAmount3.value.charAt(0) === '0' ){
        messages.push("Introduzca un valor que no empiece con 0")
        moneyAmount3.style.border = '1px solid firebrick'
    }

    if (messages.length > 0){
        e.preventDefault()
        error.innerText = messages.join(', ')
    }
})


function changeSelection() {
    error.innerText = ""
    moneyAmount.style.border = "none"
    moneyAmount2.style.border = "none"
    moneyAmount3.style.border = "none"

    if (transferOption.value === "empresa"){
        empresaInput.style.display = "block"
        areaInput.style.display = "none"
        individualInput.style.display = "none"

    } else if (transferOption.value === "area"){
        empresaInput.style.display = "none"
        areaInput.style.display = "block"
        individualInput.style.display = "none"

    } else if (transferOption.value === "individual") {
        empresaInput.style.display = "none"
        areaInput.style.display = "none"
        individualInput.style.display = "block"
        
    } else {
        empresaInput.style.display = "none"
        areaInput.style.display = "none"
        individualInput.style.display = "none"
    }

}

function changeAreaSelection() {
    error.innerText = ""

    if (mainElement.value === "default") {
        element1.style.display = "none"
        element2.style.display = "none"
        element3.style.display = "none"
    } else {
        element1.style.display = "block"
        element2.style.display = "block"
        element3.style.display = "block"
    }
}


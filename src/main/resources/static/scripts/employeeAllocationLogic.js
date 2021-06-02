function changeSelection() {
    var transferOption = document.getElementById("transferOption");

    var empresaInput = document.getElementById("empresaInput");
    var areaInput = document.getElementById("areaInput");
    var individualInput = document.getElementById("individualInput");

    if (transferOption.value === "empresa"){
        empresaInput.style.display = "block";
        areaInput.style.display = "none";
        individualInput.style.display = "none";

    } else if (transferOption.value === "area"){
        empresaInput.style.display = "none";
        areaInput.style.display = "block";
        individualInput.style.display = "none";

    } else if (transferOption.value === "individual") {
        empresaInput.style.display = "none";
        areaInput.style.display = "none";
        individualInput.style.display = "block";
        
    } else {
        empresaInput.style.display = "none";
        areaInput.style.display = "none";
        individualInput.style.display = "none";
    }

}

function changeAreaSelection() {
    var mainElement = document.getElementById("areaSelection");

    var element1 = document.getElementById("areaInput2");
    var element2 = document.getElementById("moneyAmount2");
    var element3 = document.getElementById("allocateButton");

    if (mainElement.value === "default") {
        element1.style.display = "none";
        element2.style.display = "none";
        element3.style.display = "none";
    } else {
        element1.style.display = "block";
        element2.style.display = "block";
        element3.style.display = "block";
    }
}
const email = document.getElementById('adminEmail')
const password = document.getElementById('password')
const password2 = document.getElementById('PasswordCheck')
const form = document.getElementById('login-form')
const errorElement = document.getElementById('error')

form.addEventListener('submit', (e) => {
    let messages = []
    if (/^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/.test(email.value) === false) {
        messages.push('Introduzca un correo válido')
    }

    if (/(?=.{8,20}$)(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*\W).*/.test(password.value) === false) {
        messages.push('La contraseña debe por lo menos un símbolo, una minúscula, una mayúscula y 8 carácteres.')
    }

    if (password2.value !== password.value) {
        messages.push('Las contraseñas no son iguales')
    }

    if (messages.length > 0){
        e.preventDefault()
        errorElement.innerText = messages.join('/')

    }
})
const companyName = document.getElementById('companyName')
const nit = document.getElementById('nit')
const companyAreas = document.getElementById('companyAreas')
const form = document.getElementById('login-form')
const errorElement = document.getElementById('error')

form.addEventListener('submit', (e) => {
    let messages = []
    if (companyName.value === 'loco') {
        messages.push('loco no puede ser el nombre de tu empresa')
    }

    if (messages.length > 0){
        e.preventDefault()
        errorElement.innerText = messages.join(',')

    }
})
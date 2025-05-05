const emailInput = document.getElementById("email")
const passwordInput = document.getElementById("password")
const form = document.getElementById("form")

form.addEventListener("submit", handleLoginUser)

async function handleLoginUser(e){
    e.preventDefault()
    try{
        const loginUserData = {
            email: emailInput.value,
            password: passwordInput.value
        }

        const response = await fetch("/auth/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(loginUserData)
        })

        if(!response.ok){
            throw new Error()
        }

        const data = await response.json()
        console.log(data)
    }
    catch (error) {
        console.error(error)
    }
}
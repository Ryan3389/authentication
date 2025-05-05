const usernameInput = document.getElementById("username")
const emailInput = document.getElementById("email")
const passwordInput = document.getElementById("password")
const form = document.getElementById("form")

form.addEventListener("submit", handleFormSubmit)

async function handleFormSubmit(e){
    e.preventDefault()
    try{
        const userRegisterData = {
            username: usernameInput.value,
            email: emailInput.value,
            password: passwordInput.value
        }

        const response = await fetch("http://localhost:8080/auth/signup", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(userRegisterData)

        })

        if(!response.ok){
            throw new Error()
        }
        console.log("Success: data below: ")
        const data = await response.json()
        window.location.assign("/verify")
        console.log(data)
    }
    catch (error) {
        console.error("error")
    }
}


const emailInput = document.getElementById("email")
const verificationCodeInput = document.getElementById("verificationCode")
const form = document.getElementById("form")

form.addEventListener("submit", handleVerifyUser)

async function handleVerifyUser(e){
    e.preventDefault()
    console.log("Check point #1")
    try{
        const verifyUserData = {
            email: emailInput.value,
            verificationCode: verificationCodeInput.value
        }

        console.log("Check point #2 - before fetch request")
        const response = await fetch("http://localhost:8080/auth/verify", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(verifyUserData)

        })
        console.log("Check point #3 - after fetch request")

        if(!response.ok){
            throw new Error()
        }
        console.log("Success: data below: ")
        const data = await response.json()
        displayMessage(data)

    }
    catch (error) {
        console.error(error)
    }
}
function displayMessage(message){
    const paragraphElement = document.createElement("p")
    const text = document.createTextNode(message)
    paragraphElement.appendChild(text)
    document.body.appendChild(paragraphElement)
}
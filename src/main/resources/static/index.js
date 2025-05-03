const formData = new FormData

formData.append("username", "TestUser")
formData.append("email", "Ryan.Cuthbert12@gmail.com")
formData.append("password", "12345")

const userData = {
    username: "TestUser",
    email: "Ryan.Cuthbert12@gmail.com",
    password: "12345"

}


async function fetchData (){
    try{
        const response = await fetch("http://localhost:8080/auth/signup", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(userData)

        })
        if(!response.ok){
            throw new Error()
        }
        console.log("Success, data below: ")
        const data = await response.json()
        console.log(data)
    }
    catch (error) {
        console.error(error)
    }
}

fetchData()

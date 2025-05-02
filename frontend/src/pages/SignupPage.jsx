import { useState } from "react";
import SignupForm from "../components/SignupForm.jsx";
import Verify from "./Verify.jsx";
function SignupPage(){
    const [ pageLayout, setPageLayout ] = useState("form")
    const [ formState, setFormState ] = useState({
        username: null,
        email: null,
        password: null
    })

    const handleFormSubmit = async (e) => {
        e.preventDefault()
        try{
            const response = await fetch("/auth/signup", {
                method: "POST",
                headers: {
                  "Content-Type": "application/json"
                },
                body: JSON.stringify(formState)
            })

            if(!response.ok){
                throw new Error()
            }
            const data = await response.json()
            setPageLayout("verify")
            console.log(data)

        }
        catch (error) {
            console.error(error)
        }

    }
    return(
       <section className="form-section">
           { pageLayout === "form" ?
               <SignupForm
                   formSubmit={handleFormSubmit}
               /> :
               <Verify />}
       </section>
    )
}

export default SignupPage


// import { useState } from "react";
//
// function SignupPage(){
//     const [ pageLayout, setPageLayout ] = useState("form")
//     const [ formState, setFormState ] = useState({
//         username: null,
//         email: null,
//         password: null
//     })
//
//     const handleFormSubmit = async (e) => {
//         try{
//             const response = await fetch("/auth/signup", {
//                 method: "POST",
//                 headers: {
//                     "Content-Type": "application/json"
//                 },
//                 body: JSON.stringify(formState)
//             })
//
//             if(!response.ok){
//                 throw new Error()
//             }
//             const data = await response.json()
//             setPageLayout("verify")
//             console.log(data)
//
//         }
//         catch (error) {
//             console.error(error)
//         }
//
//     }
//     return(
//         <section className="form-section">
//             <h1>Create an Account</h1>
//             <form onSubmit={handleFormSubmit}>
//                 <div className="input-div">
//                     <label>Username</label>
//                     <input
//                         type="text"
//                         placeholder="John.Doe35"
//                     />
//                 </div>
//                 <div className="input-div">
//                     <label>Email</label>
//                     <input
//                         type="email"
//                         placeholder="John.Doe35@example.com"
//                     />
//                 </div>
//                 <div className="input-div">
//                     <label>Password</label>
//                     <input
//                         type="password"
//                     />
//                 </div>
//                 <input type="submit" value="Create Account"/>
//             </form>
//         </section>
//     )
// }
//
// export default SignupPage
function SignupPage(){
    return(
       <section className="form-section">
           <h1>Create an Account</h1>
           <form>
               <div className="input-div">
                   <label>Username</label>
                   <input
                       type="text"
                       placeholder="John.Doe35"
                   />
               </div>
               <div className="input-div">
                   <label>Email</label>
                   <input
                    type="email"
                    placeholder="John.Doe35@example.com"
                   />
               </div>
               <div className="input-div">
                   <label>Password</label>
                   <input
                    type="password"
                   />
               </div>
               <input type="submit" value="Create Account"/>
           </form>
       </section>
    )
}

export default SignupPage
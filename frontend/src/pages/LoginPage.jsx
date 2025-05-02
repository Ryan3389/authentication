function LoginPage(){
    return(
        <section className="form-section">
            <h1>Login</h1>
            <form>
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
                <input type="submit" value="Login"/>
            </form>
        </section>

    )

}

export default LoginPage
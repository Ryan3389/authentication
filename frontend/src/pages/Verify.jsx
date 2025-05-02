function Verify(){
    return(
        // <section className="form-section">
            <form>
                <div className="input-div">
                    <label>Email</label>
                    <input
                        type="email"
                        placeholder="John.Doe35@example.com"
                    />
                </div>
                <div className="input-div">
                    <label>Verification Code</label>
                    <input
                        type="text"
                        placeholder="12345"
                    />
                </div>
                <input type="submit" value="Verify Account"/>
            </form>
        // </section>
    )
}

export default Verify
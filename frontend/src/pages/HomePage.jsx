import { Link } from "react-router-dom"
function HomePage(){
    return(
        <section className="hero-section">
            <article className="hero-article">
                <h1>Welcome to Our App</h1>
                <p>This is the best decision of your life. Login or sign up to continue</p>
                   <Link to={"/login"} className="btn">Login</Link>
                   <Link to={"/signup"} className="btn">Sign Up</Link>
            </article>
        </section>
    )

}

export default HomePage
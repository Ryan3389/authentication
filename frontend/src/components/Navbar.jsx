import { Link } from "react-router-dom"
function Navbar(){
    return(
        <header>
            <nav>
                <p>Authentication</p>
                <span className="nav-links">
                    <Link to={"/login"} className="login-btn nav-btn">Login</Link>
                    <Link to={"/signup"} className="signup-btn nav-btn">Sign Up</Link>
                </span>
            </nav>
        </header>
    )
}

export default Navbar
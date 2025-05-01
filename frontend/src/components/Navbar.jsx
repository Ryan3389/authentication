import { Link } from "react-router-dom"
function Navbar(){
    return(
        <header>
            <nav>
                <p>Authentication</p>
                <span className="nav-links">
                    <Link to={"/login"}>Login</Link>
                    <Link to={"/signup"}>Sign Up</Link>
                </span>
            </nav>
        </header>
    )
}

export default Navbar
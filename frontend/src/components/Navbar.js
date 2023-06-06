//Navbar
import "../index.css"
import Login from '../pages/Login';




const Navbar = () => {

    const userName = Login.username;

    return (
        <header className="navbar">
            <section className="navbar-container">
                <div className="title_text">
                    <p>Project Filer</p> {userName + ': Welcome'}
                </div>
            </section>
        </header>
    );
};

export default Navbar


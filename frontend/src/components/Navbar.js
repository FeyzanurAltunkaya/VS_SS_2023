//Navbar
import { Link, useLocation} from 'react-router-dom'
import * as Icons from "react-icons/fa"
import "../index.css"
import Login from './Login';
import { useState } from 'react';




const Navbar = () => {

    const userName = Login.username;

    return (
        <header>
            <section className="navbar-container">
                <div className="title_text">
                    <p>Project Filer</p>
                </div>

            </section>
        </header>
    )
}
export default Navbar


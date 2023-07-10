import React, { Component } from "react";
import { Routes, Route, Link } from "react-router-dom";
import "./App.css";
import "./index.css"
import Home from "./pages/Home";
import UploadFiles from "./pages/UploadFiles";


class App extends Component {
    constructor(props) {
        super(props);

    }
    render() {


        return (
            <>

                    <nav className="navbar">

                        <div className="navbar-container">
                            <div>
                                <Link to={"/"} className="nav-link" >
                                    Home
                                </Link>
                            </div>

                            <div>
                            <a href="/upload" className="nav-link" >
                                MyFiles
                            </a>
                            </div>

                        </div>

                    </nav>






                <div className="container">
                    <Routes>

                        <Route path="/" element={<Home/>}/>

                        <Route path="/upload" element={<UploadFiles/>}/>


                    </Routes>
                </div>
            </>
        );
    }
}

export default App;
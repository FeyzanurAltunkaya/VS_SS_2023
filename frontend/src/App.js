import React, { useState } from 'react';
import { Routes, Route, useNavigate } from 'react-router-dom';
import FileUpload from './pages/FileUpload.js';
import Login from './pages/Login';
import Navbar from './components/Navbar';

const App = () => {
    const [loggedIn, setLoggedIn] = useState(false);
    const [username, setUsername] = useState('');
    const navigate = useNavigate();

    const handleLoginSuccess = (username) => {
        setLoggedIn(true);
        setUsername(username);
        navigate('/FileUpload');
    };

    return (
        <div>
            <Routes>
                <Route
                    path="/"
                    element={<Login onLoginSuccess={handleLoginSuccess} />}
                />
                {loggedIn && (
                    <Route
                        path="/FileUpload"
                        element={<FileUpload />}
                    />
                )}
            </Routes>
            {loggedIn && <Navbar />}
        </div>
    );
};

export default App;

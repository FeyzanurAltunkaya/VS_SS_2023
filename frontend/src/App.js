import React, { useState } from 'react';
import { BrowserRouter as Router, Routes, Route, useNavigate } from 'react-router-dom';
import FileUpload from './FileUpload';
import Login from './Login';

const App = () => {
    const [loggedIn, setLoggedIn] = useState(false);
    const navigate = useNavigate();

    const handleLoginSuccess = () => {
        setLoggedIn(true);
        navigate('/FileUpload');
    };

    return (
        <div>
            <Routes>
                <Route
                    path="/"
                    element={<Login onLoginSuccess={handleLoginSuccess} />}
                />
                <Route
                    path="/FileUpload"
                    element={<FileUpload />}
                />
            </Routes>
        </div>
    );
};

export default App;
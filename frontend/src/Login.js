import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

const Login = ({ onLoginSuccess }) => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();

    const handleUsernameChange = (e) => {
        setUsername(e.target.value);
    };

    const handlePasswordChange = (e) => {
        setPassword(e.target.value);
    };

    const handleFormSubmit = (e) => {
        e.preventDefault();

        // Hier kannst du die Logik für die Überprüfung des Benutzernamens und des Passworts einfügen
        if (username === 'admin' && password === 'password') {
            console.log('Erfolgreich eingeloggt!');
            onLoginSuccess();
            navigate('/FileUpload');
        } else {
            console.log('Ungültiger Benutzername oder Passwort!');
            // Füge hier weitere Aktionen hinzu, wie eine Fehlermeldung anzeigen
        }
    };

    return (
        <form onSubmit={handleFormSubmit}>
            <div>
                <label htmlFor="username">Benutzername:</label>
                <input
                    type="text"
                    id="username"
                    value={username}
                    onChange={handleUsernameChange}
                />
            </div>
            <div>
                <label htmlFor="password">Passwort:</label>
                <input
                    type="password"
                    id="password"
                    value={password}
                    onChange={handlePasswordChange}
                />
            </div>
            <button type="submit">Anmelden</button>
        </form>
    );
};

export default Login;

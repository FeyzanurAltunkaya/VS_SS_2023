import React, { useState } from 'react';

const FileUpload = () => {
    const [selectedFile, setSelectedFile] = useState(null);

    const handleFileChange = (event) => {
        setSelectedFile(event.target.files[0]);
    };

    const handleUpload = () => {
        if (selectedFile) {
            const formData = new FormData();
            formData.append('file', selectedFile);

            // Hier kannst du den Upload-Request an deinen Server senden
            // Verwende z.B. Fetch oder eine AJAX-Bibliothek wie Axios

            // Beispiel mit Fetch:
            fetch('/upload', {
                method: 'POST',
                body: formData
            })
                .then(response => {
                    // Handle die Serverantwort
                })
                .catch(error => {
                    // Handle den Fehler
                });
        }
    };

    return (
        <div>
            <input type="file" onChange={handleFileChange} />
            <button onClick={handleUpload}>Hochladen</button>
        </div>
    );
};

export default FileUpload;

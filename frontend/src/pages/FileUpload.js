import React, { useState, useEffect } from 'react';
const FileUpload = () => {
    const [selectedFiles, setSelectedFiles] = useState([]);
    const [uploadedFiles, setUploadedFiles] = useState([]);

    const fetchUploadedFiles = () => {
        fetch('http://localhost:8000/files')
            .then((response) => response.json())
            .then((data) => {
                setUploadedFiles(data.files);
            })
            .catch((error) => {
                console.error(error);
            });
    };

    useEffect(() => {
        // Funktion aufrufen, um die hochgeladenen Dateien abrufen zu können
        fetchUploadedFiles();
    }, []);
    const handleFileChange = (event) => {
        setSelectedFiles(Array.from(event.target.files));
    };

    const handleUpload = () => {
        if (selectedFiles.length > 0) {
            const formData = new FormData();
            selectedFiles.forEach((file) =>{
                formData.append('files', file);
            });
            // Hier den Upload-Request an den Server senden!!
            // z.B. Fetch oder eine AJAX-Bibliothek wie Axios

            // bsp mit Fetch:
            fetch('http://localhost:8000/upload', {
                method: 'POST',
                body: formData,
            })
                .then((response) => response.json())
                .then((data) => {
                    console.log(data); // Serverantwort
                    // Aktualisiere die Liste der hochgeladenen Dateien
                    fetchUploadedFiles();
                })
                .catch((error) => {
                    // Handle den Fehler
                    console.error(error);
                });
            }
        };
        const handleDelete = (fileName) => {
            fetch(`http://localhost:8000/delete/${fileName}`, {
                method: 'DELETE',
            })
                .then((response) => response.text())
                .then((data) => {
                    console.log(data); // Serverantwort
                    // Aktualisiere die Liste der hochgeladenen Dateien
                    fetchUploadedFiles();
                })
                .catch((error) => {
                    console.error(error);
                });

        };

    return (
        <div>
            <input
                type="file"
                onChange={handleFileChange}
                className="file-input"
                multiple
            />
            <button onClick={handleUpload} className="upload-button">
                Hochladen
            </button>
            <h2>Hochgeladene Dateien:</h2>
            <ul className="file-list">
                {uploadedFiles.map((file) => (
                    <li key={file} className="file-item">
                        {file}
                        <button onClick={() => handleDelete(file)}>Löschen</button>
                    </li>
                ))}
            </ul>
        </div>
    );

};

export default FileUpload;

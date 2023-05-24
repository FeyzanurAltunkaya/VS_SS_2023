import React, { useState, useEffect } from 'react';

const FileUpload = () => {
    const [selectedFile, setSelectedFile] = useState(null);
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
        // Hier kannst du eine Funktion aufrufen, um die hochgeladenen Dateien abzurufen
        fetchUploadedFiles();
    }, []);
    const handleFileChange = (event) => {
        setSelectedFile(event.target.files[0]);
    };

    const handleUpload = () => {
        if (selectedFile) {
            const formData = new FormData();
            formData.append('file', selectedFile);

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
                .then((response) => response.json())
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
                <input type="file" onChange={handleFileChange}/>
                <button onClick={handleUpload}>Hochladen</button>
                <h2>Hochgeladene Dateien:</h2>
                <ul>
                    {uploadedFiles.map((file) => (
                        <li key={file}>{file}
                            <button onClick={() => handleDelete(file)}>Löschen</button>
                        </li>
                    ))}
                </ul>
            </div>
        );
    };

export default FileUpload;

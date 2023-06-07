import React, { useState, useEffect } from 'react';
import { faUpload, faFolder } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
const FileUpload = () => {
    const [selectedFiles, setSelectedFiles] = useState([]);
    const [uploadedFiles, setUploadedFiles] = useState([]);
    const [folders, setFolders] = useState([]);
    const [activeFolder, setActiveFolder] = useState('');
    const [newFolderName, setNewFolderName] = useState('');

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

    const fetchFolders = () => {
        fetch('http://localhost:8000/folders')
            .then((response) => response.json())
            .then((data) => {
                setFolders(data.folders);
            })
            .catch((error) => {
                console.error(error);
            });
    };

    useEffect(() => {
        fetchUploadedFiles();
        fetchFolders();
    }, []);

    const handleFileChange = (event) => {
        setSelectedFiles(Array.from(event.target.files));
    };

    const handleUpload = () => {
        if (selectedFiles.length > 0) {
            const formData = new FormData();
            selectedFiles.forEach((file) => {
                formData.append('files', file);
                formData.append('folder', activeFolder);
            });

            fetch('http://localhost:8000/upload', {
                method: 'POST',
                body: formData,
            })
                .then((response) => response.text()) // Text statt JSON
                .then((data) => {
                    console.log(data); // Serverantwort als Text
                    fetchUploadedFiles();
                })
                .catch((error) => {
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
                fetchUploadedFiles();
            })
            .catch((error) => {
                console.error(error);
            });
    };

    const handleCreateFolder = () => {
        const folderName = prompt('Geben Sie den Namen des Ordners ein:');
        if (folderName) {
            fetch('http://localhost:8000/create-folder', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ folderName }),
            })
                .then((response) => response.text())
                .then((data) => {
                    console.log(data); // Serverantwort
                    fetchFolders();
                })
                .catch((error) => {
                    console.error(error);
                });
        }
    };


    const handleDragOver = (event) => {
        event.preventDefault();
    };

    const handleDrop = (event, folder) => {
        event.preventDefault();
        const files = Array.from(event.dataTransfer.files);
        const formData = new FormData();
        files.forEach((file) => {
            formData.append('files', file);
            formData.append('folder', folder);
        });

        fetch('http://localhost:8000/upload', {
            method: 'POST',
            body: formData,
        })
            .then((response) => response.json())
            .then((data) => {
                console.log(data); // Serverantwort
                fetchUploadedFiles();
            })
            .catch((error) => {
                console.error(error);
            });
    };

    const handleFolderClick = (folder) => {
        setActiveFolder(folder);
    };
    const handleNewFolderNameChange = (event) => {
        setNewFolderName(event.target.value);
    };
    const handleNewFolderSubmit = () => {
        if (newFolderName) {
            fetch('http://localhost:8000/create-folder', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ folderName: newFolderName }),
            })
                .then((response) => response.text())
                .then((data) => {
                    console.log(data); // Serverantwort
                    fetchFolders();
                    setNewFolderName('');
                })
                .catch((error) => {
                    console.error(error);
                });
        }
    };

    return (
        <div>
            <div className="your-directories">
                <input
                    type="file"
                    onChange={handleFileChange}
                    className="file-input-container"
                    multiple
                />
                <button onClick={handleUpload} className="upload-button">
                    <div className="upload-icon-container">
                        <FontAwesomeIcon icon={faUpload} />
                    </div>
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

            <div className="your-folders">
                <h2> Ordner: </h2>
                <ul className="folder-list">
                    {folders.map((folder) => (
                        <li
                            key={folder}
                            className={` folder-item ${
                                activeFolder === folder ? 'active' : ''
                            }`}
                            onClick={() => handleFolderClick(folder)}
                            onDragOver={handleDragOver}
                            onDrop={(event) => handleDrop(event, folder)}
                        >
                            <FontAwesomeIcon icon={faFolder} />
                            {folder}
                        </li>
                    ))}
                </ul>
                <div className="create-folder">
                    <input type="text" placeholder="Ordnername" value={newFolderName} onChange={handleNewFolderNameChange}/>
                    <button onClick={handleCreateFolder}>Ordner erstellen</button>
                </div>
            </div>
        </div>
    );
};

export default FileUpload;

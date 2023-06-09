import React, { useEffect, useState } from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faUpload } from "@fortawesome/free-solid-svg-icons";

const Directories = () => {
    const [selectedFiles, setSelectedFiles] = useState([]);
    const [uploadedFiles, setUploadedFiles] = useState([]);

    const fetchUploadedFiles = () => {
        fetch("http://localhost:8000/files")
            .then((response) => response.json())
            .then((data) => {
                setUploadedFiles(data.files);
            })
            .catch((error) => {
                console.error(error);
            });
    };

    useEffect(() => {
        fetchUploadedFiles();
    }, []);

    const handleFileChange = (event) => {
        setSelectedFiles(Array.from(event.target.files));
    };

    const handleUpload = () => {
        if (selectedFiles.length > 0) {
            const formData = new FormData();
            selectedFiles.forEach((file) => {
                formData.append("files", file);

            });

            fetch("http://localhost:8000/upload", {
                method: "POST",
                body: formData,
            })
                .then((response) => response.text())
                .then((data) => {
                    console.log(data);
                    fetchUploadedFiles();
                })
                .catch((error) => {
                    console.error(error);
                });
        }
    };
    const handleDelete = (fileName) => {
        fetch(`http://localhost:8000/delete/${fileName}`, {
            method: "DELETE",
        })
            .then((response) => response.text())
            .then((data) => {
                console.log(data);
                fetchUploadedFiles();
            })
            .catch((error) => {
                console.error(error);
            });
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
        </div>
    );
};

export default Directories;


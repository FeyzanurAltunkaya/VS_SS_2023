import React, { useState, useEffect } from "react";
import UploadFileService from "../services/UploadFileService";

const UploadFiles = () => {
    const [selectedFile, setSelectedFile] = useState(null);
    const [message, setMessage] = useState("");
    const [files, setFiles] = useState([]);

    useEffect(() => {
        fetchAllFiles();
    }, []);

    const handleFileChange = (event) => {
        setSelectedFile(event.target.files[0]);
    };

    const uploadFile = () => {
        if (selectedFile) {
            UploadFileService.upload(selectedFile)
                .then((response) => {
                    setMessage(response.data);
                    fetchAllFiles(); // Refresh file list
                })
                .catch((error) => {
                    console.error(error);
                    setMessage("Could not upload the file!");
                });
        } else {
            setMessage("Please select a file to upload.");
        }
    };

    const fetchAllFiles = () => {
        UploadFileService.getAllFiles()
            .then((response) => {
                setFiles(response.data);
            })
            .catch((error) => {
                console.error(error);
                setFiles([]);
            });
    };

    const updateFileName = (fileId, newFileName) => {
        UploadFileService.updateFileName(fileId, newFileName)
            .then((response) => {
                setMessage(response.data);
                fetchAllFiles(); // Refresh file list
            })
            .catch((error) => {
                console.error(error);
                setMessage("Could not update the file name!");
            });
    };

    return (
        <>
            <div>
                <input type="file" onChange={handleFileChange} />
                <button onClick={uploadFile}>Upload</button>
                <p>{message}</p>
            </div>
            <h4>Your Files</h4>
            {files.length === 0 ? (
                <p>No files available.</p>
            ) : (
                <ul>
                    {files.map((file) => (
                        <li key={file.id}>
                            {file.fileName}
                            <input
                                type="text"
                                placeholder="New file name"
                                onChange={(event) =>
                                    updateFileName(file.id, event.target.value)
                                }
                            />
                        </li>
                    ))}
                </ul>
            )}
        </>
    );
};

export default UploadFiles;

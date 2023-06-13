import React, { useState } from "react";
import UploadFileService from "../services/UploadFileService";

const UploadFiles = () => {
    const [selectedFile, setSelectedFile] = useState(null);
    const [message, setMessage] = useState("");

    const handleFileChange = (event) => {
        setSelectedFile(event.target.files[0]);
    };

    const uploadFile = () => {
        if (selectedFile) {
            UploadFileService.upload(selectedFile)
                .then((response) => {
                    setMessage(response.data);
                })
                .catch((error) => {
                    console.error(error);
                    setMessage("Could not upload the file!");
                });
        } else {
            setMessage("Please select a file to upload.");
        }
    };

    return (
        <div>
            <input type="file" onChange={handleFileChange} />
            <button onClick={uploadFile}>Upload</button>
            <p>{message}</p>
        </div>
    );
};

export default UploadFiles;

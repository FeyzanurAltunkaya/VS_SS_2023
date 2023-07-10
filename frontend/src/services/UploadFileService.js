import axios from "axios";

const BASE_URL = "http://localhost:8080"; // Update with your backend URL

const UploadFileService = {
    upload: (file) => {
        const formData = new FormData();
        formData.append("file", file);
        return axios.post(`${BASE_URL}/files1`, formData, {
            headers: {
                "Content-Type": "multipart/form-data",
            },
        });
    },

    getAllFiles: () => {
        return axios.get(`${BASE_URL}/files1`);
    },

    updateFileName: (fileId, newFileName) => {
        return axios.put(`${BASE_URL}/files1/${fileId}`, null, {
            params: {
                newFileName: newFileName,
            },
        });
    },
};

export default UploadFileService;

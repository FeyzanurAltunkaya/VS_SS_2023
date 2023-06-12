import axios from "axios";

const instance = axios.create({
    baseURL: "http://localhost:8080",

});

instance.defaults.headers.common["Content-Type"] = "multipart/form-data";
instance.defaults.headers.common["Another-Content-Type"] = "application/json";

export default instance;
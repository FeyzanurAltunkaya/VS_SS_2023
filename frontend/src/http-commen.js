import axios from "axios";

const instance = axios.create({
    baseURL: "http://localhost:8080",

});
instance.defaults.headers.common["Content-Type"] = "application/json";
instance.defaults.headers.common["Another-Content-Type"] = "multipart/form-data";


export default instance;
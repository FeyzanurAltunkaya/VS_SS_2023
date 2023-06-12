import http from "../http-files"

const getAll = () => {
    return http.get("/directory");
};

const get = id => {
    return http.get(`/directory/${id}`);
};

const getAllDirectoriesByUser = userId =>{
    return http.get(`/userdirectory/${userId}/directories`);
}

const create = data => {
    return http.post("/directory", data);
};

const update = (id, data) => {
    return http.put(`/directory/${id}`, data);
};

const remove = id => {
    return http.delete(`/directory/${id}`);
};

const removeAll = () => {
    return http.delete("/directory");
};

const findByTitle = title => {
    return http.get(`/directory?dirName=${title}`);
};

const addUserToDirectory = (userId, directoryId) => {
    return http.post(`/userdirectory/${userId}/directories/${directoryId}`);
};

const DirService = {
    getAll,
    get,
    create,
    update,
    remove,
    removeAll,
    findByTitle,
    getAllDirectoriesByUser,
    addUserToDirectory
};

export default DirService;
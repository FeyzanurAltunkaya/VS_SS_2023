import http from "../http-commen"

const getAll = () => {
    return http.get("/directory");
};

const getAllDirectoriesByUser = userId =>{
    return http.get(`/userdirectory/${userId}/directories`);
}

const getOneDirectoryOfUser = (userId, directoryId) => {
    return http.get(`/${userId}/directories/${directoryId}`);
};

const addUserToDirectory = (userId, directoryId) => {
    return http.post(`/userdirectory/${userId}/directories/${directoryId}`);
};

const get = id => {
    return http.get(`/directory/${id}`);
};

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



const deleteOneDirectoryOfUser = (userId, directoryId) => {
    return http.delete(`userdirectory/${userId}/directories/${directoryId}`);
};

const getAllDirectories = userId =>{
    return http.get(`/users/${userId}/directories`);
}

const getAllDirectoriesByUserNeu = userId =>{
    return http.get(`/userdirectory/${userId}/directories`);
}

const DirService = {
    getAll,
    get,
    create,
    update,
    remove,
    removeAll,
    getAllDirectoriesByUser,
    addUserToDirectory,
    getOneDirectoryOfUser,
    deleteOneDirectoryOfUser,
    getAllDirectories,
    getAllDirectoriesByUserNeu
};

export default DirService;
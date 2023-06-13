import http from "../http-commen";

const createDirectory = (directory) => {
    return http.post("/directory", directory);
};

const createDirectoryByUser = (userId, directory) => {
    return http.post(`/directory/user/${userId}`, directory);
};

const getDirectoryById = (id) => {
    return http.get(`/directory/${id}`);
};

const updateDirectory = (id, directory) => {
    return http.put(`/directory/${id}`, directory);
};

const deleteDirectory = (id) => {
    return http.delete(`/directory/${id}`);
};

const getDirectoriesByUserId = (userId) => {
    return http.get(`/directory/user/${userId}`);
};

const getOneDirectoryByOneUser = (userId, directoryId) => {
    return http.get(`/directory/user/${userId}/directory/${directoryId}`);
};

const DirService = {
    createDirectory,
    getDirectoryById,
    updateDirectory,
    deleteDirectory,
    getDirectoriesByUserId,
    getOneDirectoryByOneUser,
    createDirectoryByUser
};

export default DirService;

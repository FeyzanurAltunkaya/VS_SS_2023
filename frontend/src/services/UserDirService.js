import http from "../http-files"

const getAllDirectoriesOfUser = () => {
    return http.get("/userdirectory");
};

const getUserDirectory = id => {
    return http.get(`/userdirectory/${id}`);
};

const createUserDirectory = data => {
    return http.post("/userdirectory", data);
};

const updateUserDirectory = (id, data) => {
    return http.put(`/userdirectory/${id}`, data);
};




const UserDirService = {
   getUserDirectory,
    getAllDirectoriesOfUser,
    createUserDirectory,
    updateUserDirectory
};

export default UserDirService;
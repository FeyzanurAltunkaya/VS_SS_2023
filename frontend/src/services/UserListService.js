import http from "../http-commen"

const getAll = () => {
    return http.get("/users");
};

const get = id => {
    return http.get(`/users/${id}`);
};

const create = data => {
    return http.post("/users", data);
};

const update = (id, data) => {
    return http.put(`/users/${id}`, data);
};

const remove = id => {
    return http.delete(`/users/${id}`);
};

const removeAll = () => {
    return http.delete(`/users`);
};

const findByTitle = title => {
    return http.get(`/users?username=${title}`);
};

const register = (username , password) =>{
    return http.post("http://localhost:8080/api/auth/signup", {
        username,
        password
    })
}

const UserListService = {
    getAll,
    get,
    create,
    update,
    remove,
    removeAll,
    findByTitle,
    register
};

export default UserListService;
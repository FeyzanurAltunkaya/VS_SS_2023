import http from "../http-commen"

const getAll = () => {
    return http.get("/directory");
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
    return http.delete(`/directory`);
};

const findByTitle = title => {
    return http.get(`/directory?dirName=${title}`);
};

const DirService = {
    getAll,
    get,
    create,
    update,
    remove,
    removeAll,
    findByTitle
};

export default DirService;
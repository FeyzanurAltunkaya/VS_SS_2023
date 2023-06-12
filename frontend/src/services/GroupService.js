import http from "../http-files"


    const getAllGroups=() => {
        return http.get( '/group');
    }

    const get=(id) => {
        return http.get(`/group/${id}`);
    }

    const create=()=> {
        return http.post('/group');
    }

    const update=(id , data)=>{
        return http.put(`/group/${id}`, data);
    }

    const deleteById = (id) => {
        return http.get(`/group/${id}`);
    }

    const findByTitle =(userName) => {
        return http.get(`/group?group-name=${userName}`);
    }
    const GroupService = {
        get,
        getAllGroups,
        create,
        update,
        deleteById,
        findByTitle
    };

export default GroupService;
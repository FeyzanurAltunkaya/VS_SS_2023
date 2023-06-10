import http from "../http-commen"



class GroupService {
    getAllGroups() {
        return http.get( 'group');
    }

    get(id) {
        return http.get(`group/${id}`);
    }

    create() {
        return http.post('group');
    }

    update(id , data){
        return http.put(`group/${id}`, data);
    }

    delete(id) {
        return http.delete(`group/${id}`);
    }

    findByTitle(userName) {
        return http.get(`group?group-name=${userName}`);
    }
}

export default new GroupService();
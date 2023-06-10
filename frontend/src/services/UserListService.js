import http from "../http-commen"



class UserListService {
    getAllUsers() {
        return http.get( 'users');
    }

    get(id) {
        return http.get(`users/${id}`);
    }

    create() {
        return http.post('users');
    }

    update(id , data){
        return http.put(`users/${id}`, data);
    }

    delete(id) {
        return http.delete(`users/${id}`);
    }

    findByTitle(userName) {
        return http.get(`users?username=${userName}`);
    }
}

export default new UserListService();
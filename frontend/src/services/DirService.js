import http from "../http-commen"



class DirService {
    getAllDirectories() {
        return http.get( 'directory');
    }

    get(id) {
        return http.get(`directory/${id}`);
    }

    create() {
        return http.post('directory');
    }

    update(id , data){
        return http.put(`directory/${id}`, data);
    }

    delete(id) {
        return http.delete(`directory/${id}`);
    }

    findByTitle(dirName) {
        return http.get(`directory?title=${dirName}`);
    }
}

export default new DirService();
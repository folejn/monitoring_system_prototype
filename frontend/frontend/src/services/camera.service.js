import axios from 'axios';
import authHeader from './auth-header';

const API_URL = 'https://localhost:8080/api/v1';
class CameraService {  
    deleteCamera(id) {
        return axios.delete(API_URL + '/admin/cameras/'+id+'/delete', { headers: authHeader() });
    }
    createCamera(camera) {
        return axios.post(API_URL + '/admin/cameras/new', camera, { headers: authHeader() });
    }
    rotateCamera(id, direction) {
        return axios.get(API_URL + '/camera/' + id + '/servo/' + direction, { headers: authHeader() });
    }
    getCamera(id) {
        return axios.get(API_URL + '/admin/cameras/'+id, { headers: authHeader() });
    }
    updateCamera(camera) {
        return axios.put(API_URL + '/admin/cameras/update', camera, { headers: authHeader() });
    }
}
  
  export default new CameraService();
import axios from 'axios';
import authHeader from './auth-header';

const API_URL = 'https://localhost:8080/api/v1';

class UserService {
  getPublicContent() {
    //return axios.get(API_URL /*+ 'all'*/);
    return null;
  }

  getUserBoard() {
    return axios.get(API_URL + '/user/cameras', { headers: authHeader() });
  }

  getAdminBoard() {
    return axios.get(API_URL + '/admin/cameras', { headers: authHeader() });
  }
}

export default new UserService();

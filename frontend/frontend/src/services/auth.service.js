import axios from 'axios';

const API_URL = 'https://localhost:8080/api/v1/auth/';

class AuthService {
  login(signin) {
    return axios
      .post(API_URL + 'signin', {
        usernameOrEmail: signin.usernameOrEmail,
        password: signin.password
      })
      .then(response => {
        if (response.data.accessToken) {
          localStorage.setItem('user', JSON.stringify(response.data));
        }

        return response.data;
      });
  }

  logout() {
    localStorage.removeItem('user');
  }

  register(user) {
    return axios.post(API_URL + 'signup', {
      username: user.username,
      email: user.email,
      password: user.password
    });
  }
}

export default new AuthService();

<template>
    <div class="container">
      <header class="jumbotron">
        <table class="table is-bordered">
          <thead>
            <tr>
              <th>Id</th>
              <th>Address</th>
              <th>Name</th>
              <th></th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="item in content" :key="item.name">
              <td>{{ item.id }}</td>
              <td>{{ item.address }}</td> 
              <td>{{ item.name}}</td>
              <td><router-link :to="`/live/${item.id}`" tag="button" class="btn btn-primary">Show</router-link></td>
            </tr>
          </tbody>
        </table>
      </header>
    </div>
  </template>
  
  <script>
  import UserService from '../services/user.service';
  
  export default {
    name: 'User',
    data() {
      return {
        content: ''
      };
    },
    mounted() {
      UserService.getUserBoard().then(
        response => {
          this.content = response.data;
        },
        error => {
          this.content =
            (error.response && error.response.data) ||
            error.message ||
            error.toString();
        }
      );
    }
  };
  </script>
  
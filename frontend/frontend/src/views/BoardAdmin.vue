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
            <th></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="item in content" :key="item.name">
            <td>{{ item.id }}</td>
            <td>{{ item.address }}</td> 
            <td>{{ item.name}}</td>
            <td class="btn-grid">
              <router-link :to="`/live/${item.id}`" tag="button" class="btn btn-primary">Show</router-link>
              <button class="btn btn-danger" @click="deleteCamera(item.id)">Delete</button>
              <button class="btn btn-warning" @click="updateCamera(item.id)">Update</button>
            </td>
          </tr>
        </tbody>
      </table>
      <button class="btn btn-primary" @click="showModal">Add</button>
    </header>
    <CreateModal v-show="isCreateModalVisible" @close="closeModal"/>
    <UpdateModal v-show="isUpdateModalVisible" @close="closeUpdateModal" :cameraId="this.updateId"/>
  </div>
</template>
  
  <script>
  import UserService from '../services/user.service';
  import CameraService from '../services/camera.service';
  import Vue from 'vue';
  import CreateModal from '../views/CreateModal.vue'
  import UpdateModal from '../views/UpdateModal.vue'

  
  export default {
    name: 'User',
    data() {
      return {
        updateId: 0,
        content: '',
        isCreateModalVisible: false,
        isUpdateModalVisible: false
      };
    },
    components: {
      CreateModal,
      UpdateModal,
    },
    methods : {
      showModal() {
        this.isCreateModalVisible = true;
      },
      closeModal() {
        this.isCreateModalVisible = false;
        this.reloadList();
      },
      showUpdateModal() {
        this.isUpdateModalVisible = true;
      },
      closeUpdateModal() {
        this.isUpdateModalVisible = false;
        this.reloadList();
      },
      deleteCamera(id) {
        CameraService.deleteCamera(id).then( response => {
            this.reloadList();
            Vue.$toast.open(response.data);
          },
          error => {
            this.content =
              (error.response && error.response.data) ||
              error.message ||
              error.toString();
          }
        );
      },
      updateCamera(id) {
        this.updateId = id;
        UpdateModal.props.cameraId = id;
        this.showUpdateModal();
      },
      reloadList() {
        UserService.getAdminBoard().then(
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
    },
    mounted() {
      this.reloadList();
    }
  };
  </script>

<style>
.btn-grid {
  display: grid;
  grid-template-columns: repeat(3, minmax(10px,0.5fr));
  gap: 10px;
}
</style>
  
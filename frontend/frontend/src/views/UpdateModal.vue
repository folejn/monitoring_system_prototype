<template>
  <div class="modal-backdrop">
    <div class="modal">
      <header class="modal-header">
        <slot name="header">
          Update camera
        </slot>
        <button
          type="button"
          class="btn-close"
          @click="close"
        >
          x
        </button>
      </header>

      <section class="modal-body">
          <div class="card card-container">
      <form name="form" @submit.prevent="handleUpdate">
        <div v-if="!successful">
          <div class="form-group">
            <label for="address">Address</label>
            <input
              v-model="camera.address"
              type="text"
              class="form-control"
              name="address"
            />
            <div
              v-if="submitted && errors.has('address')"
              class="alert-danger"
            >{{errors.first('address')}}</div>
          </div>
          <div class="form-group">
            <label for="name">Name</label>
            <input
              v-model="camera.name"
              v-validate="'required|min:4|max:50'"
              type="text"
              class="form-control"
              name="address"
            />
            <div
              v-if="submitted && errors.has('name')"
              class="alert-danger"
            >{{errors.first('name')}}</div>
          </div>

          <div class="form-group">
            <button class="btn btn-primary btn-block">Save</button>
          </div>
        </div>
      </form>

      <div
        v-if="message"
        class="alert"
        :class="successful ? 'alert-success' : 'alert-danger'"
      >{{message}}</div>
    </div>
       </section>

      <footer class="modal-footer">
        <button
          type="button"
          class="btn-green"
          @click="close"
        >
          Close
        </button>
      </footer>
    </div>
  </div>
</template>

<script>
  import Camera from '../models/camera';
  import CameraService from '../services/camera.service';
  import Vue from 'vue';

  export default {
    setId(id) {
        this.cameraId = id;
      },
  name: 'UpdateModal',
  props: ['cameraId'],
  data() {
    return {
      camera: new Camera('', '', this.cameraId),
      submitted: false,
      successful: false,
      message: ''
    };
  },
  methods: {
      handleUpdate() {
          this.camera.id = this.cameraId;
          this.message = '';
          this.submitted = true;
          this.$validator.validate().then(isValid => {
              if (isValid) {
                  CameraService.updateCamera(this.camera).then(
                  data => {
                      this.message = data.message;
                      this.successful = true;
                      Vue.$toast.open(this.message);
                      this.close();
                  },
                  error => {
                      this.message =
                      (error.response && error.response.data.message) ||
                      error.message ||
                      error.toString();
                      this.successful = false;
                  }
              );
        }
      });
    },
      close() {
          this.$emit('close');
      },
  }
  };
</script>

<style>
.modal-backdrop {
  width: 50%;
  position: fixed;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
  background-color: rgba(0, 0, 0, 0.3);
  display: flex;
  justify-content: center;
  align-items: center;
}

.modal {
  position: fixed;
  left: 25%;
  width: 50%;
  height: 75%;
  top: 20%;

  background: #FFFFFF;
  box-shadow: 2px 2px 20px 1px;
  overflow-x: auto;
  display: flex;
  flex-direction: column;
}

.modal-header,
.modal-footer {
  padding: 15px;
  display: flex;
}

.modal-header {
  position: relative;
  border-bottom: 1px solid #eeeeee;
  color: #4AAE9B;
  justify-content: space-between;
}

.modal-footer {
  border-top: 1px solid #eeeeee;
  flex-direction: column;
  justify-content: flex-end;
}

.modal-body {
  position: relative;
  padding: 20px 10px;
}

.btn-close {
  position: absolute;
  top: 0;
  right: 0;
  border: none;
  font-size: 20px;
  padding: 10px;
  cursor: pointer;
  font-weight: bold;
  color: #4AAE9B;
  background: transparent;
}

.btn-green {
  color: white;
  background: #4AAE9B;
  border: 1px solid #4AAE9B;
  border-radius: 2px;
}
</style>
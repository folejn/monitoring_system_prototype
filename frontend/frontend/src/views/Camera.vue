<template>
    <div class="container">
      <header class="jumbotron">
        <div class="video">
            <video width="100%" height="100%" controls autoplay muted loop ref="camera" id="my-video">
            </video>
        </div>
        <div class="grid">
            <button class="btn btn-primary" @click="rotateUp">Rotate up</button>
            <button class="btn btn-primary" @click="rotateDown">Rotate down</button>
            <button class="btn btn-primary" @click="rotateRight">Rotate right</button>
            <button class="btn btn-primary" @click="rotateLeft">Rotate left</button>
        </div>
      </header>
    </div>
  </template>

  <script>
    import "./static/style.css";
    import CameraService from '../services/camera.service';
    import Vue from 'vue';
  
    export default {
      data() {
            return {
                id: this.$route.params.id,
                videoTag: null,
                timer: null,
            };
        },
        methods: {
            async loadVideo() {
                let token = JSON.parse(localStorage.getItem('user')).accessToken;
                const videoTag = document.getElementById("my-video");
                videoTag.src = await fetch('https://localhost:8080/api/v1/video/'+this.id+'/live.mp4', {
                    method: 'GET',
                    headers: { 
                        'Authorization': 'Bearer '+ token
                    }
                }).then(async function(response) {
                    return await response.arrayBuffer();
                }).then(async function(videoData) {
                    return await "data:video/mp4; base64," + Buffer.from(videoData).toString('base64');
                });
                
            },

            rotate(direction) {
                CameraService.rotateCamera(this.id, direction).then( response => {
                        Vue.$toast.open({
                            message: response.data.message,
                            type: 'success'
                        });
                    },
                    error => {
                        var message = 
                        (error.response && error.response.data) ||
                        error.message ||
                        error.toString();

                        Vue.$toast.open({
                            message: message,
                            type: 'error'
                        });
                    }
                );
            },
            rotateUp() {
                this.rotate('up');
            },
            rotateDown() {
                this.rotate('down');
            },
            rotateLeft() {
                this.rotate('left');
            },
            rotateRight() {
                this.rotate('right');
            }
        },
        mounted: function () {
            this.timer = setInterval(() => {
                this.loadVideo()
            }, 10000);
        }
    }
  </script>

<style>
.grid {
  display: grid;
  grid-template-columns: repeat(4, minmax(92px,1fr));
  gap: 10px;
}
</style>
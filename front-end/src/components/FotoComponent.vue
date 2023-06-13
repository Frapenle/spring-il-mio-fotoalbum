<template lang="">
    <div class="d-flex order-lg-2 my-2" role="search">
        <input class="form-control me-2" type="search" placeholder="Search photo" aria-label="Search"
            v-model="title" @keyup="getPhotoByTitle">
    </div>
    <div class="container">
        <div class="row">
            <div class="col-12">
                <div class="d-flex flex-wrap gap-2 justify-content-center">
                    <div class="card d-flex flex-column my-card" v-for="photo in photos" :key="photo.id">
                        <div>
                            <img :src="photo.imageUrl" class="card-img-top img-fluid" :alt="photo.title">
                        </div>
                        <div class="card-body">
                            <h5 class="card-title">{{photo.title}}</h5>
                            <p class="card-text">{{photo.description}}</p>
                            <small v-for="cat in photo.categories" :key="cat.id" class="card-text d-block">{{cat.name}}</small>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
import axios from 'axios';
export default {
    name: 'FotoComponent',
    data() {
        return {
            fotoUrl: "http://127.0.0.1:8080/api/v1/fotos/search",
            photos: [],
            title: '',
        }
    },
    methods: {
        getPhotoByTitle() {
            axios.get(`${this.fotoUrl}`, {
                params: {
                    title: this.title
                }
            })
                .then((res) => {
                    this.photos = res.data;
                    console.log(res.data);
                })
                .catch(function (error) {
                    console.log(error);
                });
        }
    },
    mounted() {
        this.getPhotoByTitle();
    },

}
</script>
<style lang="css">
.my-card {
    max-width: 18rem;
}

@media (max-width: 992px) {
    .my-card {
        max-width: 30rem;
    }
}
</style>
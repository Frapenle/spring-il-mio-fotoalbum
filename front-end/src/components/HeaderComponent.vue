<template>
    <nav class="navbar navbar-expand-lg bg-light">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">Foto</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <a class="nav-link ms-lg-2 order-lg-last" href="http://localhost:8080/login">Login</a>
                <div class="navbar-nav me-auto mb-2 mb-lg-0">
                </div>
            </div>
        </div>
    </nav>
</template>
<script>
import axios from 'axios';
export default {
    name: 'HeaderComponent',

}

</script>
<style></style>
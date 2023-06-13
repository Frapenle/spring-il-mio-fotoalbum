<template lang="">
    <div class="container mt-5">
        <div class="pt-4">
            <h5 class="text-center">Send a message for more info</h5>
            <form @submit.prevent="sendMessage">
                <div class="mb-3">
                    <label for="email" class="form-label mb-1">Email address</label>
                    <input type="email" class="form-control" id="email" placeholder="Email" v-model="mess.email" required>
                    <div class="invalid-feedback">Campo obbligatorio.</div>
                </div>
                <div class="mb-3">
                    <label for="text" class="form-label mb-1">Message</label>
                    <textarea class="form-control" id="text" rows="3" v-model="mess.message" placeholder="Message" required></textarea>
                    <div class="invalid-feedback">Campo obbligatorio.</div>
                    <button class="btn btn-primary mt-3 w-100" type="submit">Send</button>
                </div>
            </form>
            <div class="alert alert-success text-center mt-0" role="alert" v-if="alertMess">
                Message sent
            </div>
        </div>
    </div>
</template>
<script>
import axios from 'axios'
export default {
    data() {
        return {
            url: "http://localhost:8080/api/v1/message",
            mess: {
                email: '',
                message: '',
                createdAt: ''
            },
            alertMess: false,
        }
    },
    methods: {
        sendMessage() {
            this.mess.date = new Date();
            axios.post(this.url, this.mess)
                .then(res => {
                    this.$router.push('/');
                    this.alertMess = true
                    setTimeout(() => {
                        this.alertMess = false;
                    }, 2000);
                })
                .catch(function (error) {
                    console.log(error);
                });
        },
    }
}
</script>
<style></style>
<script>
import FotoComponent from '../components/FotoComponent.vue';
import MessageComponent from '../components/MessageComponent.vue';
export default {
    components: { FotoComponent, MessageComponent }
}

</script>

<template>
    <div class="row justify-content-center">
        <div class="col-12 col-md-8">
            <FotoComponent />
        </div>
        <div class="col-12 col-md-4">
            <MessageComponent />
        </div>
    </div>
</template>

<style scoped></style>

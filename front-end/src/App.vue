<script>
import HeaderComponent from './components/HeaderComponent.vue';

export default {
  name: 'App',
  components: {
    HeaderComponent,
  }
}

</script>

<template>
  <HeaderComponent />
  <section class="container">
    <router-view></router-view>
  </section>
</template>

<style></style>

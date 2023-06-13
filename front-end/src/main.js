import { createApp } from 'vue'
import { router } from './router';
import 'bootstrap/dist/css/bootstrap.css'
import "bootstrap";
import './style.css'
import axios from 'axios';
import App from './App.vue'

createApp(App).use(router).mount('#app')

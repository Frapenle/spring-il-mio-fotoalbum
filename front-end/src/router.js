import { createRouter, createWebHistory } from 'vue-router';
import FotoPage from './pages/FotoPage.vue'

const router = createRouter({
    history: createWebHistory(),
    routes: [
        {
            path: '/',
            name: 'home',
            component: FotoPage
        },
    ]
});
export { router };
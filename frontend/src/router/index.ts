import TourenListeView from '@/views/TourenListeView.vue'
import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/touren',
      name: 'TourenListeView',
      component: TourenListeView
    },
    {
      path: '/',
      redirect: '/touren'
    }
  ]
})

export default router

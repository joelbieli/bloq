import Vue from 'vue';
import Router from 'vue-router';
import Home from './views/read/Home.vue';
import store from './store';

Vue.use(Router);

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home,
    },
    {
      path: '/blog/:id',
      name: 'blog',
      component: () => import('./views/read/Blog.vue'),
    },
    {
      path: '/blogs/:tag',
      name: 'blogs_by_tag',
      component: () => import('./views/read/BlogsByTag.vue'),
    },
    {
      path: '/login',
      name: 'login',
      component: () => import('./views/read/Login.vue'),
    },
    {
      path: '/signup',
      name: 'signup',
      component: () => import('./views/read/Signup.vue'),
    },
    {
      path: '/admin',
      component: () => import('./views/admin/Layout.vue'),
      children: [
        {
          path: '',
          name: 'admin',
          component: () => import('./views/admin/Blogs.vue'),
        },
        {
          path: 'blog/:id',
          name: 'edit_blog',
          component: () => import('./views/admin/EditBlog.vue'),
        },
      ],
    },
  ],
});

router.beforeEach((to, from, next) => {
  if (to.path.startsWith('/admin')) {
    if (!store.getters.isAdmin) {
      next('/');
    }
  } else if (to.name === 'login' || to.name === 'signup') {
    if (store.getters.isLoggedIn) {
      next(from);
    }
  }

  next();
});

export default router;

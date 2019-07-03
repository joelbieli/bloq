<template>
    <div id="app">

        <nav class="navbar is-fixed-top is-primary" role="navigation" aria-label="main navigation">
            <div class="navbar-brand">
                <router-link to="/" class="logo">bloq</router-link>
            </div>

            <div class="navbar-menu">
                <div class="navbar-start">
                    <router-link class="navbar-item" to="/">
                        Home
                    </router-link>

                    <router-link class="navbar-item" to="/admin" v-if="isAdmin">
                        Admin panel
                    </router-link>
                </div>

                <div class="navbar-end">
                    <div class="navbar-item" v-if="!isLoggedIn">
                        <b-button tag="router-link" to="/signup" type="is-primary" inverted>
                            <b>Sign up</b>
                        </b-button>
                    </div>
                    <div class="navbar-item" v-if="!isLoggedIn">
                        <b-button tag="router-link" to="/login" type="is-primary" outlined inverted>
                            Log in
                        </b-button>
                    </div>
                    <div class="navbar-item" v-if="isLoggedIn">
                        <b>{{username}}</b>
                    </div>
                    <div class="navbar-item" v-if="isLoggedIn">
                        <b-button type="is-primary" outlined inverted @click="signOut">
                            Log out
                        </b-button>
                    </div>
                </div>
            </div>
        </nav>

        <div style="height: 32px"></div>

        <router-view/>
    </div>
</template>

<script>
import { mapGetters, mapState, mapMutations } from 'vuex';

export default {
  name: 'app',
  computed: {
    ...mapGetters([
      'isAdmin',
      'isLoggedIn',
    ]),
    ...mapState({
      username: state => state.user.username,
    }),
  },
  methods: {
    ...mapMutations({
      commitSignOut: 'signOut',
      commitSetUser: 'setUser',
    }),
    signOut() {
      this.commitSignOut();
      this.$notification.open({
        type: 'is-success',
        message: 'Successfully logged out',
      });
      this.$router.push('/');
    },
  },
  mounted() {
    const user = localStorage.getItem('user');

    if (user) {
      this.commitSetUser(JSON.parse(user));
    }
  },
};
</script>

<style>
    #app {
        text-align: center;
        color: #2c3e50;
    }
</style>

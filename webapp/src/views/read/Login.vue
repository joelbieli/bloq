<template>
    <div id="login" class="columns">
        <div class="column is-one-third is-offset-one-third">
            <h3 class="title has-text-centered has-text-dark">Login</h3>
            <div class="box">
                <form @submit.prevent="validateBeforeSubmit">
                    <b-field label="E-Mail"
                             :type="{'is-danger': errors.has('email')}"
                             :message="errors.first('email')">
                        <b-input v-model="email"
                                 type="email"
                                 name="email"
                                 placeholder="E-Mail"
                                 v-validate="'required|email'"></b-input>
                    </b-field>

                    <b-field label="Password"
                             :type="{'is-danger': errors.has('password')}"
                             :message="errors.first('password')">
                        <b-input v-model="password"
                                 type="password"
                                 name="password"
                                 placeholder="Password"
                                 minlength="6"
                                 password-reveal
                                 v-validate="'required|min:6'"></b-input>
                    </b-field>

                    <button class="button is-primary is-outlined is-large is-fullwidth"
                            type="submit">
                        Log in
                    </button>
                </form>
            </div>
        </div>
    </div>
</template>

<script>
/* eslint-disable no-param-reassign */

export default {
  name: 'Login',
  data() {
    return {
      email: '',
      password: '',
      stepsBack: -1,
    };
  },
  methods: {
    validateBeforeSubmit() {
      this.$validator.validateAll().then((result) => {
        if (result) {
          this.$axios.post('/auth/signin', {
            usernameOrEmail: this.email,
            password: this.password,
          }).then((response) => {
            this.$store.commit('setUser', response.data);

            localStorage.setItem('user', JSON.stringify(response.data));

            this.$notification.open({
              type: 'is-success',
              message: 'Login successful',
            });

            this.$router.go(this.stepsBack);
          }).catch(() => this.$notification.open({
            type: 'is-danger',
            message: 'A problem occurred while logging you in',
          }));
        }
      });
    },
  },
  beforeRouteEnter(to, from, next) {
    next((vm) => {
      if (from.name === 'signup') vm.stepsBack = -2;
    });
  },
};
</script>

<style scoped>

</style>

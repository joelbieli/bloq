<template>
    <div id="login" class="columns">
        <div class="column is-one-third is-offset-one-third">
            <h3 class="title has-text-centered has-text-dark">Signup</h3>
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

                    <b-field label="Username"
                             :type="{'is-danger': errors.has('username')}"
                             :message="errors.first('username')">
                        <b-input v-model="username"
                                 type="username"
                                 name="username"
                                 placeholder="Username"
                                 v-validate="'required|min:5|max:25'"></b-input>
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

                    <b-field label="Confirm password"
                             :type="{'is-danger': errors.has('confirm-password')}"
                             :message="[{
                             'The confirm password field is required' :
                                errors.firstByRule('confirm-password', 'required'),
                             'The confirm password is not valid' :
                                errors.firstByRule('confirm-password', 'is')
                             }]">
                        <b-input type="password"
                                 v-model="confirmPassword"
                                 name="confirm-password"
                                 placeholder="Password"
                                 v-validate="{ required: true, is: password }"></b-input>
                    </b-field>

                    <button class="button is-primary is-outlined is-large is-fullwidth"
                            type="submit">
                        Sign up
                    </button>
                </form>
            </div>
        </div>
    </div>
</template>

<script>
export default {
  name: 'Login',
  data() {
    return {
      email: '',
      username: '',
      password: '',
      confirmPassword: '',
    };
  },
  methods: {
    validateBeforeSubmit() {
      this.$validator.validateAll().then((result) => {
        if (result) {
          this.$axios.post('/auth/signup', {
            email: this.email,
            username: this.username,
            password: this.password,
          }).then((response) => {
            this.$store.commit('setUser', response.data);

            this.$notification.open({
              type: 'is-success',
              message: 'Signup successful',
            });

            this.$router.push('/login');
          }).catch(() => this.$notification.open({
            type: 'is-danger',
            message: 'A problem occurred while signing you up',
          }));
        }
      });
    },
  },
};
</script>

<style scoped>

</style>

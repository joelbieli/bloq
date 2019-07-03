<template>
    <div class="columns">
        <div class="column is-three-fifths is-offset-one-fifth">
            <div class="box">
                <h1 class="is-size-2 is-bold">{{blogPost.title}}</h1>
                <tags :tags="blogPost.tags"/>
                <div class="is-size-5 md-view content" v-html="parsedMD"></div>
                <span class="is-size-7 has-text-grey">
                    Created: {{formattedCreatedDate}},&#9;Last edited: {{formattedLateEditedDate}}
                </span>
            </div>
            <div class="box" v-if="isLoggedIn">
                <div class="level">
                    <b-input class="width level-item"
                             rows="1"
                             placeholder="Write a comment..."
                             type="textarea"
                             maxlength="500"
                             v-model="comment"></b-input>
                    <b-button type="is-primary" @click="submitComment">Post comment</b-button>
                </div>
                <div class="has-text-left"
                     :key="comment.id"
                     v-for="comment in blogPost.comments">
                    <div class="is-divider"></div>
                    <h4 class="is-size-6 has-text-weight-bold">{{comment.author.username}}</h4>
                    <p>
                        {{comment.text}}
                    </p>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import { mapGetters, mapState } from 'vuex';
import moment from 'moment';
import marked from 'marked';
import Tags from '@/components/read/Tags.vue';

export default {
  name: 'Blog',
  components: {
    Tags,
  },
  data() {
    return {
      blogPost: Object,
      comment: '',
    };
  },
  computed: {
    formattedCreatedDate() { return moment(this.blogPost.createdDate).format('lll'); },
    formattedLateEditedDate() { return moment(this.blogPost.lastEditedDate).format('lll'); },
    parsedMD() {
      if (this.blogPost && this.blogPost.text) {
        return marked(this.blogPost.text, { sanitize: true });
      }
      return '';
    },
    ...mapGetters({
      isLoggedIn: 'isLoggedIn',
    }),
    ...mapState({
      authToken: state => state.user.authToken,
    }),
  },
  methods: {
    submitComment() {
      this.$axios.post(
        `/blogpost/${this.blogPost.id}/comment`,
        { text: this.comment },
        { headers: { Authorization: `Bearer ${this.authToken}` } },
      ).then((response) => {
        this.comment = '';
        this.blogPost = response.data;
      })
        .catch(() => this.$notification.open({
          type: 'is-danger',
          message: 'A problem occurred while submitting your comment',
        }));
    },
  },
  mounted() {
    this.$axios.get(`/blogpost/${this.$route.params.id}`)
      .then((response) => {
        this.blogPost = response.data;
      })
      .catch(() => this.$notification.open({
        type: 'is-danger',
        message: 'A problem occurred while getting the blog post',
      }));
  },
};
</script>

<style scoped>
</style>

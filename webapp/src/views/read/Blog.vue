<template>
    <div class="columns">
        <div class="column is-three-fifths is-offset-one-fifth">
            <div class="box">
                <h1 class="is-size-2 is-bold">{{blogPost.title}}</h1>
                <tags :tags="blogPost.tags"/>
                <div class="is-size-5 md-view" v-html="parsedMD"></div>
                <span class="is-size-7 has-text-grey">
                    Created: {{formattedCreatedDate}},&#9;Last edited: {{formattedLateEditedDate}}
                </span>
            </div>
        </div>
    </div>
</template>

<script>
import Tags from '@/components/read/Tags.vue';
import moment from 'moment';
import marked from 'marked';

export default {
  name: 'Blog',
  components: {
    Tags,
  },
  data() {
    return {
      blogPost: Object,
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
  },
  mounted() {
    this.$axios.get(`/blogpost/${this.$route.params.id}`)
      .then(function success(response) {
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

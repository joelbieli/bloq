<template>
    <div class="box blog-container" @click="openBlog">
        <h1 class="is-size-2 is-bold">{{blogPost.title}}</h1>
        <div class="content" v-html="parsedMD"></div>
        <tags :tags="blogPost.tags"/>
        <span class="is-size-7 has-text-grey">
            Created: {{formattedCreatedDate}},&#9;Last edited: {{formattedLateEditedDate}}
        </span>
    </div>
</template>

<script>
import moment from 'moment';
import Tags from '@/components/read/Tags.vue';
import marked from 'marked';

export default {
  name: 'BlogPreview',
  props: {
    blogPost: Object,
  },
  computed: {
    formattedCreatedDate() { return moment(this.blogPost.createdDate).format('lll'); },
    formattedLateEditedDate() { return moment(this.blogPost.lastEditedDate).format('lll'); },
    parsedMD() {
      if (this.blogPost && this.blogPost.text) {
        return marked(this.blogPost.text.substr(0, 200) + "...", { sanitize: true });
      }
      return '';
    },
  },
  components: {
    Tags,
  },
  methods: {
    openBlog() {
      this.$router.push(`/blog/${this.blogPost.id}`);
    },
  },
};
</script>

<style scoped>
    .blog-container {
        cursor: pointer;
    }

    p {
        max-height: 6rem;
        word-wrap: break-word;
        overflow: hidden;
    }
</style>

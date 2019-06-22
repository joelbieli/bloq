<template>
    <div class="box blog-container" @click="openBlog">
        <h1 class="is-size-2 is-bold">{{blogPost.title}}</h1>
        <p>{{blogPost.text}}</p>
        <tags :tags="blogPost.tags"/>
        <span class="is-size-7 has-text-grey">
            Created: {{formattedCreatedDate}},&#9;Last edited: {{formattedLateEditedDate}}
        </span>
    </div>
</template>

<script>
import Tags from '@/components/read/Tags.vue';
import moment from 'moment';

export default {
  name: 'BlogPreview',
  props: {
    blogPost: Object,
  },
  computed: {
    formattedCreatedDate() { return moment(this.blogPost.createdDate).format('lll'); },
    formattedLateEditedDate() { return moment(this.blogPost.lastEditedDate).format('lll'); },
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

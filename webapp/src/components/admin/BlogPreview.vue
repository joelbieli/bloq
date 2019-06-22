<template>
    <div class="box blog-container" @click="editBlog">
        <span class="is-size-2 is-bold">{{blogPost.title}}</span>
        <tags :tags="blogPost.tags"/>
    </div>
</template>

<script>
import Tags from '@/components/admin/Tags.vue';

export default {
  name: 'BlogPreview',
  props: {
    blogPost: Object,
  },
  components: {
    Tags,
  },
  methods: {
    editBlog() {
      this.$router.push(`/admin/blog/${this.blogPost.id}`);
    },
  },
};
</script>

<style scoped>
    .blog-container {
        cursor: pointer;
    }
</style>

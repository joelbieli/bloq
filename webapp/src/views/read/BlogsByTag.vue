<template>
    <div class="columns">
        <div class="column is-three-fifths is-offset-one-fifth">
            <blog-preview
                    v-for="blogPost in blogPosts"
                    :key="blogPost.key"
                    :blog-post="blogPost"
            />
        </div>
    </div>
</template>

<script>
import BlogPreview from '@/components/read/BlogPreview.vue';

export default {
  name: 'BlogsByTag',
  components: {
    BlogPreview,
  },
  data() {
    return {
      blogPosts: Array,
    };
  },
  mounted() {
    this.$axios.get(`/blogposts/${this.$route.params.tag}`)
    // eslint-disable-next-line no-return-assign
      .then(response => this.blogPosts = response.data)
      .catch(() => this.$notification.open({
        type: 'is-danger',
        message: 'A problem occurred while getting the blog posts',
      }));
  },
};
</script>

<style scoped>

</style>

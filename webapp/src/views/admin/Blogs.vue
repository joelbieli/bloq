<template>
    <div class="columns is-multiline">
        <div class="column is-12">
            <b-button class="is-pulled-right"
                      icon-pack="fas"
                      icon-left="plus"
                      type="is-primary"
                      tag="router-link"
                      to="/admin/blog/new">Add Blog</b-button>
        </div>
        <div class="column is-half"
             v-for="blogpost in blogPosts"
            :key="blogpost.id">
            <blog-preview :blog-post="blogpost"/>
        </div>
    </div>
</template>

<script>
import { mapActions, mapState } from 'vuex';
import BlogPreview from '@/components/admin/BlogPreview.vue';

export default {
  name: 'Blogs',
  components: {
    BlogPreview,
  },
  computed: {
    ...mapState({
      blogPosts: 'blogPosts',
    }),
  },
  methods: {
    ...mapActions({
      loadBlogPosts: 'loadBlogPosts',
    }),
  },
  mounted() {
    this.loadBlogPosts();
  },
};
</script>

<style scoped>

</style>

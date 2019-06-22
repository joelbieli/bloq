<template>
    <div class="columns">
        <div class="column is-three-fifths is-offset-one-fifth">
            <blog-preview
                    v-for="blogPost in blogPosts"
                    :key="blogPost.id"
                    :blog-post="blogPost"
            />
        </div>
    </div>
</template>

<script>
import { mapState, mapActions } from 'vuex';
import BlogPreview from '@/components/read/BlogPreview.vue';

export default {
  name: 'Home',
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

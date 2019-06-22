<template>
    <b-taglist class="column">
        <b-tag type="is-light"
               size="is-medium"
               v-for="tag in tags"
               :key="tag.id">
            <div class="tag-inner" @click.stop="() => showBlogsByTag(tag.name)">
                {{tag.name}}
            </div>
        </b-tag>
    </b-taglist>
</template>

<script>
export default {
  name: 'Tags',
  props: {
    tags: Array,
  },
  methods: {
    showBlogsByTag(tag) {
      this.$router.push(`/blogs/${tag}`);
    },
  },
};
</script>

<style scoped>
    .tag-inner {
        cursor: pointer;
    }

    .tags:not(:last-child) {
        margin-bottom: 0;
    }

    .tags .tag {
        margin-bottom: 0;
    }
</style>

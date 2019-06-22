<template>
    <b-taglist class="column">
        <b-tag type="is-light"
               size="is-medium"
               v-for="tag in tags"
               :key="tag.id">
            <div class="tag-inner" @click.stop="() => editTag(tag.id)">
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
    editTag(tag) {
      this.$router.push(`/admin/tag/${tag}`);
    },
  },
};
</script>

<style scoped>
    .tag-inner {
        cursor: pointer;
    }
</style>

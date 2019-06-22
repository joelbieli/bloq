<template>
    <div class="box columns is-multiline">
        <div class="column is-12">
            <b-field label="Title">
                <b-input class="is-size-1" v-model="blogPost.title" size="is-medium"></b-input>
            </b-field>
        </div>
        <div class="column is-12">
            <b-field label="Tags">
                <b-taginput
                        v-model="blogPost.tags"
                        :data="tags"
                        autocomplete
                        allow-new
                        field="name"
                        icon="tag"
                        placeholder="Add a tag"
                        size="is-medium">
                </b-taginput>
            </b-field>
        </div>
        <div class="column is-half content">
            <textarea class="textarea is-family-code md-editor"
                      v-model="blogPost.text"
                      @input="resize"></textarea>
        </div>
        <div class="column is-half md-view" v-html="parsedMD"></div>
        <div class="column is-12">
            <b-button class="is-pulled-right"
                      type="is-primary"
                      icon-pack="fas"
                      icon-left="save"
                      @click="saveBlog">Save
            </b-button>
        </div>
    </div>
</template>

<script>
/* eslint-disable no-param-reassign */
/* eslint-disable no-return-assign */

import { mapState } from 'vuex';
import marked from 'marked';

export default {
  name: 'EditBlog',
  data() {
    return {
      blogPost: {},
      tags: [],
    };
  },
  computed: {
    ...mapState({
      authToken: state => state.user.authToken,
    }),
    parsedMD() {
      if (this.blogPost && this.blogPost.text) {
        return marked(this.blogPost.text, { sanitize: true });
      }
      return '';
    },
  },
  methods: {
    saveBlog() {
      this.$axios.post(
        '/blogpost',
        {
          ...this.blogPost,
          tags: this.blogPost.tags.map(tag => (typeof tag === 'string' ? { name: tag } : tag)),
        },
        { headers: { Authorization: `Bearer ${this.authToken}` } },
      )
        .then((response) => {
          this.blogPost = response.data;

          this.$notification.open({
            type: 'is-success',
            message: 'Blogpost successfully saved',
          });
        })
        .catch(() => this.$notification.open({
          type: 'is-danger',
          message: 'A problem occurred while saving the blog post',
        }));
    },
    resize(event) {
      console.log(event.target.scrollHeight);
      event.target.style.height = '1px';
      event.target.style.height = `${25 + event.target.scrollHeight}px`;
    },
  },
  mounted() {
    if (this.$route.params.id === 'new') {
      this.blogPost = {
        title: '',
        text: '',
        tags: [],
      };
    } else {
      this.$axios.get(`/blogpost/${this.$route.params.id}`)
        .then(response => this.blogPost = response.data)
        .catch(() => this.$notification.open({
          type: 'is-danger',
          message: 'A problem occurred while getting the blog post',
        }));
    }

    this.$axios.get('/tags', { headers: { Authorization: `Bearer ${this.authToken}` } })
      .then(response => this.tags = response.data)
      .catch(() => this.$notification.open({
        type: 'is-danger',
        message: 'A problem occurred while getting all tags',
      }));
  },
};
</script>

<style scoped lang="scss">
    .box {
        margin-top: 0.5rem;
    }

    .md-editor {
        width: 100%;
        max-height: none;
        border: none;
        resize: none;
        background-color: #f6f6f6;
    }
</style>

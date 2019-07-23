<template>
  <div class="about">
    <h1>This is an about page</h1>
    <button v-on:click="login">登录</button>
  </div>
</template>

<script>
  import {login} from '@/Http'
  export default {
    methods: {
      login: () => {
        login();
      }
    }
  }
</script>

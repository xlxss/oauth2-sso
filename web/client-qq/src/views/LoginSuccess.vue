<template>
    <div>
        <p>Login success</p>
        <p>{{hello}}</p>
    </div>
</template>

<script>
    import {get} from '@/Http'

    export default {
        name: "LoginSuccess",
        data() {
            return {
                hello: ''
            }
        },
        mounted() {
            console.log('LoginSuccess.in.Hello');
            get('/api/hello', {}, data => this.hello = data);
            console.log('LoginSuccess.ok');
        }
    }
</script>

<style scoped>

</style>
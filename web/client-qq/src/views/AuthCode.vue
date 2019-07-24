<template>
    <div></div>
</template>

<script>
    import {getToken} from '@/Http'
    export default {
        name: "AuthCode",
        data() {
            return {
                code: '',
            }
        },
        methods: {
            getToken: function() {
                getToken(this.code, data => {
                    console.log("AuthCode.getToken.then", data);
                    this.$router.push('/login/success');
                })
            }
        },
        mounted() {
            this.code = this.$route.query.code;
            this.getToken();
        }
    }
</script>

<style scoped>

</style>
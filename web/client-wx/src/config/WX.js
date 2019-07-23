const WX = {
    AUTH: {
        BASE_URI: 'http://auth-server:8000/auth',
        AUTHORIZE: '/oauth/authorize',
        TOKEN: '/oauth/token',
        REDIRECT: 'http://localhost:8080/auth/code',
        CLIENT_ID: 'weixin',
        CLIENT_SECRET: '111',
        SCOPE: '',
        RESPONSE_TYPE: 'code',
        GRANT_TYPE: 'authorization_code',
    },
    REST: {
        BASE_URI: 'http://client-wx:8010/wx'
    },
};

export default WX
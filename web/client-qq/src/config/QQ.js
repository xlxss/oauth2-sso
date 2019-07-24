const QQ = {
    AUTH: {
        BASE_URI: 'http://auth-server:8000/auth',
        AUTHORIZE: '/oauth/authorize',
        TOKEN: '/oauth/token',
        REDIRECT: 'http://localhost:8082/auth/code',
        CLIENT_ID: 'qq',
        CLIENT_SECRET: '111',
        SCOPE: '',
        RESPONSE_TYPE: 'code',
        GRANT_TYPE: 'authorization_code',
    },
    REST: {
        BASE_URI: 'http://client-qq:8020/qq'
    },
};

export default QQ
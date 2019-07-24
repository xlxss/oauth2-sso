import axios from 'axios'
import QQ from '@/config/QQ'

export const get = (url, params, fn) => {
    console.log('Http.get in');
    checkToken();
    axios.get(QQ.REST.BASE_URI + url, {params: params || {}, headers: {'Authorization': token()}})
        .then(res => {
            console.log('Http.get success, data:', res.data);
            if(typeof fn === 'function') {
                fn(res.data);
            }
        })
        .catch(err => console.log(err));
};

// 登录
export const login = () => {
    // window.location.href = QQ.AUTH.BASE_URI + QQ.AUTH.AUTHORIZE + "?client_id=" + QQ.AUTH.CLIENT_ID + "&response_type=" + QQ.AUTH.RESPONSE_TYPE + "&redirect_uri=" + QQ.AUTH.REDIRECT;
    window.location.href = `${QQ.AUTH.BASE_URI}${QQ.AUTH.AUTHORIZE}?client_id=${QQ.AUTH.CLIENT_ID}&response_type=${QQ.AUTH.RESPONSE_TYPE}&redirect_uri=${QQ.AUTH.REDIRECT}`;
};

// 登出
export const logout = () => {
    return axios.post(`${QQ.REST.BASE_URI}/logout`)
        .then(res => {
            sessionStorage.removeItem('access_token');
            sessionStorage.removeItem('token_type');
            sessionStorage.removeItem('refresh_token');
            sessionStorage.removeItem('expires_in');
            sessionStorage.removeItem('scope');
            sessionStorage.removeItem('token_time');
            return res;
        })
        .catch(err => console.log(err));
};

// 获取访问令牌
export const getToken = (code, fn) => {
    console.log('Http.getToken code:', code);
    axios.post(`${QQ.REST.BASE_URI}/auth/token`, {code})
        .then(res => {
            const data = res.data;
            console.log('Http.getToken data:', data);
            sessionStorage.setItem("access_token", data.access_token);
            sessionStorage.setItem("token_type", data.token_type);
            sessionStorage.setItem("refresh_token", data.refresh_token);
            sessionStorage.setItem("expires_in", data.expires_in);
            sessionStorage.setItem("scope", data.scope);
            sessionStorage.setItem("token_time", Date.now());
            console.log('Http.getToken success:', code);
            if(typeof fn === 'function') {
                fn(data);
            }
        })
        .catch(err => {
            console.log(err);
            return err;
        });
};

// 检查访问令牌是否过期，如果过期则更新令牌
const checkToken = () => {
    if(sessionStorage.getItem('access_token')) {
        try {
            const is_expires = Date.now() - parseInt(sessionStorage.getItem('expires_in')) * 1000 - parseInt(sessionStorage.getItem('token_time')) > 0;
            if (is_expires) {
                refreshToken();
            }
        } catch (e) {
            console.log(e);
            login();
        }
    } else {
        login();
    }
};

// 更新访问令牌
const refreshToken = () => {
    console.log('Http.refreshToken.in');
    axios.post(`${QQ.REST.BASE_URI}/auth/refreshToken`, sessionStorage.getItem("refresh_token"), {headers: {Authorization: token()}})
        .then(res => {
            const data = res.data;
            sessionStorage.setItem("access_token", data.access_token);
            sessionStorage.setItem("expires_in", data.expires_in);
            return data;
        })
        .catch(err => console.log(err))
};

const token = () => {
    return `${sessionStorage.getItem('token_type')} ${sessionStorage.getItem('access_token')}`;
};
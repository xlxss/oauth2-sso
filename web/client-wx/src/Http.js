import axios from 'axios'
import WX from '@/config/WX'

export const get = (url, params, fn) => {
    console.log('Http.get in');
    checkToken();
    axios.get(WX.REST.BASE_URI + url, {params: params || {}, headers: {'Authorization': token()}})
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
    // window.location.href = WX.AUTH.BASE_URI + WX.AUTH.AUTHORIZE + "?client_id=" + WX.AUTH.CLIENT_ID + "&response_type=" + WX.AUTH.RESPONSE_TYPE + "&redirect_uri=" + WX.AUTH.REDIRECT;
    window.location.href = `${WX.AUTH.BASE_URI}${WX.AUTH.AUTHORIZE}?client_id=${WX.AUTH.CLIENT_ID}&response_type=${WX.AUTH.RESPONSE_TYPE}&redirect_uri=${WX.AUTH.REDIRECT}`;
};

// 登出
export const logout = () => {
    return axios.post(`${WX.REST.BASE_URI}/logout`, {code})
        .then(res => {
            localStorage.removeItem('access_token');
            localStorage.removeItem('token_type');
            localStorage.removeItem('refresh_token');
            localStorage.removeItem('expires_in');
            localStorage.removeItem('scope');
            localStorage.removeItem('token_time');
            return res;
        })
        .catch(err => console.log(err));
};

// 获取访问令牌
export const getToken = (code, fn) => {
    console.log('Http.getToken code:', code);
    axios.post(`${WX.REST.BASE_URI}/auth/token`, {code})
        .then(res => {
            const data = res.data;
            console.log('Http.getToken data:', data);
            localStorage.setItem("access_token", data.access_token);
            localStorage.setItem("token_type", data.token_type);
            localStorage.setItem("refresh_token", data.refresh_token);
            localStorage.setItem("expires_in", data.expires_in);
            localStorage.setItem("scope", data.scope);
            localStorage.setItem("token_time", Date.now());
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
    if(localStorage.getItem('access_token')) {
        try {
            const is_expires = Date.now() - parseInt(localStorage.getItem('expires_in')) * 1000 - parseInt(localStorage.getItem('token_time')) > 0;
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
    axios.post(`${WX.REST.BASE_URI}/auth/refreshToken`, localStorage.getItem("refresh_token"), {headers: {Authorization: token()}})
        .then(res => {
            const data = res.data;
            localStorage.setItem("access_token", data.access_token);
            localStorage.setItem("expires_in", data.expires_in);
            return data;
        })
        .catch(err => console.log(err))
};

const token = () => {
    return `${localStorage.getItem('token_type')} ${localStorage.getItem('access_token')}`;
};
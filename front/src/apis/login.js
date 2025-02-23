import req from '@/apis/request.js' 

// 登录接口
export const login = params => req('get', '/user/login', params)
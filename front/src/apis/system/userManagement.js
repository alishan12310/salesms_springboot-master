import req from '@/apis/request.js' 

// 添加用户
export const addUser = params => req('post', '/user/addAllMan', params);

// 查询用户
export const queryUser = params => req('get', '/user/queryAllMan' , params);

// 修改用户信息
export const updateUser = params => req('put', '/user/updateAllMan', params);

// 删除用户
export const deleteUser = params => req('delete', "/user/deleteAllMan/" + params);

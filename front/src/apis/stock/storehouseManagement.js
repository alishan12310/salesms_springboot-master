import req from '@/apis/request.js' 

// 添加库房
export const saveStorehouse = params => req('post', '/stockStorehouse/saveStorehouse', params);

// 查询库房
export const queryStorehouse = params => req('get', '/stockStorehouse/queryStorehouse', params);

// 修改库房信息
export const updateStorehouse = params => req('put', '/stockStorehouse/updateStorehouse', params);

// 删除库房
export const deleteStorehouse = params => req('delete', '/stockStorehouse/deleteStorehouse', params);

import req from '@/apis/request.js' 

// 添加单位
export const saveUnit = params => req('post', '/stockUnit/saveUnit', params);

// 查询单位
export const queryUnit = params => req('get', '/stockUnit/queryUnit', params);

// 查询全部单位
export const queryAllUnit = params => req('get', '/stockUnit/queryAllUnit', params);

// 修改单位信息
export const updateUnit = params => req('put', '/stockUnit/updateUnit', params);

// 删除单位
export const deleteUnit = params => req('delete', '/stockUnit/deleteUnit', params);

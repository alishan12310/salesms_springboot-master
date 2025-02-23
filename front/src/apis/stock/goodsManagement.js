import req from '@/apis/request.js' 

// 添加货物
export const saveGoods = params => req('post', '/stockGoods/saveGoods', params);

// 查询货物
export const queryGoods = params => req('get', '/stockGoods/queryGoods', params);

// 查询全部货物
export const queryAllGoods = params => req('get', '/stockGoods/queryAllGoods', params);

// 修改货物信息
export const updateGoods = params => req('put', '/stockGoods/updateGoods', params);

// 删除货物
export const deleteGoods = params => req('delete', '/stockGoods/deleteGoods', params);

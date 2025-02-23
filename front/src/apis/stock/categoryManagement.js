import req from '@/apis/request.js' 

// 添加类别
export const saveCategory = params => req('post', '/stockCategory/saveCategory', params);

// 查询类别
export const queryCategory = params => req('get', '/stockCategory/queryCategory', params);

// 查询全部类别
export const queryAllCategory = params => req('get', '/stockCategory/queryAllCategory', params);

// 修改类别信息
export const updateCategory = params => req('put', '/stockCategory/updateCategory', params);

// 删除类别
export const deleteCategory = params => req('delete', '/stockCategory/deleteCategory', params);

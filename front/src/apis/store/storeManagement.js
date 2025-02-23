import req from '@/apis/request.js' 

// 添加店铺
export const saveStore = params => req('post', '/storeStoremanages', params);

// 查询店铺
export const queryStore = params => req('get', '/storeStoremanages', params);

// 查店名
export const queryStoreName = params => req('get', '/storeStoremanages/queryStoreName', params);

// 查店铺下的员工
export const queryAllManByStorehouse = params => req('get', '/user/queryAllManByStorehouse/' + params);

// 修改店铺信息
export const updateStore = params => req('put', '/storeStoremanages', params);

// 删除店铺
export const deleteStore = params => req('delete', "/storeStoremanages/"+params);

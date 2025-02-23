import req from '@/apis/request.js' 

// 添加店铺
export const saveMenu = params => req('post', '/menuManages', params);

// 查询菜单
export const queryMenu = params => req('get', '/menuManages/' + params);

// 查询所有一级菜单
export const getAllOneMenuList = params => req('get', '/menuManages/getAllOneMenuList', params);

// 查询二级菜单
export const getAllTwoMenuList = params => req('get', '/menuManages/getAllTwoMenuList/' + params);

// 修改店铺信息
export const updateMenu = params => req('put', '/menuManages', params);

// 删除店铺
export const deleteMenu = params => req('delete', "/menuManages/"+params);

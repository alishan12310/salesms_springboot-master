import req from '@/apis/request.js' 

// 添加用户
export const addNotes = params => req('post', '/indexNotes', params);

// 查询用户
export const queryNotes = params => req('get', '/indexNotes' , params);

// 修改用户信息
export const updateNotes = params => req('put', '/indexNotes', params);

// 删除用户
export const deleteNotes = params => req('delete', "/indexNotes/" + params);

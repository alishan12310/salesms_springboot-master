import req from '@/apis/request.js' 

// 添加员工
export const addEmployee = params => req('post', '/user/saveEmployee', params);

// 查询员工
export const findEmployee = params => req('get', '/user/queryEmployee', params);

// 修改员工信息
export const updateEmployee = params => req('put', '/user/updateEmployee', params);

// 委派员工
export const delegateEmployee = params => req('put', '/user/delegateEmployee', params);

// 删除员工
export const deleteEmployee = params => req('delete', '/user/deleteEmployee/' + params);

// 更改员工在职状态
export const depart = params => req('put', '/user/depart/' + params);

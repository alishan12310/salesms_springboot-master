import req from '@/apis/request.js' 

// 查找用户信息
export const searchuser = params => req('get', '/user/' + params);

// 更新用户信息
export const updatePersonalInformation = params => req('put', '/user/updatePersonalInformation', params);

//查月份
export const  getFormatYear= params => req('get', '/salesRecords/getFormatYear/' + params);

//查月份
export const  getFormatDay= params => req('get', '/salesRecords/getFormatDay', params);

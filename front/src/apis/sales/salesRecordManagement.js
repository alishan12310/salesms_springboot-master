
import req from '@/apis/request.js'

// 查所有货物
export const queryAllGoods = params => req('get', '/stockGoods/queryAllGoods', params);

// 查所有员工
export const queryAllPeople = params => req('get', '/user/queryAllPeople', params);

// 查货物详细
export const queryGoodsDetails = params => req('get', '/stockGoods/queryGoodsDetails/' + params);

// 查所有销售记录
export const getsalesRecords = params => req('get', '/salesRecords', params);

// 新增销售记录
export const addsalesRecords = params => req('post', '/salesRecords', params);

// 删除销售记录
export const delsalesRecords = params => req('delete', '/salesRecords/' +  params);

// 删除所有销售记录
export const delallsalesRecords = params => req('delete', '/salesRecords/deleteAll/' + params);

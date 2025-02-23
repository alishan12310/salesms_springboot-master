
import req from '@/apis/request.js' 

// 添加订单
export const saveOrder = params => req('post', '/purchaseOrder/saveOrder', params);

// 查询缺货记录
export const getScarceRecord = params => req('get', '/salesRecords/getScarceRecord', params);

// 修改订单信息
export const updateOrder = params => req('put', '/purchaseOrder/updateOrder', params);

// 删除订单
export const deleteOrder = params => req('delete', '/purchaseOrder/'+ params);

//查找货物类别
export const queryAllCategory = params => req('get', '/stockCategory/queryAllCategory', params);

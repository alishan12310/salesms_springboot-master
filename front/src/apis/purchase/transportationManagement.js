
import req from '@/apis/request.js' 

//: 新建物流运输
export const savelogistics = params => req('post', '/purchaseTransports' , params);

//: 查询物流运输
export const querylogistics = params => req('get', '/purchaseTransports', params);

//: 修改物流运输信息
export const updatelogistics = params => req('put', '/purchaseTransports', params);

//: 删除物流运输
export const deletelogistics = params => req('delete', '/purchaseTransports', params);

//: 查找库房
export const serachlogistics = params => req('get', '/stockStorehouse/queryStorehouse', params);

//: 获取未发货的订单
export const Unshippedorders = params => req('get', '/purchaseOrder/queryOrder', params);

//: 完成物流运输
export const successPurchaseTransport = params => req('put', '/purchaseTransports/successPurchaseTransport', params);

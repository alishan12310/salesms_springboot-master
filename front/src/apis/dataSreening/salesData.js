import req from '@/apis/request.js'


export const Storeslastmonth = params => req('get', '/salesRecords/getStoreOrders', params);

export const Hotsellinggoods = params => req('get', '/salesRecords/getSellingGoods', params);

export const Newsalestrends = params => req('get', '/salesRecords/getSaleTrend', params);

export const getNowtotalSales = params => req('get', '/salesRecords/getNowtotalSales', params);

export const getNowAlltotalSales = params => req('get', '/salesRecords/getNowAlltotalSales', params);

export const getSalesOrders = params => req('get', '/salesRecords/getSalesOrders', params);


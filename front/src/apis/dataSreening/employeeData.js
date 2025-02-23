import req from '@/apis/request.js'

export const getObtainEmployees = params => req('get', '/salesRecords/getObtainEmployees', params);

export const getEmployeeData = params => req('get', '/salesRecords/getEmployeeData', params);

export const getRevenueEmployees = params => req('get', '/salesRecords/getRevenueEmployees/' + params);

export const getSalesTrends = params => req('get', '/salesRecords/getSalesTrends/' + params);

export const getFormatDay = params => req('get', '/salesRecords/getFormatDay' , params);

export const getUser = params => req('get', '/user/' + params);

// export const getNowAlltotalSales = params => req('get', '/salesRecords/getNowAlltotalSales', params);

// export const getSalesOrders = params => req('get', '/salesRecords/getSalesOrders', params);


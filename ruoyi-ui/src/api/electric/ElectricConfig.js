import request from '@/utils/request'

// 查询宿舍电费信息的相关配置列表
export function listElectricConfig(query) {
  return request({
    url: '/electric/ElectricConfig/info',
    method: 'get',
    params: query
  })
}

// 查询宿舍电费信息的相关配置详细
export function getElectricConfig(id) {
  return request({
    url: '/electric/ElectricConfig/' + id,
    method: 'get'
  })
}

// 新增宿舍电费信息的相关配置
export function addElectricConfig(data) {
  return request({
    url: '/electric/ElectricConfig',
    method: 'post',
    data: data
  })
}

// 修改宿舍电费信息的相关配置
export function updateElectricConfig(data) {
  return request({
    url: '/electric/ElectricConfig',
    method: 'put',
    data: data
  })
}

// 删除宿舍电费信息的相关配置
export function delElectricConfig(id) {
  return request({
    url: '/electric/ElectricConfig/' + id,
    method: 'delete'
  })
}

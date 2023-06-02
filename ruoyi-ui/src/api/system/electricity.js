import request from "@/utils/request";

// 查询宿舍电费记录列表
export function listElectricity(query) {
  return request({
    url: "/api/electricity/list",
    method: "get",
    params: query,
  });
}

export function getElectricityListByDate(start, end, dormId) {
  return request({
    url:
      "/api/electricity/queryByDate?" +
      "start=" +
      start +
      "&end=" +
      end +
      "&dormId=" +
      dormId,
    method: "get",
  });
}

export function getQueryDormIds() {
  return request({
    url: "/api/electricity/queryDormIds",
    method: "get",
  });
}

// 查询宿舍电费记录详细
export function getElectricity(id) {
  return request({
    url: "/api/electricity/" + id,
    method: "get",
  });
}

// 新增宿舍电费记录
export function addElectricity(data) {
  return request({
    url: "/api/electricity",
    method: "post",
    data: data,
  });
}

// 修改宿舍电费记录
export function updateElectricity(data) {
  return request({
    url: "/api/electricity",
    method: "put",
    data: data,
  });
}

// 删除宿舍电费记录
export function delElectricity(id) {
  return request({
    url: "/api/electricity/" + id,
    method: "delete",
  });
}

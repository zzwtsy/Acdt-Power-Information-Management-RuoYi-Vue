# 本项目用于本人学习若依框架

## 简介

本项目用于获取学校电费用电记录，存储于数据库。支持多个宿舍

## 数据库设计

### electricity 表 （用于记录个宿舍用电信息）

|数据库表字段|描述|
|:--|:-|
|id|自增主键|
|dormitory_id|宿舍号|
|lighting_electricity_usage|照明电费用电量|
|air_electricity_usage|空调电费用电量|
|electric_usage_date|用电日期|

### electricity_config 表 （用于保存爬取用电信息模块的配置信息）

|数据库表字段|描述|
|:--|:-|
|id|自增主键|
|wechat_openid|微信 openid（爬取网站页面数据需要）|
|cookie|学校电费信息网站 cookie|
|dorm_ids|宿舍门牌号列表（String 数组 toString）|

## 演示图

package com.ruoyi.electric.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 宿舍电费信息的相关配置对象 electricity_config
 *
 * @author ruoyi
 * @date 2023-05-08
 */
public class ElectricityConfig extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 学校电费信息网站 cookie
     */
    private String cookie;

    /**
     * 宿舍门牌号列表
     */
    private String dormIds;

    /**
     * 微信 openid
     */
    private String wechatOpenid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public String getCookie() {
        return cookie;
    }

    public void setDormIds(String dormIds) {
        this.dormIds = dormIds;
    }

    public String getDormIds() {
        return dormIds;
    }

    public void setWechatOpenid(String wechatOpenid) {
        this.wechatOpenid = wechatOpenid;
    }

    public String getWechatOpenid() {
        return wechatOpenid;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("cookie", getCookie())
                .append("dormIds", getDormIds())
                .append("wechatOpenid", getWechatOpenid())
                .toString();
    }
}

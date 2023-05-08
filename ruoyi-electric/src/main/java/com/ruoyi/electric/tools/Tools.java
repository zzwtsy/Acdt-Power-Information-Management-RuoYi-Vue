package com.ruoyi.electric.tools;

import com.ruoyi.electric.api.Api;

import java.io.IOException;

public class Tools {

    /**
     * 判断 cookie 是否过期
     *
     * @return boolean
     */
    public static boolean isCookieExpired() {
        try {
            String s = new Api().getDormElectricityData(1, Const.COOKIE);
            return s == null && s.isEmpty();
        } catch (IOException e) {
            return true;
        }
    }

    /**
     * 将数组 toString 的字符串转换为数组
     *
     * @param string 字符串
     * @return {@link String[]}
     */
    public static String[] stringToArray(String string) {
        return string.replace("[", "")
                .replace("]", "")
                .replace(" ", "")
                .split(",");
    }
}

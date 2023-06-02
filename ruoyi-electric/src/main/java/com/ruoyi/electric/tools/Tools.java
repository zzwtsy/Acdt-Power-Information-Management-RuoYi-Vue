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

            return s == null || s.isEmpty();
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

    /**
     * 判断字符串日期格式（YYYY-MM-DD）
     *
     * @param date 日期
     * @return {@link Boolean}
     */
    public static Boolean isDate(String date) {
        return date.matches("^(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)$");
    }
}

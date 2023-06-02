package com.ruoyi.electric.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.ruoyi.electric.MyHeader;
import com.ruoyi.electric.tools.Const;
import com.ruoyi.electric.utils.JsonUtils;
import com.ruoyi.electric.utils.OkhttpUtils;
import okhttp3.Headers;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class Api {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final String HOST = "http://df.acdt.edu.cn/";

    /**
     * 获取 cookie
     *
     * @return {@link String}
     */
    public String getCookie() {
        String url = HOST + "go?openid=" + Const.wechatOpenid;
        String cookie = null;
        try {
            cookie = OkhttpUtils.get(url, null).request()
                    // http://df.acdt.edu.cn/home;jsessionid=097DFBA4F3341118C8DEE29CEBA54444
                    .url().encodedPath().split(";")[1]
                    // jsessionid 必须大写
                    .toUpperCase();
        } catch (IOException e) {
            log.error("获取 cookie 时发生错误:", e);
        }
        return cookie;
    }

    /**
     * 获取宿舍电费列表
     *
     * @param pageId 分页
     * @param cookie cookie
     * @return {@link String}
     */
    public String getDormElectricityData(int pageId, String cookie) throws IOException {
        String url = HOST + "use/record/" + pageId;

        try (Response response = OkhttpUtils.get(url, MyHeader.header(cookie))) {

            if (response.body() == null) return null;

            if (!response.isSuccessful()) return null;

            return response.body().string();
        }
    }

    /**
     * 绑定电表类型
     *
     * @param headers     headers
     * @param requestBody requestBody
     * @param room        宿舍
     * @return boolean
     * @throws IOException ioexception
     */
    public boolean bindingElectricMeterType(Headers headers, RequestBody requestBody, String room) throws IOException {
        final String url = HOST + "about/rebinding";
        JsonNode jsonNode;
        try (Response response = OkhttpUtils.post(url, headers, requestBody)) {
            if (response.body() == null) return false;
            String string = response.body().string().replace("bindinginfo", "\"bindinginfo\"");
            jsonNode = JsonUtils.toJson(string);
        }
        if (jsonNode != null) {
            return jsonNode.get(0).get("bindinginfo").asText().replace("\"", "").equals(room);
        } else {
            return false;
        }
    }
}

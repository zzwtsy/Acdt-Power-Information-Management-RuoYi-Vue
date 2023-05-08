package com.ruoyi.electric.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {
    private static final ObjectMapper mapper = new ObjectMapper();

    /**
     * 字符串转换为自定义对象
     *
     * @param content 内容
     * @param clazz   自定义对象的class对象
     * @return {@link T} 自定义对象
     * @throws JsonProcessingException json处理异常
     */
    public static <T> T stringToObject(String content, Class<T> clazz) throws JsonProcessingException {
        if (content.isEmpty() || clazz == null) {
            return null;
        }
        return mapper.readValue(content, clazz);
    }

    public static JsonNode toJson(String content) throws JsonProcessingException {
        if (content.isEmpty()) {
            return null;
        }
        return mapper.readTree(content);
    }
}
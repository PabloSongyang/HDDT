package com.creative.answer.common.util;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Pablo.风暴洋
 * @company 大连创模科技
 * @time 2020/4/24 14:38
 * @package com.creative.answer.common.util
 * @characterization Json解析工具类
 */
public class JsonUtil {
    private static Map<String,Object> resultMap = new HashMap<>();

    /**
     * 获取map
     * @return
     */
    public static Map<String,Object> getResultMap(){
        return resultMap;
    }

    /**
     * 将bean转成Json字符串
     * @param bean
     * @return
     */
    public static String beanToJSONString(Object bean){
        return new Gson().toJson(bean);
    }

    /**
     * 将Json字符串转换成对象
     * @param json
     * @param beanClass
     * @return
     */
    public static Object JSONToObject(String json, Class beanClass){
        return new Gson().fromJson(json,beanClass);
    }
}

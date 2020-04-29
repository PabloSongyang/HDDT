package com.creative.answer.controller;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Pablo.风暴洋
 * @company 大连创模科技
 * @time 2020/4/26 18:29
 * @package com.creative.answer.controller
 * @characterization 控制器父类
 */
public class BaseController{
    private Map<String, Object> resultMap = new HashMap<>();

    public Map<String, Object> getResultMap() {
        return resultMap;
    }
}

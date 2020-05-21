package com.creative.answer.config;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Pablo.风暴洋
 * @company 大连创模科技
 * @time 2020/5/20 12:53
 * @package com.creative.answer.config
 * @characterization Servlet配置
 */
public class ServletConfig {
    /**
     * 获取AJAX
     * @param request
     * @return
     */
    public static String getAJAX(HttpServletRequest request) {
        String uri = request.getRequestURI();
        String ajax = uri.substring(uri.lastIndexOf('/', uri.lastIndexOf('/') - 1) + 1);
        return ajax;
    }
}

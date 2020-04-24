package com.creative.answer.listener;

import lombok.NoArgsConstructor;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Pablo.风暴洋
 * @company 大连创模科技
 * @time 2020/4/23 13:58
 * @package com.creative.answer.listener
 * @characterization 请求监听HttpSession
 */

@NoArgsConstructor
public class RequestListener implements ServletRequestListener {


    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {

    }

    /**
     * 所有请求都加上HttpSession
     * @param servletRequestEvent
     */
    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        ((HttpServletRequest) servletRequestEvent.getServletRequest()).getSession();
    }
}

package com.creative.answer.controller;

import com.creative.answer.common.CommonData;
import com.creative.answer.config.CodeConfig;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author Pablo.风暴洋
 * @company 大连创模科技
 * @time 2020/4/23 13:58
 * @package com.creative.answer.socket
 * @characterization Java端WebSocket管理
 */

/**
 * @ServerEndpoint 注解是一个类层次的注解，它的功能主要是将目前的类定义成一个websocket服务器端,
 * 注解的值将被用于监听用户连接的终端访问URL地址,客户端可以通过这个URL来连接到WebSocket服务器端
 */
@ServerEndpoint("/websocket")
public class WebSocketController {
//    private static WebSocketController singleton = null;
//
//    public static WebSocketController getInstance(){
//        synchronized (WebSocketController.class){
//            if (singleton == null)
//                singleton = new WebSocketController();
//        }
//        return singleton;
//    }


    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    public static int onlineCount = 0;

    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
    public static CopyOnWriteArraySet<WebSocketController> webSocketManagerCopyOnWriteArraySet = new CopyOnWriteArraySet<>();

    //
    private static Map<String, HttpSession> socketNumb = new ConcurrentHashMap<>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    //整个会话
    private HttpSession httpSession;

    //消息管理map
    private Map<CodeConfig, Object> codeConfigObjectMap = new HashMap<>();

    public Map<CodeConfig, Object> getCodeConfigObjectMap() {
        return codeConfigObjectMap;
    }

    /**
     * 连接建立成功调用
     *
     * @param session session为某个客户端连接会话，需要通过其给客户端发送数据，session可选
     */
    @OnOpen
    public void onOpen(Session session, EndpointConfig endpointConfig) {
        this.session = session;
        this.httpSession = (HttpSession) endpointConfig.getUserProperties().get(HttpSession.class.getName());
        webSocketManagerCopyOnWriteArraySet.add(this);
        WebSocketController.addOnlineCount();

        System.out.println("有新的连接加入了，当前在线人数为：" + WebSocketController.getOnlineCount());
    }

    /**
     * 返回当前session
     *
     * @return
     */
    public Session getSession() {
        return this.session;
    }

    /**
     * 连接关闭调用方法
     */
    @OnClose
    public void onClose() {
        webSocketManagerCopyOnWriteArraySet.remove(this);
        WebSocketController.subOnlineCount();
        System.out.println("有一连接关闭，当前在线人数为：" + WebSocketController.getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发来的消息
     * @param session 可选参数
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("收到来自客户端的消息：" + message);
        String json = QuestionController.getInstance().onMessage(message);
        try {
            this.sendMessage(session, json);
        } catch (IOException e) {
            e.printStackTrace();
        }

        /**
         * 消息群发
         */
        if (webSocketManagerCopyOnWriteArraySet.size() != 0) {
            for (WebSocketController webSocketManager : webSocketManagerCopyOnWriteArraySet) {
                if (webSocketManager != null) {
                    try {
                        if (this.session != webSocketManager.session)
                            webSocketManager.sendMessage(webSocketManager.session, message);
                    } catch (IOException e) {
                        e.printStackTrace();
                        continue;
                    }
                }
            }
        }
    }

    /**
     * 发生错误时调用
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
        onClose();
    }

    /**
     * 发送消息(针对服务器向终端和web端群发)
     *
     * @param message
     * @throws IOException
     */
    public void sendMessage(Session session, String message) throws IOException {
        synchronized (session) {
            session.getBasicRemote().sendText(message);
        }
    }

    /**
     * 发送消息(针对web端向服务器或终端单独通知)
     *
     * @param message
     * @throws IOException
     */
    public void sendSingleMessage(String message) throws IOException {
        synchronized (this.session) {
            this.session.getBasicRemote().sendText(message);
        }
    }

    /**
     * 获取在线数
     *
     * @return
     */
    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    /**
     * 在线+1
     */
    public static synchronized void addOnlineCount() {
        WebSocketController.onlineCount++;
    }

    /**
     * 在线-1
     */
    public static synchronized void subOnlineCount() {
        WebSocketController.onlineCount--;
    }
}

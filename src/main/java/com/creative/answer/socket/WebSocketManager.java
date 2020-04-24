package com.creative.answer.socket;

import com.creative.answer.bean.BigScreenBean;
import com.creative.answer.common.util.JsonUtil;
import com.creative.answer.config.GetHttpSessionConfigurator;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
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
@ServerEndpoint(value = "/websocket",configurator = GetHttpSessionConfigurator.class)
public class WebSocketManager {
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;

    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
    private static CopyOnWriteArraySet<WebSocketManager> webSocketManagerCopyOnWriteArraySet = new CopyOnWriteArraySet<>();

    //
    private static Map<String,HttpSession> socketNumb = new ConcurrentHashMap<>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    //整个会话
    private HttpSession httpSession;

    private static boolean isBigScreen = false;

    /**
     * 连接建立成功调用
     *
     * @param session session为某个客户端连接会话，需要通过其给客户端发送数据，session可选
     */
    @OnOpen
    public void onOpen(Session session,EndpointConfig endpointConfig) {
        this.session = session;
        this.httpSession = (HttpSession) endpointConfig.getUserProperties().get(HttpSession.class.getName());
        webSocketManagerCopyOnWriteArraySet.add(this);
        WebSocketManager.addOnlineCount();

        System.out.println("有新的连接加入了，当前在线人数为：" + WebSocketManager.getOnlineCount());
    }

    /**
     * 连接关闭调用方法
     */
    @OnClose
    public void onClose() {
        webSocketManagerCopyOnWriteArraySet.remove(this);
        WebSocketManager.subOnlineCount();
        System.out.println("有一连接关闭，当前在线人数为：" + WebSocketManager.getOnlineCount());
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


        /**
         * 消息群发
         */
        for (WebSocketManager webSocketManager : webSocketManagerCopyOnWriteArraySet) {
            try {
                webSocketManager.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
                continue;
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
    }

    /**
     * 发送消息
     *
     * @param message
     * @throws IOException
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
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
        onlineCount = WebSocketManager.getOnlineCount();
        onlineCount++;
    }

    /**
     * 在线-1
     */
    public static synchronized void subOnlineCount() {
        onlineCount = WebSocketManager.getOnlineCount();
        onlineCount--;
    }
}

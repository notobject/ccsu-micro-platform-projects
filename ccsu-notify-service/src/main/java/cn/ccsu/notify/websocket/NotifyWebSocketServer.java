package cn.ccsu.notify.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author hangs.zhang
 * @date 2018/12/11
 * *****************
 * function:
 */
@Slf4j
@Component
@ServerEndpoint("/websocket/{userId}")
public class NotifyWebSocketServer {

    // 用来记录当前在线连接数
    private static AtomicInteger onlineCount = new AtomicInteger(0);

    private static ConcurrentHashMap<Integer, NotifyWebSocketServer> webSocketServerConcurrentHashMap
            = new ConcurrentHashMap<>();

    // 与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    // 接收userId
    private Integer userId;

    @OnOpen
    public void onOpen(Session session, @PathParam("userId") Integer userId) {
        this.session = session;
        webSocketServerConcurrentHashMap.put(userId, this);
        // 加入set中
        addOnlineCount();
        // 在线数加1
        log.info("新窗口开始监听:" + userId + ",当前在线人数为" + getOnlineCount());
        this.userId = userId;
        try {
            sendMessage("连接成功");
        } catch (IOException e) {
            log.error("websocket IO异常");
        }
    }

    @OnClose
    public void onClose() {
        // 从map中删除
        webSocketServerConcurrentHashMap.remove(userId);
        // 在线数减1
        subOnlineCount();
        log.info("userId 连接关闭! 当前在线人数为 " + getOnlineCount());
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("收到来自userId为 : " + userId + "的信息:" + message);
    }

    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误");
        error.printStackTrace();
    }

    private void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }


    public static void sendInfo(String message, Integer userId) {
        log.info("推送消息到userId : {}, 推送消息 : {}", userId, message);
        NotifyWebSocketServer webSocketServer = webSocketServerConcurrentHashMap.get(userId);
        if (webSocketServer == null) return;
        try {
            webSocketServer.sendMessage(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int getOnlineCount() {
        return onlineCount.get();
    }

    private static void addOnlineCount() {
        NotifyWebSocketServer.onlineCount.getAndIncrement();
    }

    private static void subOnlineCount() {
        NotifyWebSocketServer.onlineCount.getAndDecrement();
    }
}
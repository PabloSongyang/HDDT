package com.creative.answer.dao;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Pablo.风暴洋
 * @company 大连创模科技
 * @time 2020/4/24 15:12
 * @package com.creative.answer.dao
 * @characterization 在这里添加类的功能描述
 */
public class DataDAO {
    private static DataDAO singleton = null;
    private Queue<Object> dataQueue = new LinkedList<>();

    /**
     * 线程安全的单例
     *
     * @return
     */
    public static DataDAO getInstance() {
        synchronized (DataDAO.class) {
            if (singleton == null)
                singleton = new DataDAO();
            return singleton;
        }
    }

    /**
     * 初始化
     */
    public void init() {
        if (dataQueue.size() > 0) {
            for (Object o : dataQueue) {
                dataQueue.remove(o);
            }
        }
    }

    /**
     * 消息入队
     *
     * @param o
     */
    public void enqueue(Object o) {
        dataQueue.add(o);
    }

    /**
     * 消息退队
     * @param o
     * @return
     */
    public boolean dequeue(Object o) {
        boolean isRemove = false;
        if (dataQueue.contains(o))
            isRemove = dataQueue.remove(o);

        return isRemove;
    }
}

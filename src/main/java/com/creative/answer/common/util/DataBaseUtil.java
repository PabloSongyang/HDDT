package com.creative.answer.common.util;

import com.creative.answer.common.db.CurrencyDataSource;
import com.creative.answer.common.db.DB_VO;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Pablo.风暴洋
 * @company 大连创模科技
 * @time 2020/4/26 10:01
 * @package com.creative.answer.common.util
 * @characterization 在这里添加类的功能描述
 */
public class DataBaseUtil {
    private static DataBaseUtil singleton = null;

    /**
     * 线程安全的单例
     * @return
     */
    public static DataBaseUtil getInstance() {
        synchronized (DataBaseUtil.class){
            if (singleton == null)
                singleton = new DataBaseUtil();
        }
        return singleton;
    }

    /**
     * 获取连接
     *
     * @return
     */
    public Connection getConnection() {
        return CurrencyDataSource.getInstance().getConnection();
    }

    /**
     * 数据更新
     *
     * @param po
     * @return
     */
    public int executeUpdate(DB_VO po) {
        try {
            return po.preparedStatement.executeUpdate();
        } catch (SQLException e) {
            return -1;
        }
    }

    /**
     * 数据查询
     *
     * @param po
     */
    public void executeQuery(DB_VO po) {
        try {
            po.resultSet = po.preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭连接
     *
     * @param po
     */
    public void close(DB_VO po) {
        if (po != null) {
            if (po.resultSet != null && po.preparedStatement != null && po.connection != null) {
                try {
                    po.resultSet.close();
                    po.preparedStatement.close();
                    po.connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}

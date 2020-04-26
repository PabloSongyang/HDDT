package com.creative.answer.common.db;

import com.alibaba.druid.pool.DruidDataSource;
import com.creative.answer.config.DataBaseConfig;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Pablo.风暴洋
 * @company 大连创模科技
 * @time 2020/4/26 9:39
 * @package com.creative.answer.common.db
 * @characterization 数据库连接池
 */
public class CurrencyDataSource {
    private static DruidDataSource dataSource;

    private static CurrencyDataSource singleton = null;

    /**
     * 线程安全单例
     * @return
     */
    public static CurrencyDataSource getInstance() {
        synchronized (CurrencyDataSource.class){
            if (singleton == null)
                singleton = new CurrencyDataSource();
        }
        return singleton;
    }

    static {
        dataSource = new DruidDataSource();
        dataSource.setDriverClassName(DataBaseConfig.SQLSERVER_DRIVER_CLASS);
        dataSource.setUrl            (DataBaseConfig.SQLSERVER_URL);
        dataSource.setUsername       (DataBaseConfig.SQLSERVER_USER);
        dataSource.setPassword       (DataBaseConfig.SQLSERVER_PASSWORD);
        dataSource.setInitialSize    (DataBaseConfig.INITIAL_SIZE);
        dataSource.setMaxActive      (DataBaseConfig.MAX_ACTIVE);
        dataSource.setMinIdle        (DataBaseConfig.MIN_IDLE);
        dataSource.setMaxWait        (DataBaseConfig.MAX_WAIT);
    }

    /**
     * 获取连接
     * @return
     * @throws SQLException
     */
    public Connection getConnection(){
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            return null;
        }
    }

}

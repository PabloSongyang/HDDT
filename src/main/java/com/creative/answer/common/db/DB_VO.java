package com.creative.answer.common.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Pablo.风暴洋
 * @company 大连创模科技
 * @time 2020/4/26 9:41
 * @package com.creative.answer.common.db
 * @characterization 数据库结果桥联
 */
public class DB_VO {
    public Connection connection;
    public PreparedStatement preparedStatement;
    public ResultSet resultSet;
}

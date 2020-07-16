package com.creative.answer.config;

/**
 * @author Pablo.风暴洋
 * @company 大连创模科技
 * @time 2020/4/26 9:33
 * @package com.creative.answer.config
 * @characterization 数据库信息配置
 */
public class DataBaseConfig {
    //Oracle驱动
    public static final String ORACLE_DRIVER_CLASS    = "oracle.jdbc.driver.OracleDriver";

    //Oracle连接必要信息
    public static final String ORACLE_URL             = "jdbc:oracle:thin:@localhost:1521:orcl";
    public static final String ORACLE_USER            = "scott";
    public static final String ORACLE_PASSWORD        = "tiger";

    //MySQL驱动
    public static final String MYSQL_DRIVER_CLASS     = "com.mysql.jdbc.Driver";

    //MySQL连接必要信息
    public static final String MYSQL_URL              = "jdbc:mysql://localhost:3306/db_vegetable?useUnicode=true&characterEncoding=UTF-8";
    public static final String MYSQL_USER             = "root";
    public static final String MYSQL_PASSWORD         = "root";

    //SqlServer驱动
    public static final String SQLSERVER_DRIVER_CLASS = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

    //SqlServer连接必要信息
    public static final String SQLSERVER_URL          = "jdbc:sqlserver://218.61.208.71:1433;DatabaseName=cmkj_box_native";
    public static final String SQLSERVER_USER         = "sa";
    public static final String SQLSERVER_PASSWORD     = "cmkj@CMKJ";//71服务器"cmkj@CMKJ"  本地"sqlserverroot"



    //通用
    public static final int    INITIAL_SIZE = 10;
    public static final int    MAX_ACTIVE   = 50;
    public static final int    MIN_IDLE     = 10;
    public static final int    MAX_WAIT     = 120000;

}

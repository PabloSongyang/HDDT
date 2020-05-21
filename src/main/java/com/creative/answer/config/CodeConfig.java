package com.creative.answer.config;

/**
 * @author Pablo.风暴洋
 * @company 大连创模科技
 * @time 2020/4/26 16:30
 * @package com.creative.answer.config
 * @characterization 消息号配置
 */
public class CodeConfig {
    //获取答完每一道题之后的玩家排行数据
    public static final int GET_RANK_INFO_IN_ROUND = 11;

    //获取所有的题目信息
    public static final int GET_ALL_QUESTIONS      = 1001;

    //获取所有的选项信息
    public static final int GET_ALL_OPTIONS        = 1002;

    //请求开始答题，倒计时
    public static final int GET_START_QUESTIONS    = 1003;

    //获取最终的排行
    public static final int GET_FINISH_RANKING     = 1007;

    //中途退出游戏
    public static final int EXIT_GAME              = 1008;
}

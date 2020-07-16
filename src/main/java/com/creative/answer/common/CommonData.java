package com.creative.answer.common;

import com.creative.answer.bean.RankBean;

import java.util.*;

/**
 * @author Pablo.风暴洋
 * @company 大连创模科技
 * @time 2020/4/24 14:03
 * @package com.creative.answer.common
 * @characterization 通用属性
 */
public class CommonData {
    public static int state = 1;
    public static int timeCutDown = 20;
    public static boolean isStart = false;
    public static String big_screen = "";
    public static int playerNumber = 0;
    public static int hall_status = 0;

    public static Map<String,Object> rankMap = new HashMap<>();
    public static List<Map<String, Object>> rankMapList = new ArrayList<>();
    public static List<RankBean> rankBeanList = new ArrayList<>();
    public static List<String> roomIdList = new ArrayList<>();

}

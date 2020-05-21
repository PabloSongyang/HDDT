package com.creative.answer.bean;

/**
 * @author Pablo.风暴洋
 * @company 大连创模科技
 * @time 2020/4/27 11:21
 * @package com.creative.answer.bean
 * @characterization Unity发来的题目json消息
 */
public class QuestionFromUnityBean {
    public int code;
    public String result;
    public Integer number;
    public int isAnswered;
    public String uuid;
    public Data data;

    public static class Data{
        public String Count;
        public String id;
        public String status;
        public User user;
        public String playerNumber;
        public String isEnd;


        public static class User{
            public String uuid;
            public String name;
            public String imageUri;
        }
    }


}

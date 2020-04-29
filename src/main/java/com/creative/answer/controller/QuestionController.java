package com.creative.answer.controller;

import com.creative.answer.Service.AnswerService;
import com.creative.answer.Service.QuestionService;
import com.creative.answer.Service.impl.AnswerServiceImpl;
import com.creative.answer.Service.impl.QuestionServiceImpl;
import com.creative.answer.bean.AnswerBean;
import com.creative.answer.bean.QuestionBean;
import com.creative.answer.bean.QuestionFromUnityBean;
import com.creative.answer.common.CommonData;
import com.creative.answer.config.CodeConfig;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.websocket.Session;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Pablo.风暴洋
 * @company 大连创模科技
 * @time 2020/4/26 16:05
 * @package com.creative.answer.controller
 * @characterization 题目控制层
 */

public class QuestionController extends BaseController{
    private static QuestionController singleton = null;
    private QuestionService questionService = new QuestionServiceImpl();
    private AnswerService answerService = new AnswerServiceImpl();
    private Gson gson = new Gson();
    private boolean isStart = false;

    public static QuestionController getInstance() {
        synchronized (QuestionController.class) {
            if (singleton == null)
                singleton = new QuestionController();
        }
        return singleton;
    }

    /**
     * 获取消息
     *
     * @param message
     * @param session
     * @return
     */
    public String onMessage(String message, Session session) {
        String sendJson = "";
//        CommonData.isStart = false;
        System.out.println("unity发来消息：" + message);
        if (message.contains("code")) {

            QuestionFromUnityBean o = null;
            try {
                o = gson.fromJson(message, new TypeToken<QuestionFromUnityBean>() {
                }.getType());
            } catch (Exception e) {
                e.printStackTrace();
            }


            switch (o.code) {
                //请求题目数量及内容
                case CodeConfig.GET_ALL_QUESTIONS:
                    super.getResultMap().clear();
                    List<QuestionBean> questionBeanList = new ArrayList<>();
                    switch (o.data.Count) {
                        case "20":
                            questionBeanList = questionService.getAllQuestions(Integer.parseInt(o.data.Count));
                            break;
                        case "40":
                            questionBeanList = questionService.getAllQuestions(Integer.parseInt(o.data.Count));
                            break;
                        case "60":
                            questionBeanList = questionService.getAllQuestions(Integer.parseInt(o.data.Count));
                            break;
                    }
                    if (questionBeanList.size() > 0) {
                        super.getResultMap().put("msg", "ok");
                        super.getResultMap().put("code", 8);
                        super.getResultMap().put("flag", CodeConfig.GET_ALL_QUESTIONS);
                        super.getResultMap().put("question", questionBeanList);
                    }
                    sendJson = gson.toJson(super.getResultMap());
                    System.out.println(sendJson);
                    break;

                //请求题目选项数据
                case CodeConfig.GET_ALL_OPTIONS:
                    super.getResultMap().clear();
                    String questionId = o.data.id;
                    System.out.println("questionId ==>> " + questionId);
                    List<AnswerBean> answerBeanList = answerService.getAnswerOptionsByQuestionsID(questionId);
                    if (answerBeanList.size() > 0) {
                        super.getResultMap().put("msg", "ok");
                        super.getResultMap().put("code", 9);
                        super.getResultMap().put("flag", CodeConfig.GET_ALL_OPTIONS);
                        super.getResultMap().put("option", answerBeanList);
                    }
                    sendJson = gson.toJson(super.getResultMap());
                    System.out.println(sendJson);
                    break;

                case CodeConfig.GET_START_QUESTIONS:
//                    CommonData.isStart = true;
                    super.getResultMap().clear();
                    if ("start".equals(o.data.status)) {
                        super.getResultMap().put("time", 3);
                        super.getResultMap().put("code", 3);
                        super.getResultMap().put("number", 2);
                        super.getResultMap().put("state", CommonData.state);
                    }
                    sendJson = gson.toJson(super.getResultMap());
                    System.out.println(sendJson);
                case 1004:

                    break;
            }
        }
        return sendJson;
    }

    public boolean reqStart(){
        return isStart;
    }

//
//    @Override
//    public void run() {
//        Thread.currentThread().setName("CUTDOWN");
//        System.out.println("执行答题倒计时线程");
////        int count = 20;
////        for (int i = 20; i >= 0; i--) {
////            count = i;
////            System.out.println(count);
////            try {
////                Thread.sleep(1000);
////            } catch (InterruptedException e) {
////                e.printStackTrace();
////            }
////        }
//    }
}

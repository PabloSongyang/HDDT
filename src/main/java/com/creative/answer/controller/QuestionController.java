package com.creative.answer.controller;

import com.creative.answer.Service.QuestionService;
import com.creative.answer.Service.impl.QuestionServiceImpl;
import com.creative.answer.bean.QuestionBean;
import com.creative.answer.bean.QuestionCountBean;
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

public class QuestionController extends BaseController {
    private static QuestionController singleton = null;
    private QuestionService questionService = new QuestionServiceImpl();
    private Gson gson = new Gson();

    public static QuestionController getInstance() {
        synchronized (QuestionController.class) {
            if (singleton == null)
                singleton = new QuestionController();
        }
        return singleton;
    }

    public void getAllQuestions() {
    }

    public String onMessage(String message, Session session) {
//        List<QuestionBean> questionBeanList = questionService.getAllQuestions();
//        gson.toJson(questionBeanList);
        String json = "";

        System.out.println("unity发来消息：" + message);
        if (message.contains("code")) {

            QuestionCountBean o = null;
            try {
                o = gson.fromJson(message, new TypeToken<QuestionCountBean>() {
                }.getType());
            } catch (Exception e) {
                e.printStackTrace();
            }


            switch (o.code) {
                case 2:

                    break;
                case 8:
                    super.getResultMap().clear();
                    List<QuestionBean> questionBeanList = new ArrayList<>();
                    switch (o.data.Count) {
                        case "20":
                            questionBeanList = questionService.getAllQuestions(Integer.parseInt(o.data.Count));

                            System.out.println(questionBeanList.size());
                            break;
                        case "40":
                            questionBeanList = questionService.getAllQuestions(Integer.parseInt(o.data.Count));
                            System.out.println(questionBeanList.size());
                            break;
                        case "60":
                            questionBeanList = questionService.getAllQuestions(Integer.parseInt(o.data.Count));
                            System.out.println(questionBeanList.size());
                            break;
                    }
                    if (questionBeanList.size() > 0){
                        super.getResultMap().put("msg","发送成功");
                        super.getResultMap().put("code",1);
                        super.getResultMap().put("question",questionBeanList);
                    }
                    json = "发给unityjson：" + gson.toJson(super.getResultMap());
                    break;
            }
        }
        return json;
    }

    public void sendMessage(String message) throws IOException {

    }
}

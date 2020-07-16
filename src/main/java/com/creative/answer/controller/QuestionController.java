package com.creative.answer.controller;

import com.creative.answer.Service.AnswerService;
import com.creative.answer.Service.QuestionService;
import com.creative.answer.Service.impl.AnswerServiceImpl;
import com.creative.answer.Service.impl.QuestionServiceImpl;
import com.creative.answer.bean.AnswerBean;
import com.creative.answer.bean.QuestionBean;
import com.creative.answer.bean.QuestionFromUnityBean;
import com.creative.answer.bean.RankBean;
import com.creative.answer.common.CommonData;
import com.creative.answer.config.CodeConfig;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.websocket.Session;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private AnswerService answerService = new AnswerServiceImpl();
    private List<QuestionBean> questionBeanList = new ArrayList<>();

    private Gson gson = new Gson();

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
     * @return
     */
    public String onMessage(String message) {
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
                case 2:
//                    super.getResultMap().clear();
//                    QuestionFromUnityBean.Data.User user = o.data.user;
//                    System.out.println("UUID -->> " + user.uuid);
//                    super.getResultMap().put("msg", "ok");
//                    super.getResultMap().put("code", 9999);
//                    super.getResultMap().put("uuid", user.uuid);
//                    super.getResultMap().put("name", user.name);
//                    super.getResultMap().put("imageUri", user.imageUri);
//                    sendJson = gson.toJson(super.getResultMap());
//                    System.out.println(sendJson);
                    break;

                case CodeConfig.GET_RANK_INFO_IN_ROUND:
//                    if (CommonData.rankBeanList.size() > 0) {
//                        CommonData.rankBeanList.clear();
//                    }
                    RankBean rankBean = new RankBean(o.uuid, o.number);
                    CommonData.rankBeanList.add(rankBean);

//                    if (rankBean.getUuid().equals(o.uuid)) {
//                        System.out.println("存在这个玩家:" + rankBean.getUuid());
//                        if (CommonData.rankBeanList.contains(rankBean))
//                        {
//
//                        }
//                        else{
//                            CommonData.rankBeanList.remove(rankBean);
//                            CommonData.rankBeanList.add(rankBean);
//                        }
////                        if (!CommonData.rankBeanList.contains(rankBean))
////                            CommonData.rankBeanList.add(rankBean);
////                        else
////                        {
////
////                        }
//                    } else {
//                        System.out.println("不存在这个玩家");
//                    }
//
//
//                    for (int i = 0; i < CommonData.playerNumber; i++) {
//                        if (!rankBean.getUuid().equals(CommonData.rankBeanList.get(i).getUuid())) {
//                            CommonData.rankBeanList.add(rankBean);
//                        } else {
//                            CommonData.rankBeanList.get(i).setNumber(o.number);
//                        }
//                    }

//                    boolean have = false;
//                    int index = 0;
//                    if (CommonData.rankBeanList.size() > 0) {
//                        for (int i = 0; i < CommonData.playerNumber; i++) {
//                            if (o.uuid.equals(CommonData.rankBeanList.get(i).getUuid())) {
//                                CommonData.rankBeanList.get(i).setNumber(o.number);
//                            } else {
//                                have = true;
//                                System.out.println("打印" + i + "次");
//                                break;
////                                CommonData.rankBeanList.add(rankBean);
////                                System.out.println("打印" + i + "次");
//                            }
//                        }
//
//                        if (have)
//                            CommonData.rankBeanList.add(rankBean);
//
//
//                    } else {
//                        CommonData.rankBeanList.add(rankBean);
//                    }


//                    CommonData.rankBeanList.forEach(r -> System.out.println("排行榜 -->>  " + r));
                    break;

                case CodeConfig.GET_ALL_QUESTIONS:
                    super.getResultMap().clear();
                    CommonData.rankBeanList.clear();
                    switch (o.data.Count) {
                        case "5"://测试用的
                            questionBeanList = questionService.getAllQuestions(Integer.parseInt(o.data.Count));
                            break;
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
                        super.getResultMap().put("all_question", questionBeanList);
                        super.getResultMap().put("option", answerBeanList);
                        super.getResultMap().put("rank", CommonData.rankBeanList);
                    }
                    sendJson = gson.toJson(super.getResultMap());
                    System.out.println(sendJson);
                    break;

                case CodeConfig.GET_START_QUESTIONS:
//                    CommonData.isStart = true;
                    super.getResultMap().clear();
                    CommonData.rankBeanList.clear();
                    super.getResultMap().put("time", 8);
                    super.getResultMap().put("code", 3);
                    super.getResultMap().put("number", 2);
                    super.getResultMap().put("state", CommonData.state);
                    sendJson = gson.toJson(super.getResultMap());
                    System.out.println(sendJson);
                    break;
                case CodeConfig.GET_FINISH_RANKING:
                    super.getResultMap().clear();
                    super.getResultMap().put("msg", "ok");
                    super.getResultMap().put("code", 12);
                    super.getResultMap().put("ranking", CommonData.rankBeanList);
                    sendJson = gson.toJson(super.getResultMap());
                    System.out.println(sendJson);
                    break;
                case CodeConfig.EXIT_GAME:
                    super.getResultMap().clear();
                    WebSocketController.webSocketManagerCopyOnWriteArraySet.clear();
                    System.out.println("Unity端关闭，当前在线人数为：" + WebSocketController.getOnlineCount());

                    break;
                case CodeConfig.ROOM_CTRL:
                    super.getResultMap().clear();
                    if (o.data.roomId != null) {
                        CommonData.roomIdList.add(o.data.roomId);
                    }
                    super.getResultMap().put("msg", "ok");
                    super.getResultMap().put("code", 16);
                    super.getResultMap().put("roomIdList", CommonData.roomIdList);
                    sendJson = gson.toJson(super.getResultMap());
                    System.out.println(sendJson);


                    break;

            }
        }
        return sendJson;
    }

}

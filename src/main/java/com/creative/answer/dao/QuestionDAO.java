package com.creative.answer.dao;

import com.creative.answer.bean.QuestionBean;

import java.util.List;

/**
 * @author Pablo.风暴洋
 * @company 大连创模科技
 * @time 2020/4/26 14:18
 * @package com.creative.answer.dao
 * @characterization 题目数据持久层接口
 */
public interface QuestionDAO {
    /**
     * 获取所有题目
     * @return
     */
    List<QuestionBean> getAllQuestions(Integer count);

    /**
     * 根据题号获取对应题目
     * @param number
     * @return
     */
    QuestionBean getQuestionByNumber(Integer number);
}

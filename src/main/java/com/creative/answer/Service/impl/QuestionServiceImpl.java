package com.creative.answer.Service.impl;

import com.creative.answer.Service.QuestionService;
import com.creative.answer.bean.QuestionBean;
import com.creative.answer.dao.QuestionDAO;
import com.creative.answer.dao.impl.QuestionDAOImpl;

import java.util.List;

/**
 * @author Pablo.风暴洋
 * @company 大连创模科技
 * @time 2020/4/26 14:37
 * @package com.creative.answer.Service.impl
 * @characterization 问题事务层接口实现
 */
public class QuestionServiceImpl implements QuestionService {
    private QuestionDAO questionDAO = new QuestionDAOImpl();

    @Override
    public List<QuestionBean> getAllQuestions(Integer count) {
        return questionDAO.getAllQuestions(count);
    }

    @Override
    public QuestionBean getQuestionByNumber(Integer number) {
        return questionDAO.getQuestionByNumber(number);
    }
}

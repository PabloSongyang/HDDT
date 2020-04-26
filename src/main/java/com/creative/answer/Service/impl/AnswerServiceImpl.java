package com.creative.answer.Service.impl;

import com.creative.answer.Service.AnswerService;
import com.creative.answer.bean.AnswerBean;
import com.creative.answer.dao.AnswerDAO;
import com.creative.answer.dao.impl.AnswerDAOImpl;

import java.util.List;

/**
 * @author Pablo.风暴洋
 * @company 大连创模科技
 * @time 2020/4/26 15:41
 * @package com.creative.answer.Service.impl
 * @characterization 答案选项事务层接口实现
 */
public class AnswerServiceImpl implements AnswerService {
    private AnswerDAO answerDAO = new AnswerDAOImpl();

    @Override
    public List<AnswerBean> getAnswerOptionsByQuestionsID(String Questions_ID) {
        return answerDAO.getAnswerOptionsByQuestionsID(Questions_ID);
    }
}

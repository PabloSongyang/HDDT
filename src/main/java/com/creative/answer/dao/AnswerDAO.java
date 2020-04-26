package com.creative.answer.dao;

import com.creative.answer.bean.AnswerBean;

import java.util.List;

/**
 * @author Pablo.风暴洋
 * @company 大连创模科技
 * @time 2020/4/26 15:26
 * @package com.creative.answer.dao
 * @characterization 答案选项数据层接口
 */
public interface AnswerDAO {
    List<AnswerBean> getAnswerOptionsByQuestionsID(String Questions_ID);
}

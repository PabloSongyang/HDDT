package com.creative.answer.Service;

import com.creative.answer.bean.AnswerBean;

import java.util.List;

/**
 * @author Pablo.风暴洋
 * @company 大连创模科技
 * @time 2020/4/26 15:40
 * @package com.creative.answer.Service
 * @characterization 答案选项事务层接口
 */
public interface AnswerService {
    List<AnswerBean> getAnswerOptionsByQuestionsID(String Questions_ID);
}

package com.creative.answer.test;

import com.creative.answer.Service.AnswerService;
import com.creative.answer.Service.impl.AnswerServiceImpl;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Pablo.风暴洋
 * @company 大连创模科技
 * @time 2020/4/26 16:01
 * @package com.creative.answer.test
 * @characterization 答案选项测试
 */
public class AnswerTest {
    private AnswerService answerService;

    @Before
    public void init() {
        answerService = new AnswerServiceImpl();
    }

    @Test
    public void getAnswerOptionsByQuestionsIDTest() {
        answerService.getAnswerOptionsByQuestionsID("91D7FF24-9D6D-479A-A79B-25ADA07E0D16").forEach(answerBean -> System.out.println(answerBean));
    }
}

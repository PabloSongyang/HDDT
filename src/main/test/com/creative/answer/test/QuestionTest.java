package com.creative.answer.test;

import com.creative.answer.Service.QuestionService;
import com.creative.answer.Service.impl.QuestionServiceImpl;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Pablo.风暴洋
 * @company 大连创模科技
 * @time 2020/4/26 14:40
 * @package com.creative.answer.test
 * @characterization 题目数据测试
 */
public class QuestionTest {
    private QuestionService questionService;

    @Before
    public void init(){
        questionService = new QuestionServiceImpl();
    }

    @Test
    public void getAllQuestionsTest(){
        questionService.getAllQuestions(40).forEach(questionBean -> System.out.println(questionBean));
    }

    @Test
    public void getQuestionByNumberTest(){
        System.out.println(questionService.getQuestionByNumber(1));
    }
}

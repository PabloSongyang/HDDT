package com.creative.answer.config;

/**
 * @author Pablo.风暴洋
 * @company 大连创模科技
 * @time 2020/4/26 9:45
 * @package com.creative.answer.config
 * @characterization Sql语句
 */
public class SqlConfig {
    //快问快答题目
    public static final String GET_ALL_QUESTIONS                    = "select * from dbo.Questions q where q.Sences_ID='69C498E5-0C1E-49D3-B84D-49EDA50FAF6A' and q.Del=0 and q.Number<=? order by q.Number";
    public static final String GET_QUESTION_BY_NUMBER               = "select * from dbo.Questions q where q.Sences_ID='69C498E5-0C1E-49D3-B84D-49EDA50FAF6A' and q.Del=0 and q.Number=? order by q.Number";

    //题目对应选项
    public static final String GET_ANSWER_OPTIONS_BY_QUESTIONS_ID   = "select * from dbo.QuestionOptions qo where qo.Questions_ID=? order by qo.OrderNumber";
}

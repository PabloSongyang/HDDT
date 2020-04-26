package com.creative.answer.dao.impl;

import com.creative.answer.bean.AnswerBean;
import com.creative.answer.common.db.DB_VO;
import com.creative.answer.common.util.DataBaseUtil;
import com.creative.answer.config.SqlConfig;
import com.creative.answer.dao.AnswerDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Pablo.风暴洋
 * @company 大连创模科技
 * @time 2020/4/26 15:27
 * @package com.creative.answer.dao.impl
 * @characterization 答案选项数据层接口实现
 */
public class AnswerDAOImpl implements AnswerDAO {
    @Override
    public List<AnswerBean> getAnswerOptionsByQuestionsID(String Questions_ID) {
        String sql = SqlConfig.GET_ANSWER_OPTIONS_BY_QUESTIONS_ID;

        DB_VO db_vo = new DB_VO();
        DataBaseUtil dataBaseUtil = DataBaseUtil.getInstance();

        db_vo.connection = dataBaseUtil.getConnection();

        List<AnswerBean> answerOptionsList = new ArrayList<>();

        try {
            db_vo.preparedStatement = db_vo.connection.prepareStatement(sql);
            db_vo.preparedStatement.setString(1,Questions_ID);
            dataBaseUtil.executeQuery(db_vo);

            while (db_vo.resultSet.next()){
                AnswerBean answerBean = new AnswerBean();
                answerBean.setId(db_vo.resultSet.getString(1));
                answerBean.setOrderNumber(db_vo.resultSet.getInt(2));
                answerBean.setTitle(db_vo.resultSet.getString(3));
                answerBean.setAnswerOption(db_vo.resultSet.getString(4));
                answerBean.setAnswer(db_vo.resultSet.getInt(5));
                answerBean.setQuestions_ID(db_vo.resultSet.getString(6));
                answerBean.setInsertTime(db_vo.resultSet.getTimestamp(7));
                answerBean.setDel(db_vo.resultSet.getInt(8));
                answerOptionsList.add(answerBean);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dataBaseUtil.close(db_vo);
        }

        return answerOptionsList;
    }
}

package com.creative.answer.dao.impl;

import com.creative.answer.bean.QuestionBean;
import com.creative.answer.common.db.DB_VO;
import com.creative.answer.common.util.DataBaseUtil;
import com.creative.answer.config.SqlConfig;
import com.creative.answer.dao.QuestionDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Pablo.风暴洋
 * @company 大连创模科技
 * @time 2020/4/26 14:20
 * @package com.creative.answer.dao.impl
 * @characterization 问题数据层实现
 */
public class QuestionDAOImpl implements QuestionDAO {
    @Override
    public List<QuestionBean> getAllQuestions(Integer count) {

        String sql = SqlConfig.GET_ALL_QUESTIONS;

        DB_VO db_vo = new DB_VO();
        DataBaseUtil dataBaseUtil = DataBaseUtil.getInstance();

        db_vo.connection = dataBaseUtil.getConnection();

        List<QuestionBean> questionList = new ArrayList<>();

        try {
            db_vo.preparedStatement = db_vo.connection.prepareStatement(sql);
            db_vo.preparedStatement.setInt(1, count);
            dataBaseUtil.executeQuery(db_vo);

            while (db_vo.resultSet.next()) {
                QuestionBean questionBean = new QuestionBean();
                this.setQuestionBean(questionBean, db_vo);
                questionList.add(questionBean);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dataBaseUtil.close(db_vo);
        }

        return questionList;
    }

    @Override
    public QuestionBean getQuestionByNumber(Integer number) {
        String sql = SqlConfig.GET_QUESTION_BY_NUMBER;

        DB_VO db_vo = new DB_VO();
        DataBaseUtil dataBaseUtil = DataBaseUtil.getInstance();

        db_vo.connection = dataBaseUtil.getConnection();

        QuestionBean questionBean = null;

        try {
            db_vo.preparedStatement = db_vo.connection.prepareStatement(sql);
            db_vo.preparedStatement.setInt(1, number);
            dataBaseUtil.executeQuery(db_vo);

            if (db_vo.resultSet.next()) {
                questionBean = new QuestionBean();
                this.setQuestionBean(questionBean, db_vo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dataBaseUtil.close(db_vo);
        }


        return questionBean;
    }

    /**
     * 设置题目实体数据
     *
     * @param questionBean
     * @param db_vo
     */
    private void setQuestionBean(QuestionBean questionBean, DB_VO db_vo) {
        try {
            questionBean.setId(db_vo.resultSet.getString(1));
            questionBean.setTitle(db_vo.resultSet.getString(2));
            questionBean.setNumber(db_vo.resultSet.getInt(3));
            questionBean.setSences_ID(db_vo.resultSet.getString(4));
            questionBean.setDetails(db_vo.resultSet.getString(5));
            questionBean.setInsertTime(db_vo.resultSet.getTimestamp(6));
            questionBean.setDel(db_vo.resultSet.getInt(7));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}

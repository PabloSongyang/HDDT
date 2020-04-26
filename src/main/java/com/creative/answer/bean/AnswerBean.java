package com.creative.answer.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author Pablo.风暴洋
 * @company 大连创模科技
 * @time 2020/4/26 14:47
 * @package com.creative.answer.bean
 * @characterization 答案bean
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AnswerBean implements Serializable {
    private String id;
    private Integer orderNumber;
    private String title;
    private String answerOption;
    private Integer answer;
    private String questions_ID;
    private Timestamp insertTime;
    private Integer del;
}

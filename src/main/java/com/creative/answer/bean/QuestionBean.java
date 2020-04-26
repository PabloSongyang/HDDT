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
 * @time 2020/4/26 14:03
 * @package com.creative.answer.bean
 * @characterization 问题bean
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class QuestionBean implements Serializable {
    private String id;
    private String title;
    private Integer number;
    private String sences_ID;
    private String details;
    private Timestamp insertTime;
    private Integer del;
}

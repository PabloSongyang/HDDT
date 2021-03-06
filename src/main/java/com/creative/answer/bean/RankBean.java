package com.creative.answer.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author Pablo.风暴洋
 * @company 大连创模科技
 * @time 2020/5/6 15:26
 * @package com.creative.answer.bean
 * @characterization 排行榜bean
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RankBean implements Serializable {
    private String uuid;
    private int number;
}

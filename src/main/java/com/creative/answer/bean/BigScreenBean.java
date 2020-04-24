package com.creative.answer.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author Pablo.风暴洋
 * @company 大连创模科技
 * @time 2020/4/24 14:08
 * @package com.creative.answer.bean
 * @characterization 大屏幕端信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BigScreenBean implements Serializable {
    private String type;
}

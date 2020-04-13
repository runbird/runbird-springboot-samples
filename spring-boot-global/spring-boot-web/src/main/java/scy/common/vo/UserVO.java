package com.scy.common.vo;

import lombok.Builder;
import lombok.Data;

/**
 * 类名： UserVO <br>
 * 描述：TODO <br>
 * 创建日期： 2019/12/16 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
@Data
@Builder
public class UserVO {
    private long id;

    private String name;

    private Integer age;
}

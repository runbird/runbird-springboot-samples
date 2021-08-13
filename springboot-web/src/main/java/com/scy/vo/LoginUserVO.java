package com.scy.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 类名： LoginUserVO <br>
 * 描述：TODO <br>
 * 创建日期： 2019/12/16 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginUserVO {

    private String token;
    private String username;
    private String userId;
}

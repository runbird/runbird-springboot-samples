package com.scy.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @desc:
 * @author: Suocaiyuan
 * @date: 2019-12-14 14:45
 **/
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class JwtParam {
    private String username;
    private String password;
}

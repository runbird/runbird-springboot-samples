package com.scy.modules.common.dto.input;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 类名： BaseInput <br>
 * 描述：TODO <br>
 * 创建日期： 2019/11/29 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
@Data
@ApiModel(description = "应用基础传入参数")
public class BaseInput {
    @ApiModelProperty(value = "令牌")
    @JSONField(name = "token")
    private String token;
}

package com.scy.modules.common.dto.input;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 类名： BaseQuery <br>
 * 描述：TODO <br>
 * 创建日期： 2019/11/29 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
@Data
@ApiModel(description = "基础类查询参数")
public class BaseQuery extends BasePageQuery {
    @ApiModelProperty(value = "用户ID")
    private Integer userId;
}

package com.scy.modules.common.dto.input;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 类名： BasePageQuery <br>
 * 描述：TODO <br>
 * 创建日期： 2019/11/29 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
@ApiModel(description = "基础分页检索")
public class BasePageQuery {
    @ApiModelProperty(value = "当前页", required = true, position = 0)
    private Integer page;
    @ApiModelProperty(value = "每页显示数量", required = true, position = 1)
    private Integer limit;

    public Integer getPage() {
        return page == null ? 1 : page;
    }
}

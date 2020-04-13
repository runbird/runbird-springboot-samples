package com.scy.common.api.response;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 类名： CommonResult <br>
 * 描述：TODO <br>
 * 创建日期： 2019/12/16 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
@Data
@NoArgsConstructor
public final class CommonResult<T> {

    private int status = 1;

    private String errorCode = "";

    private String errorMsg = "";

    private T resultBody;

    public CommonResult(T resultBody) {
        this.resultBody = resultBody;
    }
}

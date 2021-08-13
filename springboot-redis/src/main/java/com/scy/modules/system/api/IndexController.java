package com.scy.modules.system.api;

import com.scy.modules.common.api.ApiResult;
import com.scy.modules.common.validator.annoation.NoRepeatSubmit;
import com.scy.modules.common.api.BaseController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 类名： IndexController <br>
 * 描述：TODO <br>
 * 创建日期： 2019/11/28 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
@RestController
public class IndexController extends BaseController {
    @NoRepeatSubmit
    @GetMapping(value = "/index", produces = "application/json;charset=utf-8")
    public ApiResult index() {
        return ApiResult.ok("Hello World ~ ");
    }
}

package com.scy.common.api.request;

import com.scy.common.enums.ModesEnum;
import org.springframework.core.convert.converter.Converter;

/**
 * 类名： StringToEnumConverter <br>
 * 描述：TODO <br>
 * 创建日期： 2019/12/16 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
public class StringToEnumConverter implements Converter<String, ModesEnum> {


    @Override
    public ModesEnum convert(String source) {
        return ModesEnum.valueOf(source);
    }
}

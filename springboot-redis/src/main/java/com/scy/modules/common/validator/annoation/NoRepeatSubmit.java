package com.scy.modules.common.validator.annoation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 类名： NoRepeatSubmit <br>
 * 描述：TODO <br>
 * 创建日期： 2019/11/28 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NoRepeatSubmit {

    int time() default 3 * 1000;
}

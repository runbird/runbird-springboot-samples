package com.scy.api.annoation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 类名： LoginUser <br>
 * 描述：TODO <br>
 * 创建日期： 2019/12/16 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser {
}

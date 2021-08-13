package com.scy.utils;

import lombok.extern.slf4j.Slf4j;

/**
 * 类名： ResourceDowngrationUtil <br>
 * 描述：TODO <br>
 * 创建日期： 2021/8/12 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
@Slf4j
public enum ResourceDowngrationUtil {
    INSTANCE;

    public static final String packageName = "com.scy.service";

    public static final String MAX_THRESHOLD_KEY = "maxThreshold";

    public void init() {
    }
}

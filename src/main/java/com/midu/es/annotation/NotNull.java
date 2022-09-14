package com.midu.es.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

/**
 * @author zhangqy
 * @description 变量、参数 、返回值不为空说明
 * @createTime 2022年09月13日 09:53:00
 */
@Documented
@Retention(RetentionPolicy.CLASS)
@Target({PARAMETER, FIELD, METHOD})
public @interface NotNull {
}

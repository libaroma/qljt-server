package com.office.qljt.qljtoffice.annotation;

import java.lang.annotation.*;

/**
 * 用户权限注解
 *
 * @author 续加仪
 * @date 2022/10/07
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CheckUserAuth {

    /**
     * @return 角色
     */
    long role() default 0L;

}

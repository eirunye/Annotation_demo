package eirunye.annotation;

import java.lang.annotation.Repeatable;

/**
 * Author Eirunye
 * Created by on 2018/11/24.
 * Describe
 * E_mail eirunye@aliyun.com
 */
@Repeatable(Repeatables.class)
public @interface RepeatablesTest {
    String value() default "哈哈哈";
}

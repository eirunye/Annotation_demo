package eirunye.annotation;

import java.lang.annotation.*;

/**
 * Author Eirunye
 * Created by on 2018/11/24.
 * Describe
 * E_mail eirunye@aliyun.com
 */

@Documented
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AnnotationTest {
    String name() default "小明";
    int userId() default  1;
}

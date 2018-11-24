package eirunye.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Author Eirunye
 * Created by on 2018/11/24.
 * Describe
 * E_mail eirunye@aliyun.com
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Repeatables {

    RepeatablesTest[] value();
}

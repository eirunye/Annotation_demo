package eirunye.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Author Eirunye
 * Created by on 2018/11/24.
 * Describe
 * E_mail eirunye@aliyun.com
 */

public class TestAnnotation1Main {
    public static void main(String[] args) {
        try {
            Field name = Test.class.getDeclaredField("name"); //获取该类的声明字段
            name.setAccessible(true);
            AnnotationTest1 annotationTest1 = name.getAnnotation(AnnotationTest1.class);//获取该字段的注解
            if (annotationTest1 != null) {
                System.out.println(annotationTest1.value()); //输出值
            }


            Method fun = Test.class.getDeclaredMethod("fun");
            if (fun != null) {
                Annotation[] annotations = fun.getAnnotations();
                for (int i = 0; i < annotations.length; i++) {
                    System.out.println(annotations[i].annotationType().getSimpleName());
                }
            }

        } catch (NoSuchFieldException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}

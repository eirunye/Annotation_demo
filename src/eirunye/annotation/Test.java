package eirunye.annotation;

/**
 * Author Eirunye
 * Created by on 2018/11/24.
 * Describe
 * E_mail eirunye@aliyun.com
 */
public class Test {

    @AnnotationTest1(value = "小云")
    public String name;

    @AnnotationTest2
    public void fun() {
        System.out.println("方法执行");
    }
}

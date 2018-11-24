package eirunye.annotation;

/**
 * Author Eirunye
 * Created by on 2018/11/24.
 * Describe
 * E_mail eirunye@aliyun.com
 */

@AnnotationTest(name = "小刚", userId = 2)
public class TestAnnotationMain {

    public static void main(String[] args) {
        boolean hasAnnotation = TestAnnotationMain.class.isAnnotationPresent(AnnotationTest.class);
        if (hasAnnotation) {
            AnnotationTest annotation = TestAnnotationMain.class.getAnnotation(AnnotationTest.class);
            System.out.println("name："+annotation.name() + "\n" + "userId：" + annotation.userId());
        }
    }
}

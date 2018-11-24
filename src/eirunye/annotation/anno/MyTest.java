package eirunye.annotation.anno;

/**
 * Author Eirunye
 * Created by on 2018/11/24.
 * Describe
 * E_mail eirunye@aliyun.com
 */
public class MyTest {

    @MyAnnotation
    public void mytestLoad() {
        System.out.println("测试加载");
    }

    @MyAnnotation
    public void mytestRequest() {
        System.out.println("测试请求");
    }

    @MyAnnotation
    public void mytestProgress() {
        System.out.println("测试进度");
    }

    @MyAnnotation
    public void mytestError() {
        System.out.println(1 );
    }

    public void mytestNoAnno(){
        System.out.println("没有注解的方法");
    }

    @Override
    public String toString() {
        return "MyTest{}";
    }
}

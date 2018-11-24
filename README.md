# Java注解

# 简介
由于无论在Java后台或者Android开发中我们经常遇到注解这个功能机制，例如常用的框架Java后台开发中，Spring、MyBatis等，Android的Dagger2，butterknife等，都是注解框架。今天我们就了解java是如何进行设置注解的？我们可以可以定义一个注解，方便我们使用等等。

# 注解元
在进行了解注解时我们先来了解一下，一般注解主要包含以下几个重要的注解元，java注解的机制离不开这几个注解元。

|注解元|介绍<功能>|
|-|-|
|1.@Target|注解用于什么地方，下面会介绍|
|2.@Retention|什么时候使用该注解|
|3.@Documented|注解是否将包含在JavaDoc中| 
|4.@Inherited|是否允许子类继承该注解，表示父类如果添加此注解，子类也可以使用|
|5.@Repeatable|java8添加的，可重复的，表该注解可以多次使用| 

# 注解元解释

## @Target
通过`@Target`进行添加到注解中，说明了Annotation所修饰的对象范围：Annotation可被用于 packages、types（类、接口、枚举、Annotation类型）、类型成员（方法、构造方法、成员变量、枚举值）、方法参数和本地变量（如循环变量、catch参数）。在Annotation类型的声明中使用了`@target`可更加明晰其修饰的目标，用于什么地方，修饰什么东西，如下常用的几种方式。

* CONSTRUCTOR: 用于描述构造器
* FIELD:用于描述域
* LOCAL_VARIABLE:用于描述局部变量
* METHOD:用于描述方法
* PACKAGE:用于描述包
* PARAMETER:用于描述参数
* TYPE:用于描述类、接口(包括注解类型) 或enum声明
如下代码
```java
@Target({ElementType.FIELD,ElementType.METHOD}) //多个的时候使用{}括起来，然后用逗号分隔开
public @interface TargetTest {
}
```
## @Retention
该注解是表示我们在运行或者编译时的一个保留状态，指示要保留带注释类型的注释的时间长度。
* SOURCE:在源文件中有效（即源文件保留），在编译时将其抛弃掉。
* CLASS:在class文件中有效（即class保留），不会添加载到JVM中
* RUNTIME:在运行时有效（即运行时保留），这个和我们常用的加载是一致的，运行时会加载到JVM中，一般通过反射可获取到，一般这个是我们常用的。
```java
@Retention(RetentionPolicy.RUNTIME)//在使用该注解其，只能选择其中一种属性，不能定义多个
public @interface RetentionTest {
}
```

## @Documented
`@Documented`用于描述其它类型的annotation应该被作为被标注的程序成员的公共API。
如下代码
```java
@Documented //添加文档注解
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AnnotationTest {
}
```

## @Inherited：
`@Inherited`元注解是一个标记注解， `@Inherited`阐述了某个被标注的类型是被继承的。如果一个使用了`@Inherited`修饰的annotation类型被用于一个class，则这个annotation将被用于该class的子类。
```java
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited  //添加继承注解
public @interface AnnotationTest {
}
```
## @Repeatable
这个是在java8添加的注解特性，`@Repeatable`的值表示可重复注释类型的包含注释类型。
```java
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Repeatables {
    RepeatablesTest[] value();
}
@Repeatable(Repeatables.class)
public @interface RepeatablesTest {
    String value() default "哈哈哈";
}
```
# 例子
我们简单的介绍了注解的常用的5个注解元，解下来我们通过例子来实现注解。
代码如下，根据上面的提示我们进行测试。
在看例子之前我们先来了解一下常用的几个方法。
* Class.getAnnotations() 获取所有的注解，包括自己声明的以及继承的
* Class.getAnnotation(Class< A > annotationClass) 获取指定的注解，该注解可以是自己声明的，也可以是继承的
* Class.getDeclaredAnnotations() 获取自己声明的注解
* Class.getDeclaredField(String name); //获取该类的声明字段
* Class.getDeclaredMethods();//返回的是一个Method[]数组
```java
@Documented
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AnnotationTest {
    String name() default "小明"; //表示默认为小明。
}
```
```java
@AnnotationTest() //在该类添加注解
public class TestAnnotationMain {

    public static void main(String[] args) {
        boolean hasAnnotation = TestAnnotationMain.class.isAnnotationPresent(AnnotationTest.class);
        if (hasAnnotation) {
            AnnotationTest annotation = TestAnnotationMain.class.getAnnotation(AnnotationTest.class);
            System.out.println(annotation.name());
        }

    }
}
```
打印输出结果：

![输出1.png](https://upload-images.jianshu.io/upload_images/3012005-1f456a2d492413d6.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/500)

如果我们想更改name的值可以这么弄
```
@AnnotationTest(name = "小刚")
public class TestAnnotationMain {
    public static void main(String[] args) {
        boolean hasAnnotation = TestAnnotationMain.class.isAnnotationPresent(AnnotationTest.class);
        if (hasAnnotation) {
            AnnotationTest annotation = TestAnnotationMain.class.getAnnotation(AnnotationTest.class);
            System.out.println(annotation.name());
        }
    }
}
```
![输出2.png](https://upload-images.jianshu.io/upload_images/3012005-037c41da49c434e1.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/500)
如果我们想给一个类的属性进行赋值可以这么做
1.创建一个注解如下
```java
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface AnnotationTest1 {
    String value(); //value来定义
}
```
2.引用该主解
```java
public class Test {
    @AnnotationTest1(value = "小云") //引用注解进行赋值
    public String name;
}
```
3.测试
```java
public class TestAnnotation1Main {
    public static void main(String[] args) {
        try {
            Field name = Test.class.getDeclaredField("name");//该该类的字段
            name.setAccessible(true); 
            AnnotationTest1 annotationTest1 = name.getAnnotation(AnnotationTest1.class);//获取该字段的注解
            if (annotationTest1 != null) {
                System.out.println(annotationTest1.value()); //输出值
            } 
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
```

> 获取方法上的注解类 如AnnotationTest2 

```java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AnnotationTest2 {
     //todo无任何方法或者属性
}
```
```java
public class Test {

    @AnnotationTest1(value = "小云")
    public String name;

    @AnnotationTest2 //目的获取该AnnotationTest2 
    public void fun() {
        System.out.println("方法执行");
    }
}
```
获取
```java
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
```
举例
```
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {
}
////////////////////////////////////////////////////////////////////////////
public class MyTest { //进行获取注解方法的全部数据
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
   ///////该方法不执行
   public void mytestNoAnno(){
        System.out.println("没有注解的方法");
    }
}
////////////////////////////////////////////////////////////////////////////
public class TestMain {
    public static void main(String[] args) {
        MyTest myTest = new MyTest();
        Method[] methods = myTest.getClass().getDeclaredMethods();
        for (int i = 0; i < methods.length; i++) {
            Method method = methods[i];
            if (method.isAnnotationPresent(MyAnnotation.class)) {
                try {
                    method.setAccessible(true);
                    method.invoke(myTest, null);//调用该类的注解方法
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("==================输出完成！====================");
    }
}
```
![输出3.png](https://upload-images.jianshu.io/upload_images/3012005-5882231d506e3639.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/500)


# 总结
我们在开发中长见的注解如下：
|常用注解|解释|
|-|-|
|@Override| 方法重写|
|@SuppressWarnings| 提示警告|
|@SafeVarargs |参数安全|
|@Deprecated|作用是对不应该再使用的方法添加注解|
|@FunctionalInterface|函数式编程，用于Lambda 表达式| 

注解给我们带来了许多方便，但是我们也得知道其优缺点。
* 优点
注解方便我们进行单元测试，有利于进行开发。
代码整洁，通过注解的方式变可知修饰的变量，或者方法。
节省配置，减少配置文件大小。

* 缺点
通过反射进行设置，可能会产生性能上的问题。
若要对配置进行修改需要重新编译，扩展性差。




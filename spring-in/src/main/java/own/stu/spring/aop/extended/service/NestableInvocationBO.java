package own.stu.spring.aop.extended.service;

/**
 * Created by dell on 2017/6/9.
 */
public class NestableInvocationBO {
    public void method1() {
        method2();
        System.out.println("method1 executed !");
    }

    public void method2() {
        System.out.println("method2 executed !");
    }
}

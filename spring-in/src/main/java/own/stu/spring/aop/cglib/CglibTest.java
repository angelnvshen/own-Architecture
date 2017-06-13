package own.stu.spring.aop.cglib;

import org.springframework.cglib.proxy.Enhancer;
import own.stu.spring.aop.cglib.interceptor.Hacker;
import own.stu.spring.aop.classLoader.service.Programmer;

/**
 * Created by dell on 2017/6/9.
 */
public class CglibTest {
    public static void main(String[] args) {
        Programmer programmer = new Programmer();
        Hacker hacker = new Hacker();
        //cglib 中加强器，用来创建动态代理
        Enhancer enhancer = new Enhancer();
        //设置要创建动态代理的类
        enhancer.setSuperclass(programmer.getClass());

        // 设置回调，这里相当于是对于代理类上所有方法的调用，都会调用CallBack，而Callback则需要实行intercept()方法进行拦
        enhancer.setCallback(hacker);
        Programmer proxy =(Programmer)enhancer.create();
        proxy.code();
    }
}

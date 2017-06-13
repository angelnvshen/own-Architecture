package own.stu.spring.aop.cglib.interceptor;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by dell on 2017/6/9.
 */
public class Hacker implements MethodInterceptor {
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("**** I am a hacker,Let's see what the poor programmer is doing Now...");
        methodProxy.invokeSuper(o, objects);
        System.out.println("****  Oh,what a poor programmer.....");
        return null;
    }
}

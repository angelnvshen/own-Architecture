package own.stu.spring.aop.extended;

import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import own.stu.spring.aop.extended.aspect.PerformanceTraceAspect;
import own.stu.spring.aop.extended.service.NestableInvocationBO;

/**
 * Created by dell on 2017/6/9.
 */
public class ExtendedTest {
    public static void main(String[] args) {
        AspectJProxyFactory weaver = new AspectJProxyFactory(new NestableInvocationBO());

        weaver.setProxyTargetClass(true);

        weaver.addAspect(PerformanceTraceAspect.class);
        Object proxy = weaver.getProxy();

        ((NestableInvocationBO) proxy).method2();
        ((NestableInvocationBO) proxy).method1();
    }
}

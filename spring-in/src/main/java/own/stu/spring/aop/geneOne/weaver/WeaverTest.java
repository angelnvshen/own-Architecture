package own.stu.spring.aop.geneOne.weaver;

import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.NameMatchMethodPointcutAdvisor;
import own.stu.spring.aop.geneOne.weaver.Interceptor.PerformanceMethodInterceptor;
import own.stu.spring.aop.geneOne.weaver.service.ITask;
import own.stu.spring.aop.geneOne.weaver.service.impl.Executable;
import own.stu.spring.aop.geneOne.weaver.service.impl.MockTask;

/**
 * Created by dell on 2017/6/7.
 */
public class WeaverTest {

    /**
     * 基于接口的代理
     * 动态代理
     * @param args
     */
    public static void main(String[] args) {
        MockTask task = new MockTask();
        ProxyFactory weaver = new ProxyFactory(task);
        //weaver.setInterfaces(new Class[]{ITask.class});

        NameMatchMethodPointcutAdvisor advisor = new NameMatchMethodPointcutAdvisor();
        advisor.setMappedName("execute");
        advisor.setAdvice(new PerformanceMethodInterceptor());

        weaver.addAdvisor(advisor);

        ITask proxyObject = (ITask) weaver.getProxy();
        proxyObject.execute(null);

        System.out.println(proxyObject.getClass());
    }

    /**
     * 基于类的代理
     * CGLIB
     * @param args
     */
    public static void main3(String[] args) {
        ProxyFactory weaver = new ProxyFactory(new Executable());

        NameMatchMethodPointcutAdvisor advisor = new NameMatchMethodPointcutAdvisor();
        advisor.setMappedName("execute");
        advisor.setAdvice(new PerformanceMethodInterceptor());

        weaver.addAdvisor(advisor);

        Executable proxyObject = (Executable) weaver.getProxy();
        proxyObject.execute();

        System.out.println(proxyObject.getClass());
    }
}

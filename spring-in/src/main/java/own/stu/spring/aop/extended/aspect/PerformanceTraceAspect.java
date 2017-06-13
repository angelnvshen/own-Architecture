package own.stu.spring.aop.extended.aspect;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.util.StopWatch;
import own.stu.spring.aop.geneOne.weaver.Interceptor.PerformanceMethodInterceptor;

/**
 * Created by dell on 2017/6/9.
 */
@Aspect
public class PerformanceTraceAspect {
    private final Log logger = LogFactory.getLog(PerformanceMethodInterceptor.class);

    @Pointcut("execution(public void *.method1())")
    public void method1() {
    }

    @Pointcut("execution(public void *.method2())")
    public void method2() {
    }

    @Pointcut("method1()||method2()")
    public void compositePointcut() {
    }

    @Around("compositePointcut()")
    public Object performanceTrace(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch watch = new StopWatch();
        try {
            watch.start();
            return joinPoint.proceed();
        } finally {
            watch.stop();
            if (logger.isInfoEnabled())
                logger.info("PT in method[" + joinPoint.getSignature().getName() + "]>>>>>>>" + watch.toString());
        }
    }
}

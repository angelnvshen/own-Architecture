package own.stu.spring.aop.geneOne.weaver.Interceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StopWatch;

/**
 * Created by dell on 2017/6/7.
 */
public class PerformanceMethodInterceptor implements MethodInterceptor {

    private final Log logger = LogFactory.getLog(PerformanceMethodInterceptor.class);

    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        StopWatch watch = new StopWatch();
        try {
            watch.start();
            return methodInvocation.proceed();
        } finally {
            watch.stop();
            if (logger.isInfoEnabled())
                logger.info(watch.toString());
        }
    }
}

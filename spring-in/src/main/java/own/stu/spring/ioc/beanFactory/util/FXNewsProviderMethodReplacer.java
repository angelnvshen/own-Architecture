package own.stu.spring.ioc.beanFactory.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.support.MethodReplacer;

import java.lang.reflect.Method;

/**
 * Created by dell on 2017/6/2.
 */
public class FXNewsProviderMethodReplacer implements MethodReplacer {
    private static final Log logger = LogFactory.getLog(FXNewsProviderMethodReplacer.class);

    public Object reimplement(Object target, Method method, Object[] args) throws Throwable {
        logger.info("before executing method[" + method.getName() + "] on Object[" + target.getClass().getName() + "].");
        System.out.println("sorry,We will do nothing this time.");
        logger.info("end of executing method[" + method.getName() + "] on Object[" + target.getClass().getName() + "].");
        return null;
    }
}

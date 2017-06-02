package own.stu.spring.ioc.beanFactory.service.impl;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import own.stu.spring.ioc.beanFactory.model.FXNewsBean;
import own.stu.spring.ioc.beanFactory.service.IFXNewsPersister;

/**
 * 使用 BeanFactoryAware 接口
 * 能够保证每次调用 BeanFactory 的 getBean("newsBean") ，就同样可以每次都取得新的 FXNewsBean 对象实例
 */
public class MockNewsPersister implements IFXNewsPersister,BeanFactoryAware {
    private BeanFactory beanFactory;

    public void persistNews(FXNewsBean bean) {
        persistNews();
    }

    public void persistNews() {
        System.out.println("persist bean:" + getNewsBean());
    }

    public FXNewsBean getNewsBean() {
        return (FXNewsBean) beanFactory.getBean("newsBean");
    }

    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }
}
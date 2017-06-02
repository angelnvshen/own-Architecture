package own.stu.spring.ioc.beanFactory.service.impl;

import own.stu.spring.ioc.beanFactory.model.FXNewsBean;
import own.stu.spring.ioc.beanFactory.service.IFXNewsPersister;

public class MockNewsPersister implements IFXNewsPersister {
    private FXNewsBean newsBean;

    public void persistNews(FXNewsBean bean) {
        persistNews();
    }

    public void persistNews() {
        System.out.println("persist bean:" + getNewsBean());
    }

    public FXNewsBean getNewsBean() {
        return newsBean;
    }

    public void setNewsBean(FXNewsBean newsBean) {
        this.newsBean = newsBean;
    }
}
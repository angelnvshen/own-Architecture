package own.stu.spring.ioc.beanFactory.service;

import own.stu.spring.ioc.beanFactory.model.FXNewsBean;

/**
 * Created by dell on 2017/6/2.
 */
public interface IFXNewsPersister {
    void persistNews(FXNewsBean fxNewsBean);
}

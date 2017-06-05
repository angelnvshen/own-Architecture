package own.stu.spring.ioc.beanFactory;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import own.stu.spring.ioc.beanFactory.service.impl.MockNewsPersister;

/**
 * Created by dell on 2017/6/2.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class BeanFactoryTest {

    @Autowired
    private MockNewsPersister mockPersister;

    @Test
    public void testScope(){
        mockPersister.persistNews();
//        mockPersister.persistNews();
    }
}

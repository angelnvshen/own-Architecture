package own.stu.spring.ioc.applicationContext;

import org.junit.Test;
import org.springframework.core.io.*;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Created by dell on 2017/6/5.
 */
public class ApplicationContextTest {

    String filePath = "E:\\sms_after_succ.log";

    @Test
    public void testDefaultResourceLoader(){
        DefaultResourceLoader resourceLoader = new DefaultResourceLoader();

        Resource fakeFileResource = resourceLoader.getResource(filePath);
        assertTrue(fakeFileResource instanceof ClassPathResource);

        Resource urlResource1 = resourceLoader.getResource("file:" + filePath);
        assertTrue(urlResource1 instanceof UrlResource);

        Resource urlResource2 = resourceLoader.getResource("http://www.baidu.com");
        assertTrue(urlResource2 instanceof  UrlResource);

        try {
            fakeFileResource.getFile();
            fail("no such file with path[" + fakeFileResource.getFilename() + "] exists in classpath");
        } catch (Exception e) {
            //
        }

        try{
            urlResource1.getFile();
        }
        catch(Exception e){
            fail();
        }
    }

    @Test
    public void testResourceTypesWithFileSystemResourceLoader() {
        ResourceLoader resourceLoader = new FileSystemResourceLoader();
        Resource fileResource = resourceLoader.getResource(filePath);
        assertTrue(fileResource instanceof FileSystemResource);
        assertTrue(fileResource.exists());
        Resource urlResource = resourceLoader.getResource("file:" + filePath);
        assertTrue(urlResource instanceof UrlResource);
    }
}

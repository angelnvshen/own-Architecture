package own.stu.spring.aop.classLoader.loader;

/**
 * Created by dell on 2017/6/8.
 */
public class OwnClassLoader extends ClassLoader {

    public Class<?> defineMyClass(byte[] b, int off, int len) {
        return super.defineClass(b, off, len);
    }

}

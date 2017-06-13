package own.stu.spring.aop.classLoader;

import own.stu.spring.aop.classLoader.loader.OwnClassLoader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by dell on 2017/6/8.
 */
public class LoaderTest {
    public static void main(String[] args) throws IOException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        //读取本地的class文件内的字节码，转换成字节码数组
        File file = new File(".");
        System.out.println(file.getCanonicalPath());
        InputStream input = new FileInputStream(file.getCanonicalPath()+"/target/classes/own/stu/spring/aop/classLoader/service/Programmer.class");
        byte[] result = new byte[1024];
        int count = input.read(result);
        System.out.println(count);

        // 使用自定义的类加载器将 byte字节码数组转换为对应的class对象
        OwnClassLoader loader = new OwnClassLoader();
        Class clazz = loader.defineMyClass( result, 0, count);
        //测试加载是否成功，打印class 对象的名称
        System.out.println(clazz.getCanonicalName());

        //实例化一个Programmer对象
        Object o= clazz.newInstance();

        //调用Programmer的code方法
        clazz.getMethod("code", null).invoke(o, null);
    }
}

package own.stu.spring.aop.jdkDynamic.invocationHandler;

import own.stu.spring.aop.jdkDynamic.service.imp.ElectricCar;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by dell on 2017/6/8.
 */
public class InvocationHandlerImpl implements InvocationHandler {


    private ElectricCar car;

    public InvocationHandlerImpl(ElectricCar car) {
        this.car = car;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("You are going to invoke " + method.getName() + " ...");
        method.invoke(car, null);
        System.out.println(method.getName() + " invocation Has Been finished...");
        return null;
    }
}
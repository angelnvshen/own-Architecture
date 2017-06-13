package own.stu.spring.aop.jdkDynamic.service.imp;

import own.stu.spring.aop.jdkDynamic.service.Rechargable;
import own.stu.spring.aop.jdkDynamic.service.Vehicle;

/**
 * 电能车类，实现Rechargable，Vehicle接口
 *
 * @author louluan
 */
public class ElectricCar implements Rechargable, Vehicle {

    public void drive() {
        System.out.println("Electric Car is Moving silently...");
    }

    public void recharge() {
        System.out.println("Electric Car is Recharging...");
    }

}
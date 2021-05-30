package com.shizy.feature.proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;

import java.lang.reflect.Proxy;

public class ProxyTest {

    public static void main(String[] args) {
        new ProxyTest().test();
    }

    /**
     * 接口直接调用fun
     */
    private void test() {
        /**
         * 直接new对象
         */
        FunInterface funClass = new FunClass();
        funClass.fun();

        /**
         * jdk proxy
         */
        FunInterface funClass2 = jdkProxyGetInstance(funClass);
        funClass2.fun();

        /**
         * cglib
         */
        FunClassNoInterface funClass3 = cglibGetInstance(funClass);
        funClass3.fun();
    }

    /**
     * jdk proxy 调用fun
     * <p>
     * - 被代理对象需要实现接口，并且只能调用接口中的方法
     * - 调用对象需要改成代理对象，代码需改动。spring框架中可以直接加代理是因为框架中预留了代理对象的执行代码
     */
    private FunInterface jdkProxyGetInstance(FunInterface funClass) {
        return (com.shizy.feature.proxy.FunInterface) Proxy.newProxyInstance(funClass.getClass().getClassLoader(), funClass.getClass().getInterfaces(), (proxy, method, args) -> {
            System.out.println("proxy1");
            Object object = method.invoke(funClass);
            System.out.println("proxy2");
            return object;
        });
    }

    /**
     * cglib 调用fun
     * <p>
     * - 被代理对象可以不实现接口，cglib创建一个子类进行代理
     * - 调用对象需要改成代理对象，代码需改动。spring框架中可以直接加代理是因为框架中预留了代理对象的执行代码
     */
    private FunClassNoInterface cglibGetInstance(FunInterface funClass) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(FunClassNoInterface.class);
        enhancer.setCallback((MethodInterceptor) (o, method, objects, methodProxy) -> {
            System.out.println("proxy1");
            Object object = methodProxy.invokeSuper(o, objects);
            System.out.println("proxy2");
            return object;
        });
        return (FunClassNoInterface) enhancer.create();
    }
}


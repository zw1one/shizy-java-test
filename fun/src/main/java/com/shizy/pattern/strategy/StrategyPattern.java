package com.shizy.pattern.strategy;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 策略模式
 * <p>
 * Strategy接口
 * Strategy类A、B、C
 * StrategyContext
 */
public class StrategyPattern {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        XxStrategy strategy = new XxStrategyAaa();
        XxStrategy strategy2 = (XxStrategy) Class.forName("com.com.shizy.pattern." + "XxStrategyBbb").newInstance();

        XxStrategyContext context = new XxStrategyContext(strategy2);
        context.execStrategy();
    }

}

/**
 * xx策略接口
 */
interface XxStrategy {
    public void doSomething();
}

/**
 * xx策略类aaa
 */
class XxStrategyAaa implements XxStrategy {

    @Override
    public void doSomething() {
        System.out.println("aaa");
    }
}

/**
 * xx策略类bbb
 */
class XxStrategyBbb implements XxStrategy {

    @Override
    public void doSomething() {
        System.out.println("bbb");
    }
}

/**
 * xx策略类Context
 */
@Data
@AllArgsConstructor
class XxStrategyContext {
    private XxStrategy xxStrategy;

    public void execStrategy() {
        xxStrategy.doSomething();
    }
}

















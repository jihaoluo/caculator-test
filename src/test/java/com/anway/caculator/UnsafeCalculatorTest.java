package com.anway.caculator;


import com.anway.calculator.UnsafeCalculator;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * @author lokia
 */
public class UnsafeCalculatorTest {

    @Test
    public void test() {
        final UnsafeCalculator caculator = new UnsafeCalculator();
        caculator.setCurrentVal(BigDecimal.valueOf(10));
        caculator.add(BigDecimal.valueOf(20));
        assert 0 == caculator.getCurrentVal().compareTo(BigDecimal.valueOf(30));
        System.out.println("当前值：" + caculator.getCurrentVal());
        caculator.sub(BigDecimal.valueOf(15));
        assert 0 == caculator.getCurrentVal().compareTo(BigDecimal.valueOf(15));
        System.out.println("当前值：" + caculator.getCurrentVal());
        caculator.mul(BigDecimal.valueOf(2.5));
        assert 0 == caculator.getCurrentVal().compareTo(BigDecimal.valueOf(37.5));
        System.out.println("当前值：" + caculator.getCurrentVal());
        caculator.div(BigDecimal.valueOf(3));
        assert 0 == caculator.getCurrentVal().compareTo(BigDecimal.valueOf(12.5));
        System.out.println("当前值：" + caculator.getCurrentVal());
        caculator.undo();
        assert 0 == caculator.getCurrentVal().compareTo(BigDecimal.valueOf(37.5));
        System.out.println("当前值：" + caculator.getCurrentVal());
        caculator.redo();
        assert 0 == caculator.getCurrentVal().compareTo(BigDecimal.valueOf(12.5));
        System.out.println("当前值：" + caculator.getCurrentVal());
    }

    @Test
    public void testThread() throws InterruptedException {
        //对同一个资源做并发操作
        final UnsafeCalculator caculator = new UnsafeCalculator();
        caculator.setCurrentVal(BigDecimal.ZERO);
        System.out.println("最开始值是：" + caculator.getCurrentVal());
        final Thread t1 = new Thread(() -> {
            for (int j = 0; j < 100; j++) {
                caculator.add(BigDecimal.valueOf(j));
            }
        });


        final Thread t2 = new Thread(() -> {
            for (int j = 0; j < 100; j++) {
                caculator.sub(BigDecimal.valueOf(j));
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("最终的值是：" + caculator.getCurrentVal());

        assert 0 != caculator.getCurrentVal().compareTo(BigDecimal.ZERO);
    }
}

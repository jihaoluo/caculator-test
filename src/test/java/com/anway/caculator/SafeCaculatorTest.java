package com.anway.caculator;


import com.anway.calculator.SafeCaculator;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * @author lokia
 */
public class SafeCaculatorTest {

    @Test
    public void test() {
        final SafeCaculator caculator = new SafeCaculator();
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

}

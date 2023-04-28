package com.anway.calculator;


import java.math.BigDecimal;

/**
 * 数值计算器抽象接口
 * @author lokia
 */
public interface ICaculator {


    /**
     * 加法
     * @param val
     */
    void add(BigDecimal val);

    /**
     * 减法
     * @param val
     */
    void sub(BigDecimal val);

    /**
     * 乘法
     * @param val
     */
    void mul(BigDecimal val);

    /**
     * 除法
     * @param val
     */
    void div(BigDecimal val);

    /**
     * 撤销
     */
    void undo();

    /**
     * 重做
     */
    void redo();
}

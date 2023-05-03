package com.anway.calculator;

import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 非线程安全计算器
 * @author lokia
 */
public class UnsafeCalculator implements ICaculator {

    private BigDecimal currentVal;
    private Deque<BigDecimal> undoQueue;
    private Deque<BigDecimal> redoQueue;

    public UnsafeCalculator() {
        this.currentVal = BigDecimal.ZERO;
        this.undoQueue = new ArrayDeque<>();
        this.redoQueue = new ArrayDeque<>();
    }

    @Override
    public void add(BigDecimal val) {
        undoQueue.push(currentVal);
        currentVal = currentVal.add(val);
        redoQueue.clear();
    }

    @Override
    public void sub(BigDecimal val) {
        undoQueue.push(currentVal);
        currentVal = currentVal.subtract(val);
        redoQueue.clear();
    }

    @Override
    public void mul(BigDecimal val) {
        undoQueue.push(currentVal);
        currentVal = currentVal.multiply(val);
        redoQueue.clear();
    }

    @Override
    public void div(BigDecimal val) {
        undoQueue.push(currentVal);
        currentVal = currentVal.divide(val);
        redoQueue.clear();
    }

    @Override
    public void undo() {
        if (undoQueue.isEmpty()) {
            return;
        }

        BigDecimal oldVal = undoQueue.pop();
        redoQueue.push(currentVal);
        currentVal = oldVal;
    }

    @Override
    public void redo() {
        if (redoQueue.isEmpty()) {
            return;
        }

        BigDecimal newVal = redoQueue.pop();
        undoQueue.push(currentVal);
        currentVal = newVal;

    }

    public BigDecimal getCurrentVal() {
        return currentVal;
    }

    public void setCurrentVal(BigDecimal currentVal) {
        this.currentVal = currentVal;
    }
}

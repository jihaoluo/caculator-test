package com.anway.calculator;

import java.math.BigDecimal;
import java.util.ArrayDeque;

/**
 * 非线程安全计算器
 * @author lokia
 */
public class UnsafeCaculator  implements ICaculator{

    private BigDecimal currentVal;
    private ArrayDeque<BigDecimal>  undoQueue;
    private ArrayDeque<BigDecimal>  redoQueue;

    public UnsafeCaculator() {
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
        if (!undoQueue.isEmpty()) {
            BigDecimal oldVal = undoQueue.pop();
            redoQueue.push(currentVal);
            currentVal = oldVal;
        } else {
            System.out.println("Error: nothing to undo");
        }
    }

    @Override
    public void redo() {
        if (!redoQueue.isEmpty()) {
            BigDecimal newVal = redoQueue.pop();
            undoQueue.push(currentVal);
            currentVal = newVal;
        } else {
            System.out.println("Error: nothing to redo");
        }
    }

    public BigDecimal getCurrentVal() {
        return currentVal;
    }

    public void setCurrentVal(BigDecimal currentVal) {
        this.currentVal = currentVal;
    }
}

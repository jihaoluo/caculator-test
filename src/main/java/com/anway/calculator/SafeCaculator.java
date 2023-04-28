package com.anway.calculator;

import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.concurrent.locks.ReentrantLock;


/**
 * 线程安全计算器
 * @author lokia
 */
public class SafeCaculator implements ICaculator {

    private BigDecimal currentVal;
    private ArrayDeque<BigDecimal> undoQueue;
    private ArrayDeque<BigDecimal> redoQueue;
    /**
     * 非公平锁
     */
    private ReentrantLock lock;

    public SafeCaculator() {
        this.lock = new ReentrantLock();
        this.currentVal = BigDecimal.ZERO;
        this.undoQueue = new ArrayDeque<>();
        this.redoQueue = new ArrayDeque<>();
    }

    @Override
    public void add(BigDecimal val) {
        lock.lock();
        try {
            undoQueue.push(currentVal);
            currentVal = currentVal.add(val);
            redoQueue.clear();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void sub(BigDecimal val) {
        lock.lock();
        try {
            undoQueue.push(currentVal);
            currentVal = currentVal.subtract(val);
            redoQueue.clear();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void mul(BigDecimal val) {
        lock.lock();
        try {
            undoQueue.push(currentVal);
            currentVal = currentVal.multiply(val);
            redoQueue.clear();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void div(BigDecimal val) {
        lock.lock();
        try {
            undoQueue.push(currentVal);
            currentVal = currentVal.divide(val);
            redoQueue.clear();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void undo() {
        lock.lock();
        try {
            if (undoQueue.isEmpty()) {
                return;
            }

            BigDecimal oldVal = undoQueue.pop();
            redoQueue.push(currentVal);
            currentVal = oldVal;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void redo() {
        lock.lock();
        try {
            if (redoQueue.isEmpty()) {
                return;
            }

            BigDecimal newVal = redoQueue.pop();
            undoQueue.push(currentVal);
            currentVal = newVal;
        } finally {
            lock.unlock();
        }
    }

    public BigDecimal getCurrentVal() {
        return currentVal;
    }

    public void setCurrentVal(BigDecimal currentVal) {
        this.currentVal = currentVal;
    }
}

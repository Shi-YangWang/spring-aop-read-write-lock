package com.bunny.aop.lock;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.concurrent.locks.ReentrantReadWriteLock;

@Aspect
public abstract class AbstractReadWriteLockAspect {

    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    @Pointcut
    protected abstract void read();

    @Pointcut
    protected abstract void write();

    @Around("read()")
    public void doRead(ProceedingJoinPoint joinPoint) {
        try {
            lock.readLock().lock();
            joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            lock.readLock().unlock();
        }
    }

    @Around("write()")
    public void doWrite(ProceedingJoinPoint joinPoint) {
        try {
            lock.writeLock().lock();
            joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            lock.writeLock().unlock();
        }
    }
}

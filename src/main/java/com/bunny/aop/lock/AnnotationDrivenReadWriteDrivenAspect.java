package com.bunny.aop.lock;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AnnotationDrivenReadWriteDrivenAspect extends AbstractReadWriteLockAspect{

    @Pointcut("@annotation(com.bunny.aop.annotation.ReadWriteLockManaged)")
    public void readWriteManaged() {

    }

    @Override
    @Pointcut("@annotation(com.bunny.aop.annotation.ReadOnly) && @target(com.bunny.aop.annotation.ReadWriteLockManaged)")
    public void read() {

    }

    @Override
    @Pointcut("@annotation(com.bunny.aop.annotation.Write) && @target(com.bunny.aop.annotation.ReadWriteLockManaged)")
    public void write() {

    }
}

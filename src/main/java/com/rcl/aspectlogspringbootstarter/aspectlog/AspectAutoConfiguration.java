package com.rcl.aspectlogspringbootstarter.aspectlog;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.PriorityOrdered;

/**
 * @author chenglong.ren
 * @date 2020/9/3 13:46
 * @desc
 */
@Aspect
@EnableAspectJAutoProxy(exposeProxy = true, proxyTargetClass = true)
@Configuration
@ConditionalOnProperty(
        prefix = "aspect-log",
        name = "enable",
        havingValue = "true",
        matchIfMissing = true)
public class AspectAutoConfiguration implements PriorityOrdered {

    Logger log = LoggerFactory.getLogger(AspectAutoConfiguration.class);

    @Override
    public int getOrder() {
        //保证事务等切面先执行
        return Integer.MAX_VALUE;
    }

    @Around("@annotation(com.rcl.aspectlogspringbootstarter.aspectlog.AspectLog)")
    public Object isOpen(ProceedingJoinPoint thisJoinPoint) throws Throwable {
        //执行方法名称
        String taskName = thisJoinPoint.getSignature()
                .toString().substring(
                        thisJoinPoint.getSignature()
                                .toString().indexOf(" "),
                        thisJoinPoint.getSignature().toString().indexOf("("));
        taskName = taskName.trim();
        long time = System.currentTimeMillis();
        Object result = thisJoinPoint.proceed();
        log.info("method:{} run :{} ms", taskName,
                (System.currentTimeMillis() - time));
        return result;
    }

}

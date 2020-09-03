package com.rcl.aspectlogspringbootstarter.log;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author chenglong.ren
 * @date 2020/9/3 11:49
 * @desc 自定义拦截器
 */
public class MyLogInterceptor extends HandlerInterceptorAdapter {
    Logger log = LoggerFactory.getLogger(MyLogInterceptor.class);

    private static final ThreadLocal<Long> startTimeThreadLocal = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //方法前置处理
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        MyLog myLog = method.getAnnotation(MyLog.class);
        if (null != myLog) {
            long startTime = System.currentTimeMillis();
            startTimeThreadLocal.set(startTime);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //方法后置处理
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        MyLog myLog = method.getAnnotation(MyLog.class);
        if (null != myLog) {
            Long startTime = startTimeThreadLocal.get();
            long entTime = System.currentTimeMillis();
            long expendTime = entTime - startTime;

            //打印参数
            String requestUri = request.getRequestURI();
            String methodName = method.getDeclaringClass().getName() + "#" + method.getName();
            String methodDesc = myLog.desc();
            String parameters = JSON.toJSONString(request.getParameterMap());
            log.info("\n描述：{}\n路径: {}\n方法: {}\n参数：{}\n耗时：{}", methodDesc, requestUri, methodName, parameters, expendTime);
        }
    }
}

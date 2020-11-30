package cn.litblue.springbootlog.annotation;

import cn.litblue.springbootlog.entity.Logs;
import cn.litblue.springbootlog.service.DemoService;
import cn.litblue.springbootlog.util.IpUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.net.http.HttpRequest;

/**
 * @author litblue
 * @version 1.0.0
 * @time 2020/10/13  20:53
 */

@Slf4j
@Aspect
@Configuration
public class LogAspect {

    @Resource
    private DemoService demoService;

    /**
     * Service层切点
     */
    @Pointcut(value = "@annotation(cn.litblue.springbootlog.annotation.Log)")
    public void pointcut() {
    }


    @After(value = "pointcut()")
    public void  around(JoinPoint point) {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        MethodSignature signature = (MethodSignature)point.getSignature();
        Method method = signature.getMethod();


        Log userAction = method.getAnnotation(Log.class);
        if (userAction != null) {
            Logs logs = new Logs();
            logs.setType(1);
            logs.setLogKey(userAction.name());
            logs.setContent(userAction.description());
            logs.setIpAddress(IpUtil.getRemoteAddress(request));

            demoService.insert(logs);
        }
    }

}

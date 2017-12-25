package api.aop;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author zw(汤泽辰吃得多喝得多)
 * @date 2017-12-01  19:04
 */
@Aspect
@Component
public class LogAspect {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(public * com.snapp.admin.controller..*.*(..))")
    public void controllerAspect() {}

//    @Pointcut("execution(public * org.muses.jeeplatform.service..*.*(..))")
//    public void serviceAspect() {}


    @Around("controllerAspect()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();

        Object result = null;
        try {
            LOGGER.info("=====前置通知开始=====");
            LOGGER.info("请求接口:" + request.getMethod() + "[" + request.getRequestURL() + "]");
            LOGGER.info("请求参数:" + request.getQueryString());

            result = joinPoint.proceed();

            LOGGER.info("=====后置通知开始=====");
            LOGGER.info("接口返回:" + JSON.toJSONString(result));


        }catch (Exception e){
            LOGGER.error("异常信息:{}", e.getMessage());
            LOGGER.info("=====全局异常处理=====");

            Map<String, Object> map = new HashMap<>(16);
            map.put("error", e.getClass().getName());
            map.put("type", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return new ResponseEntity(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return result;
    }

//    @AfterThrowing(pointcut = "serviceAspect()", throwing = "e")
//    public void doAfterThrowing(JoinPoint joinPoint, Exception e) {
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
//
//        //获取请求ip
//        String ip = request.getRemoteAddr();
//        //获取用户请求方法的参数并序列化为JSON格式字符串
//        String params = "";
//        if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {
//            for (int i = 0; i < joinPoint.getArgs().length; i++) {
//                params += JSON.toJSONString(joinPoint.getArgs()[i]) + ";";
//            }
//        }
//        try {
//              /*========控制台输出=========*/
//            LOGGER.info("=====异常通知开始=====");
//            LOGGER.info("异常代码:" + e.getClass().getName());
//            LOGGER.info("异常方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
//            LOGGER.info("请求IP:" + ip);
//            LOGGER.info("请求参数:" + params);
//        }catch (Exception ex) {
//            //记录本地异常日志
//            LOGGER.error("==异常通知异常==");
//            LOGGER.error("异常信息:{}", ex.getMessage());
//        }
//    }



}

package config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @Author: pengjianzhou
 * @Description:
 * @Date: Created in 下午5:34 18-5-31
 */
@Aspect
@Component
public class ModelViewAspect {

    @After(value = "@annotation(myAnotion.MyRequestMapping)")
    public void doAfter(JoinPoint joinPoint){

        Object[] args = joinPoint.getArgs();
        System.out.println(args);
    }
}

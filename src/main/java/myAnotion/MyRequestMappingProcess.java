package myAnotion;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;

/**
 * @Author: pengjianzhou
 * @Description:
 * @Date: Created in 上午9:14 18-5-31
 */
public class MyRequestMappingProcess implements BeanPostProcessor{

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("**********"+beanName);
        if (beanName.equals("testController")) {

            Method[] methods = bean.getClass().getDeclaredMethods();
            for (Method methodClass : methods) {

                if(methodClass.isAnnotationPresent(MyRequestMapping.class)) {

                    methodClass.setAccessible(true);

                    MyRequestMapping myRequestMapping = methodClass.getDeclaredAnnotation(MyRequestMapping.class);

//                    RequestMapping requestMapping = myRequestMapping.annotationType().getDeclaredAnnotation(RequestMapping.class);

                    //获取 foo 这个代理实例所持有的 InvocationHandler
//                    InvocationHandler h = Proxy.getInvocationHandler(requestMapping);
                    InvocationHandler h0 = Proxy.getInvocationHandler(myRequestMapping);
                    // 获取 AnnotationInvocationHandler 的 memberValues 字段
                    try {
//                        Field hField = h.getClass().getDeclaredField("memberValues");
                        Field hField0 = h0.getClass().getDeclaredField("memberValues");
                        // 因为这个字段事 private final 修饰，所以要打开权限
//                        hField.setAccessible(true);
                        hField0.setAccessible(true);
                        // 获取 memberValues
//                        Map memberValues = (Map) hField.get(h);
                        Map memberValues0 = (Map) hField0.get(h0);
                        // 修改 value 属性值
                        String[] value = new String[1];
                        value[0]= methodClass.getName();

//                        memberValues.put("value", value);
                        memberValues0.put("value", value);

                        System.out.println(1);

                        // 获取 foo 的 value 属性值
                    } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }


        }



        return bean;

    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}

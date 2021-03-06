package web.stateMachine.config;

/**
 * @Author: pengjianzhou
 * @Description:
 * @Date: Created in 下午5:57 18-5-11
 */


import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.statemachine.transition.Transition;
import web.stateMachine.enums.Order;
import web.stateMachine.enums.OrderStatus;
import web.stateMachine.enums.OrderStatusChangeEvent;

import java.util.EnumSet;

/**
 * 订单状态机配置
 */
@Configuration
//@EnableStateMachine(name = "orderStateMachine")
@EnableStateMachineFactory
public class OrderStateMachineConfig extends EnumStateMachineConfigurerAdapter<OrderStatus, OrderStatusChangeEvent> {

    /**
     * 配置状态
     * @param states
     * @throws Exception
     */
    @Override
    public void configure(StateMachineStateConfigurer<OrderStatus, OrderStatusChangeEvent> states) throws Exception {
        states
                .withStates()
                .initial(OrderStatus.WAIT_PAYMENT)
                .states(EnumSet.allOf(OrderStatus.class));
    }

    /**
     * 配置状态转换事件关系
     * @param transitions
     * @throws Exception
     */
    @Override
    public void configure(StateMachineTransitionConfigurer<OrderStatus, OrderStatusChangeEvent> transitions) throws Exception {
        transitions
                .withExternal().source(OrderStatus.WAIT_PAYMENT).target(OrderStatus.WAIT_DELIVER).event(OrderStatusChangeEvent.PAYED)
                .and()
                .withExternal().source(OrderStatus.WAIT_DELIVER).target(OrderStatus.WAIT_RECEIVE).event(OrderStatusChangeEvent.DELIVERY)
                .and()
                .withExternal().source(OrderStatus.WAIT_RECEIVE).target(OrderStatus.FINISH).event(OrderStatusChangeEvent.RECEIVED);
    }

  /*  @Override
    public void configure(StateMachineConfigurationConfigurer<OrderStatus, OrderStatusChangeEvent> configurer) throws Exception {
        configurer.withConfiguration().listener(listener());
    }*/



    /**
     * 持久化配置
     * 实际使用中，可以配合redis等，进行持久化操作
     * @return
     */
    @Bean
    public StateMachinePersister<OrderStatus, OrderStatusChangeEvent, Order> persister(){
        return new DefaultStateMachinePersister<>(new StateMachinePersist<OrderStatus, OrderStatusChangeEvent, Order>() {
            @Override
            public void write(StateMachineContext<OrderStatus, OrderStatusChangeEvent> context, Order order) throws Exception {
                //此处并没有进行持久化操作
                order.setStatus(context.getState());
            }

            @Override
            public StateMachineContext<OrderStatus, OrderStatusChangeEvent> read(Order order) throws Exception {
                //此处直接获取order中的状态，其实并没有进行持久化读取操作
                System.out.println("********"+order.getStatus());
//                System.out.println(shoppingCartService.getShoppingCartById(1226L).getId());
                System.out.println("********");
                return new DefaultStateMachineContext<>(order.getStatus(), null, null, null);
            }
        });
    }


   /* public StateMachineListener<OrderStatus, OrderStatusChangeEvent> listener(){
        return new StateMachineListenerAdapter<OrderStatus, OrderStatusChangeEvent>(){
            @Override
            public void transition(Transition<OrderStatus, OrderStatusChangeEvent> transition) {

                System.out.println("transition:"+transition.toString());
//                System.out.println("transition:"+transition.getSource().getId()+"——>"+transition.getTarget().getId());
            }
        };
    }*/
}

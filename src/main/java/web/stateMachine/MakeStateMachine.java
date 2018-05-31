/*
package web.stateMachine;

import com.jd.rent.order.service.stateMachine.config.StateMachineConfig;
import com.jd.rent.order.service.stateMachine.enums.Events;
import com.jd.rent.order.service.stateMachine.enums.States;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.support.StaticListableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.StateMachineBuilder;
import org.springframework.statemachine.guard.Guard;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.EnumSet;

*/
/**
 * @Author: pengjianzhou
 * @Description:
 * @Date: Created in 下午8:21 18-5-10
 *//*

@Slf4j
@Service
public class MakeStateMachine {

    public StateMachine<States,Events> getStateMachine(States currentState) throws Exception {
        StateMachineBuilder.Builder<States,Events> builder = StateMachineBuilder.builder();
        builder.configureConfiguration()
                .withConfiguration()
                //添加状态机监听器
                .listener(listener())
                .beanFactory(new StaticListableBeanFactory());//添加构建bean的工厂类，可以自行实现，这里是使用系统的默认

        Collection<ConfigEntity> data = StateMachineConfig.configEntities;
//        HashSet<States> states = new HashSet<>();
        for (ConfigEntity configEntity : data) {
//            states.add(configEntity.getTarget());
            builder.configureTransitions()
                    .withExternal()
                    .source(configEntity.getSource())
                    .target(configEntity.getTarget())
                    .action(action())
                    .guard(guard())
                    .event(configEntity.getEvent())
            ;
        }

        builder.configureStates()
                .withStates()
                .initial(currentState)
//                .state(StateMachineConfig.initState.getState())
//                .end(StateMachineConfig.endState.getState())
                .states(EnumSet.allOf(States.class));


        StateMachine<States,Events> sm = builder.build();
        if (States.UNPAID != currentState) {
            sm.start();
        }
        return sm;
    }

    @Bean
    public StateMachineListener<States, Events> listener() {
        return new StateMachineListenerAdapter<States, Events>() {
            @Override
            public void transition(Transition<States, Events> transition) {
                if (transition.getSource()==null && transition.getTarget().getId() == States.UNPAID) {
                    log.info("订单创建，待支付");
                    return;
                }
                if (transition.getSource()==null && transition.getTarget().getId() == States.WAITING_FOR_RECEIVE) {
                    log.info("订单初始化，待收货");
                    return;
                }

                if (transition.getSource().getId() == States.UNPAID
                        && transition.getTarget().getId() == States.WAITING_FOR_RECEIVE) {
                    log.info("用户完成支付，待收货");
                    return;
                }
                if (transition.getSource().getId() == States.WAITING_FOR_RECEIVE
                        && transition.getTarget().getId() == States.DONE) {
                    log.info("用户已收货，订单完成");
                    return;
                }

            }
        };
    }

    @Bean
    public Action<States,Events> action(){
        return new Action<States, Events>() {
            @Override
            public void execute(StateContext<States, Events> stateContext) {
                System.out.println(stateContext.getMessageHeader("aa"));
            }
        };
    }

    @Bean
    public Guard<States,Events> guard(){
        return new Guard<States, Events>() {
            @Override
            public boolean evaluate(StateContext<States, Events> stateContext) {
                System.out.println(stateContext.getMessageHeader("aa"));
                return true;
            }
        };
    }
}
*/

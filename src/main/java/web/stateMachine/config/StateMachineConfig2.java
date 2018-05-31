//package web.stateMachine.config;




/**
 * @Author: pengjianzhou
 * @Description:
 * @Date: Created in 下午2:37 18-5-10
 */
//@Slf4j
//@Configuration
//@EnableStateMachine
//public class StateMachineConfig2 extends EnumStateMachineConfigurerAdapter<States, Events> {


//    private static final HashSet<States> states = new HashSet<>();
//    public static final HashSet<ConfigEntity> configEntities = new HashSet <>(Arrays.asList(
//        new ConfigEntity(1,States.UNPAID,States.WAITING_FOR_SEND,Events.PAY,"",001),
//        new ConfigEntity(1,States.WAITING_FOR_SEND,States.WAITING_FOR_RECEIVE,Events.SEND,"",001),
//        new ConfigEntity(1,States.WAITING_FOR_RECEIVE,States.DONE,Events.USER_SIGNED,"",001)
//));



//    public static HashSet<States> getStates() {
//        return states;
//    }
//


//
//    @Override
//    public void configure(StateMachineStateConfigurer<States, Events> states)
//            throws Exception {
//
//        // 定义状态机中的状态
//        states
//                .withStates()
//                .initial(States.UNPAID)  // 初始状态
//                .states(EnumSet.allOf(States.class));
//    }

    //初始化当前状态机拥有哪些状态
//    @Override
//    public void configure(StateMachineTransitionConfigurer<States, Events> transitions)
//            throws Exception {
//        transitions
//                .withExternal()
//                .source(States.UNPAID).target(States.WAITING_FOR_RECEIVE)  // 指定状态来源和目标
//                .event(Events.PAY)   // 指定触发事件
//
//                .and()
//                .withExternal()
//                .source(States.WAITING_FOR_RECEIVE).target(States.DONE)
//                .event(Events.USER_SIGNED);
//    }

//    @Override
//    public void configure(StateMachineConfigurationConfigurer<States, Events> config)
//            throws Exception {
//        config
//                .withConfiguration()
//                .listener(listener());   // 指定状态机的处理监听器
//    }

 /*   @Bean
    public StateMachineListener<States, Events> listener() {
        return new StateMachineListenerAdapter<States, Events>() {
            @Override
            public void transition(Transition<States, Events> transition) {
                if(transition.getTarget().getId() == States.UNPAID) {
                    log.info("订单创建，待支付");
                    return;
                }
                if(transition.getSource().getId() == States.UNPAID
                        && transition.getTarget().getId() == States.WAITING_FOR_RECEIVE) {
                    log.info("用户完成支付，待收货");
                    return;
                }
                if(transition.getSource().getId() == States.WAITING_FOR_RECEIVE
                        && transition.getTarget().getId() == States.DONE) {
                    log.info("用户已收货，订单完成");
                    return;
                }
            }
        };
    }*/



//}
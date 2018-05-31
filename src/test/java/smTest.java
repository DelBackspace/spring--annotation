
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import web.stateMachine.OrderServiceImpl;

/**
 * @Author: pengjianzhou
 * @Description:
 * @Date: Created in 下午2:53 18-5-10
 */
public class smTest extends BaseTestConfig {

//    @Autowired
//    private StateMachine<States, Events> stateMachine;
//
//    @Test
//    public void runStatus(){
//
//        stateMachine.start();
//        stateMachine.sendEvent(Events.PAY);
//
//    }



  /*  @Test
    public void runStatus2() throws Exception{
        StateMachine<States, Events> stateMachine2 = makeStateMachine.getStateMachine(States.WAITING_FOR_RECEIVE);
//        stateMachine2.sendEvent(Events.USER_SIGNED);
        stateMachine2.sendEvent(MessageBuilder
                .withPayload(Events.USER_SIGNED)
                .setHeader("aa", "测试头部") // header中可以存放相关数据信息，
                // 这些信息，在执行过程中，可以在监听器和拦截器中获取到，通过拦截器你可以在做额外的一些事情
                .build());
    }*/

    @Autowired
    private OrderServiceImpl orderService;

    @Test
    public void testMultThread(){
        orderService.creat();
        orderService.creat();

        orderService.pay(1);

        new Thread(()->{
            orderService.deliver(1);
            orderService.receive(1);
        }).start();

        orderService.pay(2);
        orderService.deliver(2);
        orderService.receive(2);

        System.out.println(orderService.getOrders());
    }

    @Test
    public void testRestory(){
        orderService.creat();

        orderService.deliver(1);

//        new Thread(()->{
//            orderService.deliver(1);
//            orderService.receive(1);
//        }).start();
//
//        orderService.pay(2);
//        orderService.deliver(2);
//        orderService.receive(2);

        System.out.println(orderService.getOrders());
    }
}

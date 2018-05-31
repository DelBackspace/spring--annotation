package web.stateMachine.config;


import org.springframework.messaging.Message;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;
import org.springframework.stereotype.Component;
import web.stateMachine.enums.Order;
import web.stateMachine.enums.OrderStatus;
import web.stateMachine.enums.OrderStatusChangeEvent;

/**
 * @Author: pengjianzhou
 * @Description:
 * @Date: Created in 下午6:03 18-5-11
 */
//@Component
//@WithStateMachine(name = "orderStateMachine")
@WithStateMachine(id = "machine1")
public class OrderStateListenerImpl{

    @OnTransition(target = "WAIT_PAYMENT")
    public boolean start(Message<OrderStatusChangeEvent> message) {
//        Order order = (Order) message.getHeaders().get("order");
//        order.setStatus(OrderStatus.WAIT_PAYMENT);
        System.out.println("启动 headers=" + message);
        return true;
    }

    @OnTransition(source = "WAIT_PAYMENT", target = "WAIT_DELIVER")
    public boolean payTransition(Message<OrderStatusChangeEvent> message) {
        Order order = (Order) message.getHeaders().get("order");
        order.setStatus(OrderStatus.WAIT_DELIVER);
        System.out.println("支付 headers=" + message.getHeaders().toString());
        return true;
    }

    @OnTransition(source = "WAIT_DELIVER", target = "WAIT_RECEIVE")
    public boolean deliverTransition(Message<OrderStatusChangeEvent> message) {
        Order order = (Order) message.getHeaders().get("order");
        order.setStatus(OrderStatus.WAIT_RECEIVE);
        System.out.println("发货 headers=" + message.getHeaders().toString());
        return true;
    }

    @OnTransition(source = "WAIT_RECEIVE", target = "FINISH")
    public boolean receiveTransition(Message<OrderStatusChangeEvent> message){
        Order order = (Order) message.getHeaders().get("order");
        order.setStatus(OrderStatus.FINISH);
        System.out.println("收货 headers=" + message.getHeaders().toString());
        return true;
    }
}

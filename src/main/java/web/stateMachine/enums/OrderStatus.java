package web.stateMachine.enums;

/**
 * @Author: pengjianzhou
 * @Description:
 * @Date: Created in 下午5:58 18-5-11
 */
/**
 * 订单状态
 */
public enum OrderStatus {
    // 待支付，待发货，待收货，订单结束
    WAIT_PAYMENT, WAIT_DELIVER, WAIT_RECEIVE, FINISH;
}

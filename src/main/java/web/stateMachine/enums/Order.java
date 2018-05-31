package web.stateMachine.enums;

/**
 * @Author: pengjianzhou
 * @Description:
 * @Date: Created in 下午6:01 18-5-11
 */
public class Order {

    private Integer id;
    private OrderStatus status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}

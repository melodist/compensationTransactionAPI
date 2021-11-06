package MELT.compensationTransactionAPI.domains;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 주문 객체
 *
 * Created by melodist
 * User: MELT
 * Date: 2021-09-29 029
 * Time: 오후 10:14
 */
@Getter
@Entity(name = "orders")
public class Order {

    @Id @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItemList = new ArrayList<>();

    private Long itemId;
    private int stock;

    //==생성 메서드==//
    protected Order() {}

    private Order(OrderStatus orderStatus, Long itemId, int stock) {
        this.status = orderStatus;
        this.itemId = itemId;
        this.stock = stock;
    }

    public static Order createOrder(Long itemId, int stock) {
        return new Order(OrderStatus.PENDING, itemId, stock);
    }

    //==갱신 메서드==/
    public void approveOrder() { this.status = OrderStatus.APPROVED; }

    public void cancelOrder() {
        this.status = OrderStatus.CANCEL;
    }

    //==연관관계 메서드==/
}

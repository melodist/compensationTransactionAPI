package MELT.compensationTransactionAPI.domains;

import javax.persistence.*;

/**
 * 주문상품 객체
 *
 * Created by melodist
 * User: MELT
 * Date: 2021-09-29 029
 * Time: 오후 10:51
 */
@Entity
public class OrderItem {

    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    private Long itemId; // 상품 ID

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order; // 주문 객체
}

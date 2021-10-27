package MELT.compensationTransactionAPI.repository;

import MELT.compensationTransactionAPI.domains.Order;
import MELT.compensationTransactionAPI.domains.QOrder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by melodist
 * User: MELT
 * Date: 2021-10-02 002
 * Time: 오후 5:07
 */
@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager em;

    public void save(Order order) {
        em.persist(order);
    }

    public List<Order> findAll() {
        QOrder o = QOrder.order;
        JPAQueryFactory query = new JPAQueryFactory(em);
        return query.selectFrom(o).fetch();
    }
}

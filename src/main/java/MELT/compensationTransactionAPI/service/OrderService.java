package MELT.compensationTransactionAPI.service;

import MELT.compensationTransactionAPI.domains.Order;
import MELT.compensationTransactionAPI.repository.OrderRepository;
import MELT.compensationTransactionAPI.utils.orchestrator.service.CompStdCallService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by melodist
 * User: MELT
 * Date: 2021-10-02 002
 * Time: 오후 5:07
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final CompStdCallService compStdCallService;

    public void createOrder(Long itemId, int count) {
        // 주문 생성
        Order order = Order.createOrder(itemId, count);

        // 상품 재고 차감
        decreaseItem(itemId, count);

        // 결제 API 호출
        if (createBill()) {
            log.debug("Billing Succeed");
            // 주문 승인
            order.approveOrder();
        } else {
            log.debug("Billing Failed");
            // 보상 트랜잭션 API 호출
            Map<String, String> params = new HashMap<>();
            params.put("id", "1");
            params.put("count", String.valueOf(count));
            compStdCallService.callCompensationTransaction("SVC01", params);

            // 주문 실패
            order.cancelOrder();
        }

        orderRepository.save(order);
    }

    public void decreaseItem(Long itemId, int count) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> params = new HashMap<>();
        params.put("id", itemId.toString());
        params.put("count", String.valueOf(count));
        restTemplate.put("http://localhost:9100/item/decreaseStock/{id}/{count}", null, params);
    }

    public boolean createBill() {
        RestTemplate restTemplate = new RestTemplate();
        try {
            restTemplate.exchange("http://localhost:9200/bill/createBill", HttpMethod.PUT, HttpEntity.EMPTY, String.class, new HashMap<>());
        } catch (HttpServerErrorException e) {
            log.debug(e.getStatusText());
            return false;
        }
        return true;
    }
}

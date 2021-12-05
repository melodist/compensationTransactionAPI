package MELT.compensationTransactionAPI.service;

import MELT.compensationTransactionAPI.config.ApplicationYmlRead;
import MELT.compensationTransactionAPI.domains.Message;
import MELT.compensationTransactionAPI.domains.Order;
import MELT.compensationTransactionAPI.domains.OrderDto;
import MELT.compensationTransactionAPI.domains.item.ItemDto;
import MELT.compensationTransactionAPI.repository.OrderRepository;
import MELT.compensationTransactionAPI.utils.orchestrator.service.CompStdCallService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    private final ApplicationYmlRead applicationYmlRead;

    private String url =applicationYmlRead.getUrl();
    private String portItem = applicationYmlRead.getPort_item();
    private String portBill = applicationYmlRead.getPort_bill();

    public Long createOrder(Long itemId, int count) {
        // 주문 생성
        Order order = Order.createOrder(itemId, count);

        // 상품 재고 차감
        decreaseItem(itemId, count);

        // boolean billingResult = createBill();
        boolean billingResult = createBillV2(itemId);
        Long orderId = -1L;

        // 결제 API 호출
        if (billingResult) {
            log.debug("Billing Succeed");
            // 주문 승인
            order.approveOrder();
        } else {
            log.debug("Billing Failed");
            // 보상 트랜잭션 API 호출
            Map<String, String> params = new HashMap<>();
            params.put("id", String.valueOf(itemId));
            params.put("count", String.valueOf(count));
            compStdCallService.callCompensationTransaction("SVC01", params);

            // 주문 실패
            order.cancelOrder();
        }

        orderRepository.save(order);

        if (billingResult) {
            orderId = order.getId();
        } else {
            orderId = -order.getId();
        }

        return orderId;
    }

    /**
     * 상품의 재고를 차감한다.
     * @param itemId : 상품 ID
     * @param count : 차감할 재고량
     */
    public void decreaseItem(Long itemId, int count) {
        RestTemplate restTemplate = new RestTemplate();

        // 재고 확인
        ResponseEntity<Message<ItemDto>> response = restTemplate.exchange(url + portItem + "/item/{id}/",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Message<ItemDto>>() {}, itemId);
        ItemDto findItemDto = response.getBody().getData();
        log.debug("findItemDto : {}", findItemDto.toString());

        // 재고 변경
        Map<String, String> params = new HashMap<>();
        params.put("id", itemId.toString());
        params.put("stock", String.valueOf(findItemDto.getStock() - count));
        restTemplate.put(url+ portItem + "/item/{id}/{stock}", null, params);
    }

    /**
     * 상품의 재고를 증가시킨다.
     * @param itemId : 상품 ID
     * @param count : 증가시킬 재고량
     */
    public void increaseItem(Long itemId, int count) {
        RestTemplate restTemplate = new RestTemplate();

        // 재고 확인
        ResponseEntity<Message<ItemDto>> response = restTemplate.exchange(url + portItem + "/item/{id}/",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Message<ItemDto>>() {}, itemId);
        ItemDto findItemDto = response.getBody().getData();
        log.debug("findItemDto : {}", findItemDto.toString());

        // 재고 변경
        Map<String, String> params = new HashMap<>();
        params.put("id", itemId.toString());
        params.put("stock", String.valueOf(findItemDto.getStock() + count));
        restTemplate.put(url + portItem + "/item/{id}/{stock}", null, params);
    }

    public boolean createBill() {
        RestTemplate restTemplate = new RestTemplate();
        try {
            restTemplate.exchange(url + portBill + "/bill/createBill", HttpMethod.PUT, HttpEntity.EMPTY, String.class, new HashMap<>());
        } catch (HttpServerErrorException e) {
            log.debug(e.getStatusText());
            return false;
        }
        return true;
    }

    /**
     * 상품 ID에 따라 다른 결제 결과를 반환한다.
     * @param itemId : 상품 ID
     * @return 결제 성공시 true, 실패시 false
     */
    public boolean createBillV2(Long itemId) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            Map<String, String> params = new HashMap<>();
            params.put("id", itemId.toString());
            restTemplate.getForEntity(url + portBill + "/bill/createBill/{id}", String.class, params);
        } catch (HttpServerErrorException e) {
            log.debug(e.getStatusText());
            return false;
        }
        return true;
    }

    /**
     * 주문 내역을 조회한다.
     * @return 주문 DTO 리스트
     */
    public List<OrderDto> findAll() {
        List<Order> orders = orderRepository.findAll();
        return orders
                .stream()
                .map(o -> new OrderDto(o.getId(), o.getStatus(), o.getItemId(), o.getStock()))
                .collect(Collectors.toList());
    }
}

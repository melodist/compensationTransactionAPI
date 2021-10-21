package MELT.compensationTransactionAPI.controller;

import MELT.compensationTransactionAPI.domains.Message;
import MELT.compensationTransactionAPI.domains.OrderDto;
import MELT.compensationTransactionAPI.service.OrderService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * Created by melodist
 * User: MELT
 * Date: 2021-10-02 002
 * Time: 오후 5:41
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @PutMapping("/order/{id}/{count}")
    @ApiOperation(value = "주문 생성", notes = "상품 ID와 수량으로 주문을 생성한다.")
    public ResponseEntity<Message<OrderDto>> createOrder(@PathVariable Long id, @PathVariable int count) {
        Message<OrderDto> message;
        OrderDto orderDto = new OrderDto(id, count);

        if (orderService.createOrder(id, count)) {
            message = new Message<>("200 OK", "주문에 성공하였습니다.", orderDto);
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            message = new Message<>("500 Internal Server Error"
                    ,"주문이 실패하였습니다. 보상 트랜잭션 실행 내역을 확인해주세요", orderDto);
            return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/compTrx/{id}/{count}")
    @ApiIgnore
    public ResponseEntity createCompTrx(@PathVariable Long id, @PathVariable int count) {
        orderService.increaseItem(id, count);
        return new ResponseEntity(HttpStatus.OK);
    }
}

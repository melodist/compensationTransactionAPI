package MELT.compensationTransactionAPI.controller;

import MELT.compensationTransactionAPI.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PutMapping("/createOrder/{id}/{count}")
    public ResponseEntity createOrder(@PathVariable Long id, @PathVariable int count) {
        orderService.createOrder(id, count);
        return new ResponseEntity(HttpStatus.OK);
    }
}

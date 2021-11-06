package MELT.compensationTransactionAPI.web.controller;

import MELT.compensationTransactionAPI.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 주문 Web Controller
 *
 * Created by melodist
 * User: MELT
 * Date: 2021-11-06 006
 * Time: 오후 7:41
 */
@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
@ApiIgnore
public class OrderWebController {

    private final OrderService orderService;

    /**
     * 주문 내역을 조회한다.
     * @param model
     * @return 주문 내역 View 주소
     */
    @GetMapping("/")
    public String orderView(Model model) {
        model.addAttribute("result", orderService.findAll());
        return "order/order";
    }
}

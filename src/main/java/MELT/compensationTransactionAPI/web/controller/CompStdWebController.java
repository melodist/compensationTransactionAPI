package MELT.compensationTransactionAPI.web.controller;

import MELT.compensationTransactionAPI.utils.orchestrator.enums.HttpStatus;
import MELT.compensationTransactionAPI.utils.orchestrator.enums.RestMethod;
import MELT.compensationTransactionAPI.utils.orchestrator.enums.SyncStatus;
import MELT.compensationTransactionAPI.utils.orchestrator.model.CompStd;
import MELT.compensationTransactionAPI.utils.orchestrator.model.CompStdCondition;
import MELT.compensationTransactionAPI.utils.orchestrator.service.CompStdService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@Controller
@RequestMapping("/compStd")
@RequiredArgsConstructor
@ApiIgnore
public class CompStdWebController {

    private final CompStdService compStdService;

    /**
     * Practice method for thymeleaf grid
     * @param compStdCondition
     * @param model
     * @return
     */
    @GetMapping("/")
    public String compStdView(CompStdCondition compStdCondition, Model model) {
        List<CompStd> result = compStdService.findAll();
        model.addAttribute("result", result);
        return "compTrx/compStd";
    }

    @GetMapping("/addService")
    public String addService(Model model) {
        var compStdA = new CompStd("SVC01"
                ,"http://localhost:9000/order/compTrx/{id}/{stock}"
                ,HttpStatus.HTTP
                ,SyncStatus.SYNC
                ,RestMethod.PUT
                ,5);
        compStdService.insert(compStdA);
        List<CompStd> result = compStdService.findAll();
        model.addAttribute("result", result);
        return "redirect:";
    }

    /**
     * 보상 트랜잭션 서비스 추가 화면을 호출한다.
     * @return
     */
    @GetMapping("/addForm")
    public String addForm() {
        return "compTrx/addForm";
    }
}

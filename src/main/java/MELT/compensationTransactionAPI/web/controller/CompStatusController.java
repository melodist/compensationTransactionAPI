package MELT.compensationTransactionAPI.web.controller;

import MELT.compensationTransactionAPI.domain.CompStd;
import MELT.compensationTransactionAPI.domain.CompStdCondition;
import MELT.compensationTransactionAPI.service.CompStdService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/compStatus")
@RequiredArgsConstructor
public class CompStatusController {

    private final CompStdService compStdService;

    /**
     * Practice method for thymeleaf grid
     * @param compStdCondition
     * @param model
     * @return
     */
    @GetMapping("/basicView")
    public String basicView(CompStdCondition compStdCondition, Model model) {

        var compStdA = new CompStd("test", "Y", "Sync", "POST", 5);
        var compStdB = new CompStd("test", "Y", "Sync", "POST", 5);

        compStdService.insert(compStdA);
        compStdService.insert(compStdB);

        List<CompStd> result = compStdService.findAll();
        model.addAttribute("result", result);
        return "compTrx/basicView";
    }

    /**
     * 보상 트랜잭션 서비스 추가 화면을 호출한다.
     * @return
     */
    @GetMapping("/addForm")
    public String addForm() {
        return "compTrx/addForm";
    }

    /**
     * 보상 트랜잭션 서비스를 저장한다.
     * @return
     */
    @PostMapping("/addForm")
    public String addService(CompStd compStd) {
        // TODO 보상 트랜잭션 서비스 저장 로직
        return "redirect:/compStatus/basicView";
    }
}

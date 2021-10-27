package MELT.compensationTransactionAPI.web.controller;

import MELT.compensationTransactionAPI.utils.orchestrator.model.CompLog;
import MELT.compensationTransactionAPI.utils.orchestrator.model.CompLogDto;
import MELT.compensationTransactionAPI.utils.orchestrator.service.CompLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by melodist
 * User: MELT
 * Date: 2021-10-23 023
 * Time: 오전 9:30
 */
@Slf4j
@Controller
@RequestMapping("/compLog")
@RequiredArgsConstructor
public class CompLogController {

    private final CompLogService compLogService;

    /**
     * 보상 트랜잭션 API 로그를 조회한다.
     * @param model
     * @return
     */
    @GetMapping
    public String compLogView(Model model) {
        List<CompLog> findResult = compLogService.findAll();
        List<CompLogDto> result = findResult.stream()
                .map(s -> new CompLogDto(s.getCompStd().getApiId(),
                        s.getCompStatus(),
                        s.getInsDtm().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                        s.getCompDtm().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                ))
                .collect(Collectors.toList());
        model.addAttribute("result", result);
        return "compTrx/compLog";
    }
}

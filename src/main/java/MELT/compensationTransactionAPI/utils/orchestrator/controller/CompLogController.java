package MELT.compensationTransactionAPI.utils.orchestrator.controller;

import MELT.compensationTransactionAPI.utils.orchestrator.model.CompLog;
import MELT.compensationTransactionAPI.utils.orchestrator.model.CompLogDto;
import MELT.compensationTransactionAPI.utils.orchestrator.model.CompStd;
import MELT.compensationTransactionAPI.utils.orchestrator.service.CompLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by melodist
 * User: MELT
 * Date: 2021-09-18 018
 * Time: 오후 9:11
 *
 * 보상 트랜잭션 API 로그 Controller
 */
@Slf4j
@Controller
@RequestMapping("/compLog")
@RequiredArgsConstructor
@ApiIgnore
public class CompLogController {

    private final CompLogService compLogService;

    /**
     * 보상 트랜잭션 API 로그를 생성한다.
     * @param compStd
     * @return
     */
    @PostMapping("/compLog/createLog")
    public String createLog(CompStd compStd) {
        compLogService.createLog(compStd);
        return "success";
    }

    /**
     * 보상 트랜잭션 API 로그를 조회한다.
     * @param model
     * @return
     */
    @GetMapping
    public String compLogView(Model model) {
        List<CompLog> findResult = compLogService.findAll();
        List<CompLogDto> result = findResult.stream()
                .map(s -> {
                    return new CompLogDto(s.getCompStd().getApiId(),
                            s.getCompStatus(),
                            s.getInsDtm().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                            s.getCompDtm().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                    );
                })
                .collect(Collectors.toList());
        model.addAttribute("result", result);
        return "compTrx/compLog";
    }
}

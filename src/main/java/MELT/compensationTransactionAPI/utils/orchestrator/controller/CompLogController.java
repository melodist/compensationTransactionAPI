package MELT.compensationTransactionAPI.utils.orchestrator.controller;

import MELT.compensationTransactionAPI.utils.orchestrator.model.CompStd;
import MELT.compensationTransactionAPI.utils.orchestrator.service.CompLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

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
        return null;
    }
}

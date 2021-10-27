package MELT.compensationTransactionAPI.utils.orchestrator.controller;

import MELT.compensationTransactionAPI.utils.orchestrator.model.CompStd;
import MELT.compensationTransactionAPI.utils.orchestrator.model.Message;
import MELT.compensationTransactionAPI.utils.orchestrator.service.CompLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
@RestController
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
    @PostMapping("/compLog")
    public ResponseEntity<Message> createLog(CompStd compStd) {
        Message message = new Message("200 OK", "보상 트랜잭션 API 로그를 생성하였습니다.");
        compLogService.createLog(compStd);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}

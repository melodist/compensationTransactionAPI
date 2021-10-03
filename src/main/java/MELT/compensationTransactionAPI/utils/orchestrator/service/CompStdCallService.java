package MELT.compensationTransactionAPI.utils.orchestrator.service;

import MELT.compensationTransactionAPI.utils.orchestrator.enums.SyncStatus;
import MELT.compensationTransactionAPI.utils.orchestrator.model.CompStatus;
import MELT.compensationTransactionAPI.utils.orchestrator.model.CompStd;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * Created by melodist
 * User: MELT
 * Date: 2021-09-10 010
 * Time: 오후 5:25
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class CompStdCallService {

    private final CompStdService compStdService;
    private final CompLogService compLogService;
    private final RestTemplate restTemplate = new RestTemplate();

    /**
     * 보상 트랜잭션 서비스 호출
     * 1. 요청한 ID로 보상 트랜잭션 서비스 조회
     * 2. 보상 트랜잭션 이력 저장
     * 3. 동기, 비동기 여부 확인에 따라 다른 메소드 호출
     * 4. 보상 트랜잭션 이력 완료
     */
    public boolean callCompensationTransaction(String serviceId, Map params) {
        // 1. 요청한 ID로 보상 트랜잭션 서비스 조회
        CompStd request = compStdService.findOneByApiId(serviceId);

        // 2. 보상 트랜잭션 이력 저장
        Long logId = compLogService.createLog(request);

        // 3. 동기, 비동기 여부 확인에 따라 다른 메소드 호출
        ResponseEntity<String> response = new ResponseEntity(HttpStatus.BAD_REQUEST);
        if (request.getIsSync().equals(SyncStatus.SYNC)) {
            // call service
            // if exception -> 보상 트랜잭션 서비스 호출
            response = syncMethodCall(request.getUrl(), params);
        } else if (request.getIsSync().equals(SyncStatus.ASYNC)) {
            response = asyncMethodCall(request.getUrl());
        }

        // 4. 보상 트랜잭션 성공 여부 기록
        return updateCompensationTransactionLog(logId, response);
    }

    private ResponseEntity syncMethodCall (String url, Map params) {
        HttpEntity entity = HttpEntity.EMPTY;
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, entity, String.class, params);
        return response;
    }

    @Async
    public ResponseEntity asyncMethodCall(String url) {
        return restTemplate.getForEntity(url, String.class);
    }

    // 성공, 실패 여부 기록
    private boolean updateCompensationTransactionLog(Long logId, ResponseEntity response) {
        if (response.getStatusCode().is2xxSuccessful()) {
            compLogService.updateLog(logId, CompStatus.COMPLETED);
            log.debug("Compensation Transaction Service Completed");
            return true;
        } else {
            compLogService.updateLog(logId, CompStatus.ERROR);
            log.debug("Compensation Transaction Service Error");
            return false;
        }
    }
}
;
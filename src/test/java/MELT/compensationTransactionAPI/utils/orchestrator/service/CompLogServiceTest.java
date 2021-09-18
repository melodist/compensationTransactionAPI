package MELT.compensationTransactionAPI.utils.orchestrator.service;

import MELT.compensationTransactionAPI.utils.orchestrator.enums.HttpStatus;
import MELT.compensationTransactionAPI.utils.orchestrator.enums.RestMethod;
import MELT.compensationTransactionAPI.utils.orchestrator.enums.SyncStatus;
import MELT.compensationTransactionAPI.utils.orchestrator.model.CompLog;
import MELT.compensationTransactionAPI.utils.orchestrator.model.CompStatus;
import MELT.compensationTransactionAPI.utils.orchestrator.model.CompStd;
import MELT.compensationTransactionAPI.utils.orchestrator.repository.CompLogRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 보상 트랜잭션 이력 Service Test
 *
 * Created by melodist
 * User: MELT
 * Date: 2021-09-08 008
 * Time: 오전 12:14
 */
@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class CompLogServiceTest {

    @Autowired CompLogService compLogService;
    @Autowired CompLogRepository compLogRepository;
    @Autowired CompStdService compStdService;

    @Test
    void 저장() {
        //given
        CompStd compStd = new CompStd("test001", "TEST", HttpStatus.HTTP, SyncStatus.SYNC, RestMethod.POST, 5);
        String savedId = compStdService.insert(compStd);
        log.debug("savedId : {}", savedId);

        //when
        CompLog savedLog = compLogService.createLog(compStd);
        log.debug("savedId : {}", savedLog.getCompStd().getId());

        //then
        assertEquals(savedId, savedLog.getCompStd().getId());
    }

    /**
     * 전체 이력 조회 테스트
     */
    @Test
    void 전체이력조회() {
        //given
        CompStd compStd1 = new CompStd("test001", "TEST", HttpStatus.HTTP, SyncStatus.SYNC, RestMethod.POST, 5);
        CompStd compStd2 = new CompStd("test002", "TEST", HttpStatus.HTTP, SyncStatus.SYNC, RestMethod.POST, 5);
        String savedId1 = compStdService.insert(compStd1);
        String savedId2 = compStdService.insert(compStd2);
        compLogService.createLog(compStd1);
        compLogService.createLog(compStd2);

        //when
        List<String> result = compLogService.findAll()
                .stream().map(compLog -> compLog.getCompStd().getId()).collect(Collectors.toList());

        //then
        assertEquals(result, Arrays.asList(savedId1, savedId2) );
    }

}
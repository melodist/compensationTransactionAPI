package MELT.compensationTransactionAPI.service;

import MELT.compensationTransactionAPI.domain.CompLog;
import MELT.compensationTransactionAPI.domain.CompStatus;
import MELT.compensationTransactionAPI.repository.CompLogRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 보상 트랜잭션 이력 Service Test
 *
 * Created by melodist
 * User: MELT
 * Date: 2021-09-08 008
 * Time: 오전 12:14
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class CompLogServiceTest {

    @Autowired CompLogService compLogService;
    @Autowired CompLogRepository compLogRepository;

    @Test
    void 저장() {
        //given
        CompLog compLog = new CompLog(CompStatus.WAITING, "TEST", "TEST");

        //when
        CompLog savedLog = compLogService.save(compLog);

        //then
        assertEquals(compLog, savedLog);
    }

    /**
     * 전체 이력 조회 테스트
     */
    @Test
    void 전체이력조회() {
        //given
        CompLog compLog1 = new CompLog(CompStatus.WAITING, "TEST", "TEST");
        CompLog compLog2 = new CompLog(CompStatus.WAITING, "TEST", "TEST");
        compLogService.save(compLog1);
        compLogService.save(compLog2);

        //when
        List<CompLog> result = compLogService.findAll();

        //then
        assertEquals(result, Arrays.asList(compLog1, compLog2) );
    }

}
package MELT.compensationTransactionAPI.utils.orchestrator.service;

import MELT.compensationTransactionAPI.utils.orchestrator.enums.HttpStatus;
import MELT.compensationTransactionAPI.utils.orchestrator.enums.RestMethod;
import MELT.compensationTransactionAPI.utils.orchestrator.enums.SyncStatus;
import MELT.compensationTransactionAPI.utils.orchestrator.model.CompStd;
import MELT.compensationTransactionAPI.utils.orchestrator.model.CompStdCondition;
import MELT.compensationTransactionAPI.utils.orchestrator.repository.CompStdRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by melodist
 * User: MELT
 * Date: 2021-08-22 022
 * Time: 오전 12:08
 */

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class CompStdServiceTest {

    @Autowired CompStdService compStdService;
    @Autowired CompStdRepository compStdRepository;

    @Test
    void 서비스추가() {
        //given
        CompStd compStd = getCompStd();

        //when
        String saveId = compStdService.insert(compStd);

        //then
        assertEquals(compStd, compStdRepository.findOne(saveId));
    }

    private CompStd getCompStd() {
        String id = "test001";
        String url = "test";
        HttpStatus isHttp = HttpStatus.HTTP;
        SyncStatus isSync = SyncStatus.SYNC;
        RestMethod restMethod = RestMethod.POST;
        Integer retryCnt = 5;

        return new CompStd(id, url, isHttp, isSync, restMethod, retryCnt);
    }

    @Test
    void 조건조회() {
        //given
        compStdService.insert(getCompStd());

        CompStdCondition compStdCondition = new CompStdCondition("test", HttpStatus.HTTP, SyncStatus.SYNC, RestMethod.POST, "test");

        //when
        List<CompStd> compStds = compStdService.findCondition(compStdCondition);

        //then
        assertEquals(compStds.size(), 1);
    }
}
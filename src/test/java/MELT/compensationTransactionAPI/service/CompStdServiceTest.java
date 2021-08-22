package MELT.compensationTransactionAPI.service;

import MELT.compensationTransactionAPI.CompensationTransactionApiApplication;
import MELT.compensationTransactionAPI.domain.CompStd;
import MELT.compensationTransactionAPI.domain.CompStdCondition;
import MELT.compensationTransactionAPI.repository.CompStdRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
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
        Long saveId = compStdService.insert(compStd);

        //then
        assertEquals(compStd, compStdRepository.findOne(saveId));
    }

    private CompStd getCompStd() {
        String url = "test";
        String isHttp = "Y";
        String isSync = "Y";
        String restMethod = "POST";
        Integer retryCnt = 5;

        return new CompStd(url, isHttp, isSync, restMethod, retryCnt);
    }

    @Test
    void 조건조회() {
        //given
        compStdService.insert(getCompStd());

        String url = "test";
        String isHttp = "Y";
        String isSync = "Y";
        String restMethod = "POST";

        CompStdCondition compStdCondition = new CompStdCondition(url, isHttp, isSync, restMethod, "test");

        //when
        List<CompStd> compStds = compStdService.findCondition(compStdCondition);

        //then
        assertEquals(compStds.size(), 1);
    }
}
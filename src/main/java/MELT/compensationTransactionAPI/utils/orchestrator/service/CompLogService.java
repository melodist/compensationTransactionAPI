package MELT.compensationTransactionAPI.utils.orchestrator.service;

import MELT.compensationTransactionAPI.utils.orchestrator.model.CompLog;
import MELT.compensationTransactionAPI.utils.orchestrator.model.CompStatus;
import MELT.compensationTransactionAPI.utils.orchestrator.model.CompStd;
import MELT.compensationTransactionAPI.utils.orchestrator.repository.CompLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

/**
 * 보상 트랜잭션 이력 Service
 *
 * Created by melodist
 * User: MELT
 * Date: 2021-09-07 007
 * Time: 오후 11:53
 */
@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CompLogService {

    private final CompLogRepository compLogRepository;

    /**
     * 보상 트랜잭션 호출 이력을 신규 생성한다.
     * @param compStd
     * @return savedLog
     */
    @Transactional
    public CompLog createLog(CompStd compStd) {
        CompLog savedLog = new CompLog(compStd, compStd.getInsId(), compStd.getModId(), CompStatus.WAITING);
        return compLogRepository.save(savedLog);
    }

    /**
     * 보상 트랜잭션 호출 이력을 ID로 조회한다
     */
    public Optional<CompLog> findById(Long id) {
        return compLogRepository.findById(id);
    }

    /**
     * 보상 트랜잭션 호출 이력을 조회한다.
     * @return
     */
    public List<CompLog> findAll() {
        return compLogRepository.findAll();
    }




}

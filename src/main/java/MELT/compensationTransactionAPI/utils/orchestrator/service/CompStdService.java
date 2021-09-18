package MELT.compensationTransactionAPI.utils.orchestrator.service;

import MELT.compensationTransactionAPI.utils.orchestrator.model.CompStd;
import MELT.compensationTransactionAPI.utils.orchestrator.model.CompStdCondition;
import MELT.compensationTransactionAPI.utils.orchestrator.repository.CompStdRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CompStdService {

    private final CompStdRepository compStdRepository;

    /**
     * 1건 입력
     */
    @Transactional
    public String insert(CompStd compStd) {
        compStdRepository.save(compStd);
        return compStd.getId();
    }

    /**
     * ID로 조회
     */
    public CompStd findOne(String id) {
        return compStdRepository.findOne(id);
    }

    /**
     * 전체 조회
     */
    public List<CompStd> findAll() {
        return compStdRepository.findCompStds();
    }

    /**
     * 조건으로 조회
     */
    public List<CompStd> findCondition(CompStdCondition compStdCondition) {
        return compStdRepository.findCondition(compStdCondition);
    }
}

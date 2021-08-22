package MELT.compensationTransactionAPI.service;

import MELT.compensationTransactionAPI.domain.CompStd;
import MELT.compensationTransactionAPI.domain.CompStdCondition;
import MELT.compensationTransactionAPI.repository.CompStdRepository;
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
    public Long insert(CompStd compStd) {
        // 중복 서비스 검사
        compStdRepository.save(compStd);
        return compStd.getId();
    }

    /**
     * ID로 조회
     */
    public CompStd findOne(Long id) {
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

package MELT.compensationTransactionAPI.service;

import MELT.compensationTransactionAPI.domain.CompLog;
import MELT.compensationTransactionAPI.repository.CompLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

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
@RequiredArgsConstructor
public class CompLogService {

    private final CompLogRepository compLogRepository;

    @PostMapping("/compLog/save")
    public CompLog save(CompLog compLog) {
        return compLogRepository.save(compLog);
    }

    @GetMapping("/compLog/findAll")
    public List<CompLog> findAll() {
        return compLogRepository.findAll();
    }




}

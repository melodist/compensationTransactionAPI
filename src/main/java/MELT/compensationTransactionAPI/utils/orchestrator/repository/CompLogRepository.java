package MELT.compensationTransactionAPI.utils.orchestrator.repository;

import MELT.compensationTransactionAPI.utils.orchestrator.model.CompLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created by melodist
 * User: MELT
 * Date: 2021-09-07 007
 * Time: 오후 11:54
 */
@Repository
public interface CompLogRepository extends JpaRepository<CompLog, Long> {

    /**
     * 전체 조회
     */
    List<CompLog> findAll();

    /**
     * ID로 조회
     * @return
     */
    Optional<CompLog> findById(Long id);

    /**
     * 보상 트랜잭션 이력 저장
     * @return
     */
    CompLog save(CompLog compLog);

}

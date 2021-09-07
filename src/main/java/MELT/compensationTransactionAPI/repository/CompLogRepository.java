package MELT.compensationTransactionAPI.repository;

import MELT.compensationTransactionAPI.domain.CompLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

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
     * 보상 트랜잭션 이력 저장
     * @return
     */
    CompLog save(CompLog compLog);

}

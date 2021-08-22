package MELT.compensationTransactionAPI.repository;

import MELT.compensationTransactionAPI.domain.CompStd;
import MELT.compensationTransactionAPI.domain.CompStdCondition;
import MELT.compensationTransactionAPI.domain.QCompStd;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static MELT.compensationTransactionAPI.domain.QCompStd.compStd;

@Repository
@RequiredArgsConstructor
public class CompStdRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    /**
     * ID로 조회
     */
    public CompStd findOne(Long id) {
        return em.find(CompStd.class, id);
    }

    /**
     * 전체 목록 조회
     */
    public List<CompStd> findCompStds() {
        return em.createQuery("select m from CompStd m", CompStd.class)
                .getResultList();
    }

    /**
     * 조건으로 조회
     */
    public List<CompStd> findCondition(CompStdCondition compStdCondition) {
        return queryFactory.selectFrom(compStd)
                .where(compStd.url.like(compStdCondition.getUrl()))
                .where(compStd.isHttp.eq(compStdCondition.getIsHttp()))
                .where(compStd.isSync.eq(compStdCondition.getIsSync()))
                .where(compStd.restMethod.eq(compStdCondition.getRestMethod()))
                .fetch();
    }

    /**
     * 보상 트랜잭션 서비스 등록
     */
    public void save(CompStd compStd) {
        em.persist(compStd);
    }

}

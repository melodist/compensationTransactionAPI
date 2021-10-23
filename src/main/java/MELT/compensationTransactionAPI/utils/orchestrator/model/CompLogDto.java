package MELT.compensationTransactionAPI.utils.orchestrator.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 보상 트랜잭션 로그 DTO
 * Entity를 화면과 분리하는 역할
 *
 * Created by melodist
 * User: MELT
 * Date: 2021-10-23 023
 * Time: 오전 10:55
 */
@Data
@AllArgsConstructor
public class CompLogDto {

    private String apiId;
    private CompStatus compStatus;
    private String insDtm;
    private String compDtm;
}

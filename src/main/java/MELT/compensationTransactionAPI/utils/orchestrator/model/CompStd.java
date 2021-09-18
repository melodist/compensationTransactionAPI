package MELT.compensationTransactionAPI.utils.orchestrator.model;

import MELT.compensationTransactionAPI.utils.orchestrator.enums.HttpStatus;
import MELT.compensationTransactionAPI.utils.orchestrator.enums.RestMethod;
import MELT.compensationTransactionAPI.utils.orchestrator.enums.SyncStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "compstd")
@Getter
@NoArgsConstructor
public class CompStd {

    @Id
    @Column(name = "api_id")
    private String id;

    private String url;

    @Enumerated(EnumType.STRING)
    private HttpStatus isHttp;

    @Enumerated(EnumType.STRING)
    private SyncStatus isSync;

    @Enumerated(EnumType.STRING)
    private RestMethod restMethod;

    private Integer retryCnt;

    private String insId;
    private LocalDateTime insDtm;
    private String modId;
    private LocalDateTime modDtm;

    /******** 생성 메서드 ***********/
    public CompStd(String id, String url, HttpStatus isHttp, SyncStatus isSync, RestMethod restMethod, Integer retryCnt) {
        this.id = id;
        this.url = url;
        this.isHttp = isHttp;
        this.isSync = isSync;
        this.restMethod = restMethod;
        this.retryCnt = retryCnt;
    }

    /******* 연관관계 메서드 *********/
    public void updateUrl(String url) {
        this.url = url;
    }
}

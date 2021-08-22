package MELT.compensationTransactionAPI.domain;

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
    @GeneratedValue
    @Column(name = "compstd_id")
    private Long id;

    private String url;
    private String isHttp;
    private String isSync;
    private String restMethod;
    private Integer retryCnt;

    private String insId;
    private LocalDateTime insDtm;
    private String modId;
    private LocalDateTime modDtm;

    /******** 생성 메서드 ***********/
    public CompStd(String url, String isHttp, String isSync, String restMethod, Integer retryCnt) {
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

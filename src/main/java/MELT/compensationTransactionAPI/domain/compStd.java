package MELT.compensationTransactionAPI.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

public class compStd {

    @Id
    @GeneratedValue
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
}

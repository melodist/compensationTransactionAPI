package MELT.compensationTransactionAPI.utils.orchestrator.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class CompLog {

    @Id @GeneratedValue
    private Long id;

    private CompStatus compStatus;

    @ManyToOne
    @JoinColumn(name="api_id")
    private CompStd compStd;

    private String insId;
    private LocalDateTime insDtm;
    private String compId;
    private LocalDateTime compDtm;

    /******** 생성 메서드 ***********/
    protected CompLog() {}

    public CompLog(CompStd compStd, String insId, String compId, CompStatus compStatus) {
        this.compStd = compStd;
        this.compStatus = compStatus;
        this.insId = insId;
        this.insDtm = LocalDateTime.now();
        this.compId = compId;
        this.compDtm = LocalDateTime.now();
    }
}

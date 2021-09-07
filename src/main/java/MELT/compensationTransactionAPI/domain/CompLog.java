package MELT.compensationTransactionAPI.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class CompLog {

    @Id @GeneratedValue
    private Long id;

    private CompStatus compStatus;

    private String insId;
    private LocalDateTime insDtm;
    private String compId;
    private LocalDateTime compDtm;

    protected CompLog() {}

    public CompLog(CompStatus compStatus, String insId, String compId) {
        this.compStatus = compStatus;
        this.insId = insId;
        this.insDtm = LocalDateTime.now();
        this.compId = compId;
        this.compDtm = LocalDateTime.now();
    }
}

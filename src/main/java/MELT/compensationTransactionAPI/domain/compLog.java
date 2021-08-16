package MELT.compensationTransactionAPI.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class compLog {

    @Id @GeneratedValue
    private Long id;

    private CompStatus compStatus;

    private String insId;
    private LocalDateTime insDtm;
    private String compId;
    private LocalDateTime compDtm;
}

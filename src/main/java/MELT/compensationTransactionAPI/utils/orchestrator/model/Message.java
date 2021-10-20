package MELT.compensationTransactionAPI.utils.orchestrator.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by melodist
 * User: MELT
 * Date: 2021-10-20 020
 * Time: 오후 7:59
 */
@Data
@AllArgsConstructor
public class Message {

    private String status;
    private String message;
}

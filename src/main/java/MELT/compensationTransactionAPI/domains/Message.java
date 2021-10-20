package MELT.compensationTransactionAPI.domains;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by melodist
 * User: MELT
 * Date: 2021-10-20 020
 * Time: 오후 7:59
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message <T> {

    private String status;
    private String message;
    private T data;
}

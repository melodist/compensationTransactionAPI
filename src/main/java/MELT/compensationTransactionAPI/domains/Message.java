package MELT.compensationTransactionAPI.domains;

import io.swagger.annotations.ApiModelProperty;
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

    @ApiModelProperty(example = "상태코드")
    private String status;

    @ApiModelProperty(example = "메시지")
    private String message;

    @ApiModelProperty(example = "데이터")
    private T data;
}

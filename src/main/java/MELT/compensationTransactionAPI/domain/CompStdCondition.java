package MELT.compensationTransactionAPI.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by melodist
 * User: MELT
 * Date: 2021-08-22 022
 * Time: 오후 11:39
 */
@Getter
@AllArgsConstructor
public class CompStdCondition {

    private String url;
    private String isHttp;
    private String isSync;
    private String restMethod;
    private String insId;
}

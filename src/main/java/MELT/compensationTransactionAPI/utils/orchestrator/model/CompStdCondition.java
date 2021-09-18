package MELT.compensationTransactionAPI.utils.orchestrator.model;

import MELT.compensationTransactionAPI.utils.orchestrator.enums.HttpStatus;
import MELT.compensationTransactionAPI.utils.orchestrator.enums.RestMethod;
import MELT.compensationTransactionAPI.utils.orchestrator.enums.SyncStatus;
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
    private HttpStatus isHttp;
    private SyncStatus isSync;
    private RestMethod restMethod;
    private String insId;
}

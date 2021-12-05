package MELT.compensationTransactionAPI.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by melodist
 * User: MELT
 * Date: 2021-12-05 005
 * Time: 오후 4:55
 */
@Component
@ConfigurationProperties(prefix = "const")
@Data
public class ApplicationYmlRead {
    private String url;
    private String port_item;
    private String port_bill;
}

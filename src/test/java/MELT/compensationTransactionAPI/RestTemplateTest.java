package MELT.compensationTransactionAPI;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by melodist
 * User: MELT
 * Date: 2021-09-21 021
 * Time: 오후 5:23
 *
 * RestTemplate Test
 */
@SpringBootTest
public class RestTemplateTest {

    @Test
    void GET_테스트() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:9100/test/test", String.class);
        Assertions.assertThat(response.getBody()).isEqualTo("test_success");
    }

    @Test
    void GET_테스트_실패() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:9100/test/test", String.class);
        Assertions.assertThat(response.getBody()).isNotEqualTo("test_failed");
    }

    @Test
    void API_호출_테스트() {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> params = new HashMap<>();
        params.put("id", "1");
        params.put("count", "1");
        restTemplate.put("http://localhost:9100/test/decreaseStock/{id}/{count}", null, params);
    }
}

package MELT.compensationTransactionAPI.domains;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 주문 DTO
 *
 * Created by melodist
 * User: MELT
 * Date: 2021-10-20 020
 * Time: 오후 10:01
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private Long id; // 상품 ID
    private int count; // 주문한 상품 수
}

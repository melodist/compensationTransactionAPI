package MELT.compensationTransactionAPI.domains.item;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 상품 API DTO
 * Created by melodist
 * User: MELT
 * Date: 2021-10-11 011
 * Time: 오후 10:04
 */
@Data
@NoArgsConstructor
public class ItemDto {

    public long id;
    public int stock;
}

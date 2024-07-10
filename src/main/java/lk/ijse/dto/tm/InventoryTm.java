package lk.ijse.dto.tm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class InventoryTm {
    private String inventoryId;
    private String category;
    private String qty;
    private String price;

}

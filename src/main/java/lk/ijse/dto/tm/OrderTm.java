package lk.ijse.dto.tm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class OrderTm {
    private String orderId;
    private String customerId;
    private String totalAmount;
    private String date;
}

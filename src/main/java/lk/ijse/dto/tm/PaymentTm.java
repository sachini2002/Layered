package lk.ijse.dto.tm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class PaymentTm {
    private String paymentId;
    private String bookingId;
    private String method;
    private String amount;
    private String date;

}

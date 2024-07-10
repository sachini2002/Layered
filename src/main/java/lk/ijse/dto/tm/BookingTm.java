package lk.ijse.dto.tm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class BookingTm {
    private String bookingId;
    private String roomId;
    private String customerId;
    private String packageId;

}

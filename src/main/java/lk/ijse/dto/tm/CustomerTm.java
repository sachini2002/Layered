package lk.ijse.dto.tm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class CustomerTm {
    private String customerId;
    private String name;
    private String contact;
    private String address;
}

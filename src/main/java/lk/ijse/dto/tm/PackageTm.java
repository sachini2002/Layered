package lk.ijse.dto.tm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class PackageTm {
    private String packageId;
    private String name;
    private String type;
    private Double price;

}

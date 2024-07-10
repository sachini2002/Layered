package lk.ijse.dto.tm;

import lk.ijse.dao.impl.RoomDaoImpl;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class RoomTm extends RoomDaoImpl {
    private String roomId;
    private String roomNumber;
    private String roomType;
    private String roomRate;
    private String roomStatus;
}

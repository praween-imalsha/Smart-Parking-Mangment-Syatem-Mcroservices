package lk.ijse.parkingservice.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParkingSpaceDTO {
    private int id;
    private String location;
    private String zone;
    private String ownerName;
    private boolean available;
}

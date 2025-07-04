package lk.ijse.parkingservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParkingSpace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String location;
    private String zone;
    private String ownerName;
    private boolean available;
}

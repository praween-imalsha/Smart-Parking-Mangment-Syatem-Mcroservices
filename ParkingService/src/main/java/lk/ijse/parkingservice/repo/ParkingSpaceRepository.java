package lk.ijse.parkingservice.repo;

import lk.ijse.parkingservice.entity.ParkingSpace;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParkingSpaceRepository extends JpaRepository<ParkingSpace, Integer> {
    List<ParkingSpace> findByAvailable(boolean available);
    List<ParkingSpace> findByLocationContainingIgnoreCase(String location);
}

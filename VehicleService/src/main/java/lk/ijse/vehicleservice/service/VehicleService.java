
package lk.ijse.vehicleservice.service;

import lk.ijse.vehicleservice.dto.VehicleDTO;
import java.util.List;

public interface VehicleService {
    VehicleDTO createVehicle(VehicleDTO vehicleDTO);
    VehicleDTO getVehicle(int id);
    VehicleDTO updateVehicle(VehicleDTO vehicleDTO);
    void deleteVehicle(int id);
    List<VehicleDTO> getAllVehicles();
    VehicleDTO findByLicensePlate(String licensePlate);
    VehicleDTO updateVehicleStatus(int id, boolean inside);
}
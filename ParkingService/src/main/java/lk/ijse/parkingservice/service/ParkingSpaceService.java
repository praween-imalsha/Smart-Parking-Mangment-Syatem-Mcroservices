package lk.ijse.parkingservice.service;

import lk.ijse.parkingservice.Dto.ParkingSpaceDTO;

import java.util.List;

public interface ParkingSpaceService {
    ParkingSpaceDTO add(ParkingSpaceDTO dto);
    ParkingSpaceDTO update(ParkingSpaceDTO dto);
    ParkingSpaceDTO getById(int id);
    void delete(int id);
    List<ParkingSpaceDTO> getAll();
    List<ParkingSpaceDTO> getByLocation(String location);
    List<ParkingSpaceDTO> getByAvailability(boolean available);
    ParkingSpaceDTO setAvailability(int id, boolean available);
    ParkingSpaceDTO toggleStatus(int id);
}

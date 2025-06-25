package lk.ijse.vehicleservice.service;

import lk.ijse.vehicleservice.dto.VehicleDTO;
import lk.ijse.vehicleservice.entity.Vehicle;
import lk.ijse.vehicleservice.exception.DuplicateLicensePlateException;
import lk.ijse.vehicleservice.exception.VehicleNotFoundException;
import lk.ijse.vehicleservice.repo.VehicleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;

    public VehicleServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public VehicleDTO createVehicle(VehicleDTO vehicleDTO) {
        if (vehicleRepository.existsByLicensePlate(vehicleDTO.getLicensePlate())) {
            throw new DuplicateLicensePlateException("Vehicle with license plate " + vehicleDTO.getLicensePlate() + " already exists");
        }
        Vehicle vehicle = mapToEntity(vehicleDTO);
        Vehicle savedVehicle = vehicleRepository.save(vehicle);
        return mapToDTO(savedVehicle);
    }

    @Override
    public VehicleDTO getVehicle(int id) {
        return vehicleRepository.findById(id)
                .map(this::mapToDTO)
                .orElseThrow(() -> new VehicleNotFoundException("Vehicle not found with id: " + id));
    }

    @Override
    public VehicleDTO updateVehicle(VehicleDTO vehicleDTO) {
        if (!vehicleRepository.existsById(vehicleDTO.getId())) {
            throw new VehicleNotFoundException("Vehicle not found with id: " + vehicleDTO.getId());
        }

        // Check if license plate is being changed and if new one already exists
        Vehicle existingVehicle = vehicleRepository.findById(vehicleDTO.getId()).get();
        if (!existingVehicle.getLicensePlate().equals(vehicleDTO.getLicensePlate()) &&
                vehicleRepository.existsByLicensePlate(vehicleDTO.getLicensePlate())) {
            throw new DuplicateLicensePlateException("Vehicle with license plate " + vehicleDTO.getLicensePlate() + " already exists");
        }

        Vehicle vehicle = mapToEntity(vehicleDTO);
        Vehicle updatedVehicle = vehicleRepository.save(vehicle);
        return mapToDTO(updatedVehicle);
    }

    @Override
    public void deleteVehicle(int id) {
        if (!vehicleRepository.existsById(id)) {
            throw new VehicleNotFoundException("Vehicle not found with id: " + id);
        }
        vehicleRepository.deleteById(id);
    }

    @Override
    public List<VehicleDTO> getAllVehicles() {
        return vehicleRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public VehicleDTO findByLicensePlate(String licensePlate) {
        return vehicleRepository.findByLicensePlate(licensePlate)
                .map(this::mapToDTO)
                .orElseThrow(() -> new VehicleNotFoundException("Vehicle not found with license plate: " + licensePlate));
    }

    @Override
    public VehicleDTO updateVehicleStatus(int id, boolean inside) {
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new VehicleNotFoundException("Vehicle not found with id: " + id));
        vehicle.setInside(inside);
        Vehicle updatedVehicle = vehicleRepository.save(vehicle);
        return mapToDTO(updatedVehicle);
    }

    private Vehicle mapToEntity(VehicleDTO dto) {
        return Vehicle.builder()
                .id(dto.getId())
                .licensePlate(dto.getLicensePlate())
                .type(dto.getType())
                .color(dto.getColor())
                .userId(dto.getUserId())
                .inside(dto.isInside())
                .build();
    }

    private VehicleDTO mapToDTO(Vehicle entity) {
        return VehicleDTO.builder()
                .id(entity.getId())
                .licensePlate(entity.getLicensePlate())
                .type(entity.getType())
                .color(entity.getColor())
                .userId(entity.getUserId())
                .inside(entity.isInside())
                .build();
    }
}
package lk.ijse.parkingservice.service;

import lk.ijse.parkingservice.Dto.ParkingSpaceDTO;
import lk.ijse.parkingservice.entity.ParkingSpace;
import lk.ijse.parkingservice.repo.ParkingSpaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ParkingSpaceServiceImpl implements ParkingSpaceService {

    private final ParkingSpaceRepository repo;

    private ParkingSpaceDTO toDTO(ParkingSpace p) {
        return ParkingSpaceDTO.builder()
                .id(p.getId())
                .location(p.getLocation())
                .zone(p.getZone())
                .ownerName(p.getOwnerName())
                .available(p.isAvailable())
                .build();
    }

    private ParkingSpace toEntity(ParkingSpaceDTO dto) {
        return ParkingSpace.builder()
                .id(dto.getId())
                .location(dto.getLocation())
                .zone(dto.getZone())
                .ownerName(dto.getOwnerName())
                .available(dto.isAvailable())
                .build();
    }

    @Override
    public ParkingSpaceDTO add(ParkingSpaceDTO dto) {
        return toDTO(repo.save(toEntity(dto)));
    }

    @Override
    public ParkingSpaceDTO update(ParkingSpaceDTO dto) {
        ParkingSpace space = repo.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Not Found"));
        return toDTO(repo.save(toEntity(dto)));
    }

    @Override
    public ParkingSpaceDTO getById(int id) {
        return repo.findById(id).map(this::toDTO)
                .orElseThrow(() -> new RuntimeException("Not Found"));
    }

    @Override
    public void delete(int id) {
        repo.deleteById(id);
    }

    @Override
    public List<ParkingSpaceDTO> getAll() {
        return repo.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<ParkingSpaceDTO> getByLocation(String location) {
        return repo.findByLocationContainingIgnoreCase(location)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<ParkingSpaceDTO> getByAvailability(boolean available) {
        return repo.findByAvailable(available).stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public ParkingSpaceDTO setAvailability(int id, boolean available) {
        ParkingSpace p = repo.findById(id).orElseThrow();
        p.setAvailable(available);
        return toDTO(repo.save(p));
    }

    @Override
    public ParkingSpaceDTO toggleStatus(int id) {
        ParkingSpace p = repo.findById(id).orElseThrow();
        p.setAvailable(!p.isAvailable());
        return toDTO(repo.save(p));
    }
}

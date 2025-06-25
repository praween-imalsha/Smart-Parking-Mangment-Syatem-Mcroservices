package lk.ijse.parkingservice.controller;

import lk.ijse.parkingservice.Dto.ParkingSpaceDTO;
import lk.ijse.parkingservice.service.ParkingSpaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/parking")
@RequiredArgsConstructor
@CrossOrigin
public class ParkingSpaceController {

    private final ParkingSpaceService service;

    @PostMapping
    public ParkingSpaceDTO create(@RequestBody ParkingSpaceDTO dto) {
        return service.add(dto);
    }

    @PutMapping
    public ParkingSpaceDTO update(@RequestBody ParkingSpaceDTO dto) {
        return service.update(dto);
    }

    @GetMapping
    public List<ParkingSpaceDTO> all() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ParkingSpaceDTO getById(@PathVariable int id) {
        return service.getById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        service.delete(id);
    }

    @PutMapping("/{id}/status")
    public ParkingSpaceDTO setStatus(@PathVariable int id, @RequestParam boolean available) {
        return service.setAvailability(id, available);
    }

    @PutMapping("/{id}/toggle")
    public ParkingSpaceDTO toggle(@PathVariable int id) {
        return service.toggleStatus(id);
    }

    @GetMapping("/location")
    public List<ParkingSpaceDTO> byLocation(@RequestParam String location) {
        return service.getByLocation(location);
    }

    @GetMapping("/available/{status}")
    public List<ParkingSpaceDTO> byAvailability(@PathVariable boolean status) {
        return service.getByAvailability(status);
    }
}

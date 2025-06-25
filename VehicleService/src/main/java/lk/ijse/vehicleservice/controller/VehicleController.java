
package lk.ijse.vehicleservice.controller;

import jakarta.validation.Valid;
import lk.ijse.vehicleservice.dto.VehicleDTO;
import lk.ijse.vehicleservice.exception.DuplicateLicensePlateException;
import lk.ijse.vehicleservice.exception.VehicleNotFoundException;
import lk.ijse.vehicleservice.service.VehicleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping
    public ResponseEntity<VehicleDTO> createVehicle(@Valid @RequestBody VehicleDTO vehicleDTO) {
        return ResponseEntity.ok(vehicleService.createVehicle(vehicleDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleDTO> getVehicle(@PathVariable int id) {
        return ResponseEntity.ok(vehicleService.getVehicle(id));
    }

    @PutMapping
    public ResponseEntity<VehicleDTO> updateVehicle(@Valid @RequestBody VehicleDTO vehicleDTO) {
        return ResponseEntity.ok(vehicleService.updateVehicle(vehicleDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable int id) {
        vehicleService.deleteVehicle(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<VehicleDTO>> getAllVehicles() {
        return ResponseEntity.ok(vehicleService.getAllVehicles());
    }

    @GetMapping("/license/{licensePlate}")
    public ResponseEntity<VehicleDTO> getByLicensePlate(@PathVariable String licensePlate) {
        return ResponseEntity.ok(vehicleService.findByLicensePlate(licensePlate));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<VehicleDTO> updateVehicleStatus(
            @PathVariable int id,
            @RequestParam boolean inside) {
        return ResponseEntity.ok(vehicleService.updateVehicleStatus(id, inside));
    }

    @ControllerAdvice
    public static class GlobalExceptionHandler {

        @ExceptionHandler(VehicleNotFoundException.class)
        public ResponseEntity<Map<String, String>> handleVehicleNotFound(VehicleNotFoundException ex) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", ex.getMessage()));
        }

        @ExceptionHandler(DuplicateLicensePlateException.class)
        public ResponseEntity<Map<String, String>> handleDuplicateLicensePlate(DuplicateLicensePlateException ex) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(Map.of("error", ex.getMessage()));
        }
    }
}
#vehicle save ,get,delete,update url maping

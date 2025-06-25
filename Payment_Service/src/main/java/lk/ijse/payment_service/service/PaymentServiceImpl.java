package lk.ijse.payment_service.service;

import lk.ijse.payment_service.dto.PaymentDTO;
import lk.ijse.payment_service.entity.Payment;
import lk.ijse.payment_service.repo.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository repo;

    @Override
    public PaymentDTO processPayment(PaymentDTO dto) {
        Payment entity = dtoToEntity(dto);
        entity.setStatus("SUCCESS");
        entity.setTimestamp(LocalDateTime.now());

        Payment saved = repo.save(entity);
        return entityToDto(saved);
    }

    @Override
    public List<PaymentDTO> getAllPayments() {
        return repo.findAll().stream().map(this::entityToDto).collect(Collectors.toList());
    }

    @Override
    public PaymentDTO getPaymentById(Long id) {
        Payment payment = repo.findById(id).orElseThrow(() -> new RuntimeException("Payment not found"));
        return entityToDto(payment);
    }

    @Override
    public void deletePayment(Long id) {
        repo.deleteById(id);
    }

    private PaymentDTO entityToDto(Payment entity) {
        return PaymentDTO.builder()
                .id(entity.getId())
                .userId(entity.getUserId())
                .vehicleNumber(entity.getVehicleNumber())
                .amount(entity.getAmount())
                .method(entity.getMethod())
                .status(entity.getStatus())
                .timestamp(entity.getTimestamp())
                .build();
    }

    private Payment dtoToEntity(PaymentDTO dto) {
        return Payment.builder()
                .id(dto.getId())
                .userId(dto.getUserId())
                .vehicleNumber(dto.getVehicleNumber())
                .amount(dto.getAmount())
                .method(dto.getMethod())
                .status(dto.getStatus())
                .timestamp(dto.getTimestamp())
                .build();
    }
}

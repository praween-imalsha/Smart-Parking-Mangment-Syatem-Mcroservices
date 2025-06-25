package lk.ijse.payment_service.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentDTO {
    private int id;
    private String userId;
    private String vehicleNumber;
    private double amount;
    private String method;
    private String status;
    private LocalDateTime timestamp;
}

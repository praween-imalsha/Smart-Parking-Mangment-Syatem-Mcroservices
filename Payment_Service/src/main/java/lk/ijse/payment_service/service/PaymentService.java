package lk.ijse.payment_service.service;

import lk.ijse.payment_service.dto.PaymentDTO;

import java.util.List;

public interface PaymentService {
    PaymentDTO processPayment(PaymentDTO dto);
    List<PaymentDTO> getAllPayments();
    PaymentDTO getPaymentById(Long id);
    void deletePayment(Long id);
}

package com.mcq.webapp.controller;

import com.mcq.webapp.exception.ResourceNotFoundException;
import com.mcq.webapp.model.Note;
import com.mcq.webapp.model.Payment;
import com.mcq.webapp.repository.NoteRepository;
import com.mcq.webapp.repository.PaymentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
 
@RestController
@RequestMapping("/api")
public class PaymentController {

    @Autowired
    PaymentRepository paymentRepository;

    @GetMapping("/payment")
    public List<Payment> getAllPayment() {
        return paymentRepository.findAll();
    }

    @PostMapping("/payment")
    public Payment createPayment(@Valid @RequestBody Payment payment) {
        return paymentRepository.save(payment);
    }

    @GetMapping("/payment/{id}")
    public Payment getPaymentById(@PathVariable(value = "id") Long paymentId) {
        return paymentRepository.findById(paymentId)
                .orElseThrow(() -> new ResourceNotFoundException("Payment", "id", paymentId));
    }

    @PutMapping("/payment/{id}")
    public Payment updatePayment(@PathVariable(value = "id") Long paymentId,
                                           @Valid @RequestBody Payment paymentDetails) {

        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new ResourceNotFoundException("Payment", "id", paymentId));

        //payment.setAdmin(payment.getAdmin());
        payment.setAmount(payment.getAmount());
        payment.setStatus(payment.getStatus());
        payment.setDescription(payment.getDescription());

        Payment updatedPayment = paymentRepository.save(payment);
        return updatedPayment;
    }

    @DeleteMapping("/payment/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long paymentId) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new ResourceNotFoundException("Payment", "id", paymentId));

        paymentRepository.delete(payment);

        return ResponseEntity.ok().build();
    }
}

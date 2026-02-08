package in.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.ecommerce.dto.PaymentRequest;
import in.ecommerce.dto.PaymentResponse;
import in.ecommerce.service.PaymentService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {

	@Autowired
	private PaymentService service;

	@PreAuthorize("hasRole('USER')")
	@PostMapping
	public ResponseEntity<PaymentResponse> makePayment(@RequestBody PaymentRequest request) {
		return ResponseEntity.ok(service.processpayment(request));
	}
}

package in.ecommerce.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ecommerce.Enum.PaymentStatus;
import in.ecommerce.dto.PaymentRequest;
import in.ecommerce.dto.PaymentResponse;
import in.ecommerce.entity.Payment;
import in.ecommerce.repository.PaymentRepo;

@Service
public class PaymentService {

	@Autowired
	private PaymentRepo paymentrepo;

	public PaymentResponse processpayment(PaymentRequest request) {
		// Create a Payment object

		Payment payment = new Payment();
		payment.setOrderId(request.getOrderId());
		payment.setAmount(request.getAmount());
		payment.setPaymentMethod(request.getPaymentMethod());
		payment.setStatus(PaymentStatus.SUCCESS); // simulate success
		payment.setPaymentDate(LocalDateTime.now());

		// Save the payment to the repository
		paymentrepo.save(payment);

		// Create PaymentResponse object
		PaymentResponse paymentResponse = new PaymentResponse();
		paymentResponse.setPaymentId(payment.getId());
		paymentResponse.setOrderId(payment.getOrderId());
		paymentResponse.setStatus(payment.getStatus());
		paymentResponse.setMessage("Payment Successful");
		return paymentResponse;

	}

}

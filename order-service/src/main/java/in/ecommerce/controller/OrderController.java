package in.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.ecommerce.dto.OrderRequest;
import in.ecommerce.dto.OrderRespose;
import in.ecommerce.entity.Order;
import in.ecommerce.service.OrderService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

	@Autowired
	private OrderService service;

	@PostMapping
	public ResponseEntity<OrderRespose> placeOrder(@RequestBody OrderRequest request) {
		return ResponseEntity.ok(service.orderPlaced(request));
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<List<Order>> getOrdersByUser(@PathVariable Long userId) {

		return ResponseEntity.ok(service.getOrderByUser(userId));
	}

}

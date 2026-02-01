package in.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.ecommerce.dto.InventoryRequest;
import in.ecommerce.dto.InventoryResponse;
import in.ecommerce.service.InventoryService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/inventory")
class InventoryController {

	@Autowired
	private InventoryService service;

	@PostMapping("/add")
	public ResponseEntity<InventoryResponse> addInventory(@Valid @RequestBody InventoryRequest request) {
		InventoryResponse response = service.addInventory(request);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/{productId}")
	public ResponseEntity<InventoryResponse> getInventory(@PathVariable Long productId) {

		return ResponseEntity.ok(service.getInventoryByProductId(productId));
	}

	// INTERNAL (Order Service)
		
	@PutMapping("/reduce")
	public ResponseEntity<String> reduceStock(@RequestParam Long productId, @RequestParam Integer quantity) {

		service.reduceStock(productId, quantity);
		return ResponseEntity.ok("Stock reduced");
	}

}

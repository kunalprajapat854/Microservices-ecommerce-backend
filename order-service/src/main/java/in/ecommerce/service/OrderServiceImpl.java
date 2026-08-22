package in.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ecommerce.client.InventoryClient;
import in.ecommerce.dto.OrderRequest;
import in.ecommerce.dto.OrderResponse;
import in.ecommerce.entity.Order;
import in.ecommerce.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderrepo;

	@Autowired
	private InventoryClient client;

	// BUG-03 FIX: Removed unnecessary injection of OrderServiceApplication (main class) into a service bean.
	// The field was never used and caused an anti-pattern / constructor conflict with @RequiredArgsConstructor.

	@Override
	public OrderResponse orderPlaced(OrderRequest orderRequest) {
		// BUG-08 FIX: Merged check-stock + reduce-stock into a single Feign call (reduceStock).
		// The inventory service's reduceStock is @Transactional and throws if insufficient stock.
		// This eliminates the TOCTOU race: no more check-then-reduce split across two HTTP calls.
		client.reduceStock(orderRequest.getProductId(), orderRequest.getQuantity());

		Order order = new Order();
		order.setUserId(orderRequest.getUserId());
		order.setProductId(orderRequest.getProductId());
		order.setQuantity(orderRequest.getQuantity());
		order.setTotalPrice(orderRequest.getQuantity() * orderRequest.getPrice());
		order.setStatus("PLACED");

		Order savedOrder = orderrepo.save(order);
		return new OrderResponse(savedOrder.getId(), savedOrder.getStatus(), savedOrder.getTotalPrice());
	}

	@Override
	public List<Order> getOrderByUser(long userId) {
		return orderrepo.findByUserId(userId);
	}

}

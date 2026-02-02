package in.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import in.ecommerce.OrderServiceApplication;
import in.ecommerce.client.InventoryClient;
import in.ecommerce.dto.InventoryResponse;
import in.ecommerce.dto.OrderRequest;
import in.ecommerce.dto.OrderRespose;
import in.ecommerce.entity.Order;
import in.ecommerce.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {

	private final OrderServiceApplication orderServiceApplication;

	@Autowired
	private OrderRepository orderrepo;
	
	@Autowired
	private InventoryClient client;

	OrderServiceImpl(OrderServiceApplication orderServiceApplication) {
		this.orderServiceApplication = orderServiceApplication;
	}

	@Override
	public OrderRespose orderPlaced(OrderRequest orderRequest) {
		InventoryResponse inventory = client.getInventory(orderRequest.getProductId());
		if (inventory.getInStock() || inventory.getQuantity() < orderRequest.getQuantity()) {
			throw new RuntimeException("Product out of stock");
		}

		client.reduceStock(orderRequest.getProductId(), orderRequest.getQuantity());

		Order order = new Order();
		order.setUserId(orderRequest.getUserId());
		order.setProductId(orderRequest.getProductId());
		order.setQuantity(orderRequest.getQuantity());
		order.setTotalPrice(orderRequest.getQuantity() * orderRequest.getPrice());
		order.setStatus("PLACED");

		Order savedOrder = orderrepo.save(order);
		return new OrderRespose(savedOrder.getId(), savedOrder.getStatus(), savedOrder.getTotalPrice());
	}

	@Override
	public List<Order> getOrderByUser(long userId) {
		return orderrepo.findByUserId(userId);
	}

}

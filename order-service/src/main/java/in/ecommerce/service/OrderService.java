package in.ecommerce.service;

import java.util.List;

import in.ecommerce.dto.OrderRequest;
import in.ecommerce.dto.OrderResponse;
import in.ecommerce.entity.Order;

// BUG-23 FIX: Updated to use corrected class name OrderResponse
public interface OrderService {

	OrderResponse orderPlaced(OrderRequest orderRequest);

	List<Order> getOrderByUser(long userId);

}

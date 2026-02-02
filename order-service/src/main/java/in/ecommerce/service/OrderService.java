package in.ecommerce.service;

import java.util.List;

import in.ecommerce.dto.OrderRequest;
import in.ecommerce.dto.OrderRespose;
import in.ecommerce.entity.Order;

public interface OrderService {

	OrderRespose orderPlaced(OrderRequest orderRequest);

	List<Order> getOrderByUser(long userId);

}

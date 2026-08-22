package in.ecommerce.dto;

/**
 * @deprecated Use {@link OrderResponse} instead. This class existed due to a typo.
 */
@Deprecated
public class OrderRespose extends OrderResponse {
    public OrderRespose() { super(); }
    public OrderRespose(Long orderId, String status, Double totalPrice) {
        super(orderId, status, totalPrice);
    }
}

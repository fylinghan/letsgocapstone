package org.swyr.backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) representing a single item in an order.
 * <p>
 * This class is used to transfer order item data between the client and server.
 * Each {@code OrderItemDTO} contains the identifier of the product being ordered
 * and the quantity requested.
 * </p>
 *
 * An {@link OrderItemDTO} is typically included within an {@link OrderDTO}
 * to represent the full list of items in an order.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDTO {

    /**
     * The unique identifier of the product associated with this order item.
     */
    private Long productId;

    /**
     * The quantity of the product being ordered.
     */
    private int quantity;
}
package org.swyr.backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Data Transfer Object (DTO) representing an order.
 * <p>
 * This class is used to transfer order-related data between the client and server.
 * It contains information about the shipping address, the user's email, and
 * the list of ordered items.
 * </p>
 *
 * An {@link OrderDTO} is typically used when submitting a new order request
 * or when retrieving order details for processing.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

        /**
         * The shipping address where the order should be delivered.
         */
        private String shippingAddress;

        /**
         * The email address of the user who placed the order.
         */
        private String email;

        /**
         * The list of items included in the order.
         * Each item is represented by an {@link OrderItemDTO}.
         */
        private List<OrderItemDTO> orderedItems;
}
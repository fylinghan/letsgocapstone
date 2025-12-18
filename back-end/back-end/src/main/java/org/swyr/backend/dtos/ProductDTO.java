package org.swyr.backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Data Transfer Object (DTO) representing a product.
 * <p>
 * This class is used to transfer product-related data between the client and server.
 * It contains information such as the product's price, name, series, image path,
 * and the email of the user who listed or owns the product.
 * </p>
 *
 * An {@link ProductDTO} is typically used when creating, updating, or retrieving
 * product information in the application.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    /**
     * The price of the product.
     */
    private BigDecimal price;

    /**
     * The name of the product.
     */
    private String productName;

    /**
     * The name of the series to which the product belongs.
     */
    private String seriesName;

    /**
     * The relative or absolute path to the product's image.
     */
    private String imgPath;

    /**
     * The email address of the user who listed or owns the product.
     */
    private String userEmail;
}
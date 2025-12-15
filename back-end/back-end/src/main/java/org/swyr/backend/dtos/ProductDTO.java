package org.swyr.backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private BigDecimal price;
    private String productName;
    private String seriesName;
    private String imgPath;
    private String userEmail;

}

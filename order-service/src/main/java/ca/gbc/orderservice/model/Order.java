package ca.gbc.orderservice.model;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name="t_orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true) // Order numbers are typically unique and required
    private String orderNumber;

    @Column(nullable = false)
    private String skuCode;

    @Column(nullable = false, precision = 10, scale = 2) // Precision for currency handling
    private BigDecimal price;

    @Column(nullable = false)
    private Integer quantity;
}
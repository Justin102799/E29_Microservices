package com.example.Cart.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "cart")
public class Cart {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;
    private Long productId;
    private Long userId;
    private int quantity;
}

package com.example.Cart.Controller;

import com.example.Cart.DTO.ProductDTO;
import com.example.Cart.DTO.ResponseDTO;
import com.example.Cart.Entity.Cart;
import com.example.Cart.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CartController {
    @Autowired
    private CartService cartService;
    @PatchMapping("/cart/{userId}/{productId}")
    public ResponseEntity<Cart> addToCart(@PathVariable Long userId, @PathVariable Long productId){
        Cart saveCart = cartService.addToCart(userId, productId);
        return new ResponseEntity<>(saveCart, HttpStatus.CREATED);
    }
    @DeleteMapping("/cartRemove/{userId}")
    public ResponseEntity<Cart> removedProductsCart(@PathVariable Long userId, @RequestBody Cart cartDetail){
        Cart saveCart = cartService.removedProductsCart(userId, cartDetail);
        return new ResponseEntity<>(saveCart, HttpStatus.CREATED);
    }
//    public Cart addToCart(@RequestBody Cart cart){
//        return cartService.addToCart(cart);
//    }
    @GetMapping("/carts/{userId}")
    public ResponseEntity <String> getProductById(@PathVariable("userId") Long userId){
//        Cart cart = cartService.getCartById(cartId);
        return ResponseEntity.ok(cartService.listCart(userId));
    }
//    public Optional<Cart> getCartById(@PathVariable int id){
//        return cartService.findById(id);
//    }
    @PutMapping("/update/{id}")
    public Cart updateCart(@PathVariable Long id, @RequestBody Cart cart){
        cart.setCartId(id);
        return cartService.updateCart(id, cart);
    }



}

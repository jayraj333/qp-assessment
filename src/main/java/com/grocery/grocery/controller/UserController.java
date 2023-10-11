package com.grocery.grocery.controller;


import com.grocery.grocery.entity.GroceryItem;
import com.grocery.grocery.entity.OrderItem;
import com.grocery.grocery.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/availableItems")
    public List<GroceryItem> getAllAvailableItems() {
        return userService.getAllAvailableItems();
    }

    @PostMapping("/order/{itemId}/{quantity}")
    public OrderItem orderGroceryItem(
            @PathVariable Long itemId,
            @PathVariable int quantity
    ) {
        return userService.orderGroceryItem(itemId, quantity);
    }
}

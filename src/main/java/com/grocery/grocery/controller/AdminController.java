package com.grocery.grocery.controller;

import com.grocery.grocery.entity.GroceryItem;
import com.grocery.grocery.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PostMapping("/add")
    public GroceryItem addGroceryItem(@RequestBody GroceryItem item) {
        return adminService.addGroceryItem(item);
    }

    @GetMapping("/items")
    public List<GroceryItem> getAllGroceryItems() {
        return adminService.getAllGroceryItems();
    }

    @DeleteMapping("/remove/{itemId}")
    public void removeGroceryItem(@PathVariable Long itemId) {
        adminService.removeGroceryItem(itemId);
    }

    @PutMapping("/update")
    public GroceryItem updateGroceryItem(@RequestBody GroceryItem updatedItem) {
        return adminService.updateGroceryItem(updatedItem);
    }

    @PutMapping("/updateInventory/{itemId}/{newStock}")
    public GroceryItem updateInventory(
            @PathVariable Long itemId,
            @PathVariable int newStock
    ) {
        return adminService.updateInventory(itemId, newStock);
    }
}

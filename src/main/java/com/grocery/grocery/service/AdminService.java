package com.grocery.grocery.service;

import com.grocery.grocery.entity.GroceryItem;
import com.grocery.grocery.repository.GroceryItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.grocery.grocery.customException.*;
import java.util.List;

@Service
public class AdminService {
    @Autowired
    private GroceryItemRepository groceryItemRepository;

    public GroceryItem addGroceryItem(GroceryItem item) {
        return groceryItemRepository.save(item);
    }

    public List<GroceryItem> getAllGroceryItems() {
        return groceryItemRepository.findAll();
    }

    public void removeGroceryItem(Long itemId) {
        groceryItemRepository.deleteById(itemId);
    }

    public GroceryItem updateGroceryItem(GroceryItem updatedItem) {
        return groceryItemRepository.save(updatedItem);
    }

    public GroceryItem updateInventory(Long itemId, int newStock) {
        GroceryItem item = groceryItemRepository.findById(itemId)
                .orElseThrow(() -> new NotFoundException("Grocery item not found"));

        item.setStock(newStock);
        return groceryItemRepository.save(item);
    }
}

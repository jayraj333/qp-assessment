package com.grocery.grocery.service;

import com.grocery.grocery.entity.GroceryItem;
import com.grocery.grocery.entity.OrderItem;
import com.grocery.grocery.repository.GroceryItemRepository;
import com.grocery.grocery.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.grocery.grocery.customException.*;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private GroceryItemRepository groceryItemRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;

    public List<GroceryItem> getAllAvailableItems() {
        return groceryItemRepository.findAll();
    }

    public OrderItem orderGroceryItem(Long itemId, int quantity) {
        GroceryItem item = groceryItemRepository.findById(itemId)
                .orElseThrow(() -> new NotFoundException("Grocery item not found"));
        if (item.getStock() >= quantity) {

            item.setStock(item.getStock() - quantity);
            groceryItemRepository.save(item);

            OrderItem orderItem = new OrderItem();
            orderItem.setGroceryItem(item);
            orderItem.setQuantity(quantity);
            orderItem.setTotalPrice(item.getPrice() * quantity);

            return orderItemRepository.save(orderItem);
        } else {
            throw new InsufficientStockException("Insufficient stock for the item");
        }
    }
}
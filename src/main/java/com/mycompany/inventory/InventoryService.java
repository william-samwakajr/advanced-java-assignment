
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/StatelessEjbClass.java to edit this template
 */
package com.mycompany.inventory;

import com.mycompany.inventory.Product;
import com.mycompany.inventory.InventoryServiceLocal;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import java.util.Map;
import java.util.HashMap;



/**
 *
 * @author william
 */
@Singleton
public class InventoryService implements InventoryServiceLocal {
    
    private final Map<Product, Integer> inventory = new HashMap<>();
    
    @PostConstruct
    public void initData(){
        inventory.put(new Product("mince",5), 1);
        inventory.put(new Product("pencils",100), 2);
        inventory.put(new Product("books",20), 3);
    }
    @Override
    public Map<Product, Integer> getProducts() {
      return this.inventory;
    }
    
    @Override
    public boolean reserveProduct(int itemNumber, int requestedQty){
        
          for (Map.Entry<Product, Integer> entry : inventory.entrySet()) {
            if (entry.getValue() == itemNumber) {
                Product product = entry.getKey();
                
                // Check if we have enough items left on the shelf
                if (product.getQuantity() >= requestedQty) {
                    // Decrement the quantity inside the custom product object directly
                    int newQty = product.getQuantity() - requestedQty;
                    product.setQuantity(newQty);
                    return true; // Reservation confirmed successfully
                }
                break;
            }
        }
        return false;
    }
}

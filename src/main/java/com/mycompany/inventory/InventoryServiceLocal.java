/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/SessionLocal.java to edit this template
 */
package com.mycompany.inventory;

import jakarta.ejb.Local;
import java.util.Map;
import java.util.HashMap;
import com.mycompany.inventory.Product;


/**
 *
 * @author william
 */
@Local
public interface InventoryServiceLocal {
        
    public Map<Product,Integer> getProducts();
    
    public boolean reserveProduct(int itemNumber, int requestedQty);

}

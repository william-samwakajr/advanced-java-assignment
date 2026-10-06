/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inventory;

import java.util.Objects;

/**
 *
 * @author william
 */
public class Product {
    public String name;
    public int quantity;
    
    public Product(String name , int quantity){
        this.name = name;
        this.quantity = quantity;
    }
    public String getName(){
        return this.name;
    }
    public int getQuantity() {
        return quantity; 
    }
    public void setQuantity(int quantity) { 
        this.quantity = quantity; 
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Product other = (Product) obj;
        return Objects.equals(this.name, other.name);
    }
    
}

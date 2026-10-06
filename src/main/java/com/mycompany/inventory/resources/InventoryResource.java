/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inventory.resources;

/**
 *
 * @author william
 */
import com.mycompany.inventory.InventoryServiceLocal;
import com.mycompany.inventory.Product;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.QueryParam;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Path("/inventory")
public class InventoryResource {

    @Inject // Injects the Singleton EJB interface
    private InventoryServiceLocal inventoryService;

    // A small helping class to shape the JSON formatting nicely for JavaScript
    public static class FlatItem {
        public int itemNumber;
        public String name;
        public int quantity;

        public FlatItem(int itemNumber, String name, int quantity) {
            this.itemNumber = itemNumber;
            this.name = name;
            this.quantity = quantity;
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON) // GlassFish will automatically turn this list into JSON text
    public List<FlatItem> getCatalog() {
        List<FlatItem> listForWeb = new ArrayList<>();
        Map<Product, Integer> rawMap = inventoryService.getProducts();

        // Extract values from the map keys and values to create flat items
        for (Map.Entry<Product, Integer> entry : rawMap.entrySet()) {
            Product prod = entry.getKey();
            Integer itemNum = entry.getValue();
            listForWeb.add(new FlatItem(itemNum, prod.getName(), prod.getQuantity()));
        }
        
        return listForWeb;
    }
    
    @POST
    @Path("/reserve") // Final URL: /api/catalog/reserve
    @Produces(MediaType.APPLICATION_JSON)
    
    public Response reserveItem(@QueryParam("id") int itemNumber, @QueryParam("qty") int requestedQuantity) {
        // Attempt to reserve exactly 1 unit of the product
        boolean success = inventoryService.reserveProduct(itemNumber, requestedQuantity);
        
        if (success) {
            return Response.ok("{\"message\": \"Reservation Successful!\"}").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                           .entity("{\"message\": \"Reservation Failed: Insufficient stock available.\"}").build();
            }
        }
    }
    




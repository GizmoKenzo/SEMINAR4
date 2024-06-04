package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a sale containing items and calculates totals.
 */
public class Sale {
    private List<ItemDTO> items = new ArrayList<>();
    private ItemDTO lastScannedItem;

    /**
     * Adds an item to the sale and updates the total price.
     *
     * @param itemToAdd The item to be added to the sale.
     * @return The added item.
     */
    public ItemDTO addItem(ItemDTO itemToAdd) {
        items.add(itemToAdd);
        lastScannedItem = itemToAdd;  
        return itemToAdd;
    }

    /**
     * Gets the last scanned item in the sale.
     *
     * @return The last scanned item.
     */
    public ItemDTO getLastScannedItem() {
        return lastScannedItem;
    }

     /**
     * Calculates and returns the total price of all items in the sale including VAT.
     *
     * @return The total price including VAT.
     */
    public double calculateTotalPrice() {
        double totalIncludingVat = 0.0;
        for (ItemDTO item : items) {
        totalIncludingVat += item.getPrice().getAmount() * item.getQuantity();
        }
            return totalIncludingVat;
    }

    /**
     * Calculates and returns the total VAT of all items in the sale.
     *
     * @return The total VAT.
     */
    public double calculateTotalVAT() {
        double totalVAT = 0.0;
        for (ItemDTO item : items) {
            double totalPriceWithoutVat = item.getPrice().getAmount() / (1 + (0.01 * item.getVatRate()));
            double vatAmount = (item.getPrice().getAmount() - totalPriceWithoutVat) * item.getQuantity();
            totalVAT += vatAmount;
        }
        return totalVAT;
    }
    
    /**
     * Displays the price including VAT,
     * and VAT amount for the last scanned item.
     */
    public void displayItemPricesWithVat() {
        ItemDTO item = lastScannedItem;
            double priceWithoutVat = item.getPrice().getAmount() / (1 + (0.01 * item.getVatRate()));
            double vatAmount = (item.getPrice().getAmount() - priceWithoutVat) * item.getQuantity();
            
            System.out.println("Total Price (including VAT): " + String.format("%.2f",
                                item.getPrice().getAmount() * item.getQuantity()) +
                               ", VAT Amount: " + String.format("%.2f", vatAmount) + " SEK");
        
    }
    
    /**
     * Just to get a copy of a items list.
     *
     * @return A list of items in the sale.
     */
    public List<ItemDTO> getItems() {
        return new ArrayList<>(items); 
    }

    /**
     * Increases the quantity of an item in the sale if it already exists.
     *
     * @param item The item whose quantity is to be increased.
     * @return The new total quantity of the item or 0 if the item was not found.
     */
    public int increaseItemQuantity(ItemDTO item) {
        for (ItemDTO itemInSale : items) {
            if (itemInSale.getId() == item.getId()) {
                itemInSale.setQuantity(itemInSale.getQuantity() + item.getQuantity());
                
                return itemInSale.getQuantity();
            }
        }
        return 0;
    }
}

package integration;

import java.util.ArrayList;
import java.util.List;
import model.ItemDTO;
import model.Sale;
import model.TotalRevenueObserver;

/**
 * Handles the logging and processing of ongoing sales including
 * notifying the accounting system and notyfing inventory to update itself.
 */
public class SaleLog {
    private Accounting accounting; 
    private InventoryRegistry inventoryRegistry;
    private List<TotalRevenueObserver> totalRevenueObservers = new ArrayList<>();
    private double totalRevenue = 0;

    /**
     * Creates a new instance.
     *
     * @param inventoryRegistry The inventory registry to be notified and updated.
     * @param accounting The accounting system to be notified.
     */
    public SaleLog(InventoryRegistry inventoryRegistry, Accounting accounting) {
        this.inventoryRegistry = inventoryRegistry;
        this.accounting = accounting;
    }

    /**
     * Processes a completed sale by recording the transaction,
     * notifying the accounting system and notyfing inventory to be updated.
     * It also updates the total revenue and notifies observer. 
     * 
     * @param sale The sale to be processed.
     */
    public void processSale(Sale sale) {
        
        recordTransaction(sale);
        notifyAccounting(sale);
        notifyInventoryRegistry(sale);
        updateTotalRevenue(sale.calculateTotalPrice());
    	notifyObserver();

    }

    private void recordTransaction(Sale sale) {
        System.out.println("Transaction recorded for sale of total price: " + sale.calculateTotalPrice() + " SEK");
        System.out.println("");
    }

    private void notifyAccounting(Sale sale) {
        accounting.recordSaleTransaction(sale);
    }

    private void notifyInventoryRegistry(Sale sale) {
        for (ItemDTO item : sale.getItems()) {
            inventoryRegistry.updateInventorySystem(item);
        }
    }

    private void updateTotalRevenue(double saleTotalRevenue) {
        totalRevenue += saleTotalRevenue;
    }

    private void notifyObserver(){
		for (TotalRevenueObserver obs : totalRevenueObservers) {
            obs.updateRevenue(totalRevenue);
        }
	}

    /**
     * Adds an observer to a list of observers.
     *
     * @param obs The observer to add.
     */
    public void addTotalRevenueObserver(TotalRevenueObserver obs) {
        totalRevenueObservers.add(obs);
    }
}


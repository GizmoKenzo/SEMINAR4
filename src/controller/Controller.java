package controller;

import integration.*;
import model.*;


/**
 * This is the application’s only controller class. All calls to the model pass through here.
 */
public class Controller {
    private Sale sale;
    private InventoryRegistry inventoryRegistry;
    private Accounting accounting;
    private SaleLog saleLog;
    private Printer printer;
    private CashRegister cashRegister;
    
    /**
     * Creates a new instance.
     *
     * @param printer Interface to printer.
     */
    public Controller(Printer printer) {
        this.inventoryRegistry = new InventoryRegistry();
        this.accounting = new Accounting();
        this.saleLog = new SaleLog(inventoryRegistry, accounting);
        this.printer = printer;
        this.cashRegister = new CashRegister();
    }

    /**
     * Initiates the process of a new sale.
     */
    public void startingSale() {
        this.sale = new Sale();
    }

    /**
     * Scans an item, retrieves information of items from the inventory and records it in the sale.
     *
     * @param item The item being purchased by the customer.
     * @return The scanned items information from the inventory or null if the item is not found.
     * @throws ItemNotValidException 
     * @throws DatabaseFailureException 
     */
    public ItemDTO scanItem(ItemDTO item) throws ItemNotValidException, DatabaseFailureException {
       
        try {

            if ("DatabaseItem".equalsIgnoreCase(item.getName())) {
                throw new DatabaseFailureException("SYSTEM FAILURE ");
            }

            ItemDTO scannedItem = inventoryRegistry.getItemInfo(item.getName(), item.getQuantity());
            if (scannedItem != null) {
                scannedItem.setPrice(item.getPrice());
                sale.addItem(scannedItem);
                return scannedItem;
            } 
            else {
                throw new ItemNotValidException("Item not found in inventory: " + item.getName());
            }
        } catch (ItemNotValidException | DatabaseFailureException exception) {
           
            throw exception;
        }
    }
    
    /**
     * Calculates and returns the total price and total VAT of all scanned items.
     *
     * @return A string showing the total price including total VAT, 
     * and also total VAT amount alone.
     */
    public String allItemsAreScanned() {
        double totalPrice = sale.calculateTotalPrice();
        double totalVAT = sale.calculateTotalVAT();
        return String.format("Total Price (including VAT): %.2f SEK \nTotal VAT: %.2f SEK", totalPrice, totalVAT);
    }    

    /**
     * Displays current VAT information for every item in the sale.
     */
    public void showVatForEveryItem(){
        sale.displayItemPricesWithVat(); 
    }

    /**
     * Increases the quantity of a sold item if it already exists in the sale.
     *
     * @param item The item to increase the quantity of.
     * @return The new total quantity of the item in the sale.
     */
    public int increaseAmountSoldItem(ItemDTO item) {
        return sale.increaseItemQuantity(item);
    }

    /**
     * Ends the ongoing sale.
     */
    public void endSale() {
        System.out.println("Ending sale:");
    }

    /**
     * Initiates the payment process, calculating change and recording the transaction.
     *
     * @param amount The amount of money received from the customer.
     */
    public void initiatePayment(AmountOfMoney amount) {
        double totalAmount = sale.calculateTotalPrice();
        CashPayment payment = new CashPayment(amount.getAmount());
        cashRegister.addPayment(payment);

        double change = payment.getAmountReceived().subtract(new AmountOfMoney(totalAmount)).getAmount();
        System.out.println("Payment of " + amount + " received. Change: " + change + " SEK");
    }

    /**
     * Completes the sale, processes the transaction, generates and prints the receipt.
     */
    public void completeSale() {
        Receipt receipt = new Receipt(sale);
        printer.printReceipt(receipt);
        saleLog.processSale(sale);

        System.out.println("");
        System.out.println("Sale completed and processed.");
    }

    /**
     * Registers an observer to class saleLog.
     *
     * @param obs The observer to register.
     */
    public void registerTotalRevenueObserver(TotalRevenueObserver obs) {
        saleLog.addTotalRevenueObserver(obs);
    }

}


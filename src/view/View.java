package view;

import controller.Controller;
import model.*;
import controller.DatabaseFailureException;
import controller.ItemNotValidException;
import util.LogHandler;

/**
 * This program has no view, instead
 * this class is a placeholder for the entire view.
 */
public class View {
    private Controller contr;
    private LogHandler log = LogHandler.getLogger();

    /**
     * Creates a new instance. 
     *
     * @param contr The controller that is used for all operations.
     */
    public View(Controller contr)
    {
        this.contr = contr;
        contr.registerTotalRevenueObserver(new TotalRevenueView());
        contr.registerTotalRevenueObserver(new TotalRevenueFileOutput());
    }

    /**
     * Simulates user interactions that trigger all
     * system operations.
     * @throws ItemNotValidException 
     * @throws DatabaseFailureException 
     */
    public void runSale() throws ItemNotValidException, DatabaseFailureException {
    	System.out.println("Sale have now started:");
        System.out.println("");
        contr.startingSale();

        try {
            int itemID = 345031;
            ItemDTO item1 = new ItemDTO(itemID, "TOMATO", "Fresh organic tomatoes", 1, new AmountOfMoney(15.00), 5.0);
            ItemDTO scannedItem = contr.scanItem(item1);
            System.out.println("Scanning item: \n" + scannedItem);
            contr.showVatForEveryItem();
            System.out.println("");

            ItemDTO sameItem = new ItemDTO(itemID, "ItemName", "Description", 1, new AmountOfMoney(0.00), 0.0);
            System.out.println("Increase the same item by one unit using itemID: " + itemID);
            contr.increaseAmountSoldItem(sameItem);
            System.out.println(scannedItem);
            contr.showVatForEveryItem();
            System.out.println("");

            /*// UNCOMMENT THIS AFTER UNCOMMENTING ItemNotValidException TO THROW DatabaseFailureException.
            ItemDTO databaseItem = new ItemDTO(000000, "DatabaseItem", "This item should cause a database fail", 1, new AmountOfMoney(0.00), 0.0);
            ItemDTO scannedDatabaseItem = contr.scanItem(databaseItem);
            System.out.println("Scanning item: \n" + scannedDatabaseItem);
            */

            /*//UNCOMMENT THIS FIRST TO THROW ItemNotValidException.
            // Apple does not exist in inventory and will throw ItemNotValidException.
            ItemDTO anotherItem = new ItemDTO(345032, "APPLE", "Item does not exist", 1, new AmountOfMoney(29.90), 6.0);
            ItemDTO scannedAnotherItem = contr.scanItem(anotherItem);
            System.out.println("Scanning item: \n" + scannedAnotherItem);
            contr.showVatForEveryItem();
            System.out.println("");
            */

            contr.endSale();
            String totalPriceDetails = contr.allItemsAreScanned();
            System.out.println(totalPriceDetails);
            System.out.println("\nThe customer gives 100 SEK as payment.\n");
            contr.initiatePayment(new AmountOfMoney(100));
            contr.completeSale();
            
        } catch (ItemNotValidException exception) {
            System.out.println(exception.getMessage());
            log.logException(exception); 
            
        } catch (DatabaseFailureException exception) {
            System.out.println(exception.getMessage());
            log.logException(exception);
            
        }
    }
}
package integration;

import model.Sale;

/**
 * Handles communication with the external accounting system.
 */
public class Accounting {
    
    /**
     * Records a sale transaction in the external accounting system.
     *
     * @param sale The sale to be recorded.
     */
    public void recordSaleTransaction(Sale sale) {
        System.out.println("Sent sale info to external accounting system.");
    }
}

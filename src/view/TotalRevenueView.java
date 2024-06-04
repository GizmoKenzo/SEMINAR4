package view;

import model.TotalRevenueObserver;

/**
 * Implements the TotalRevenueObserver interface,
 * and displays the total revenue to the console when it is updated.
 */
public class TotalRevenueView implements TotalRevenueObserver {

    /**
     * Updates the display with total revenue.
     *
     * @param totalRevenue The total revenue to be displayed.
     */
    @Override
    public void updateRevenue(double totalRevenue) {
        System.out.println("Total Revenue: " + totalRevenue + " SEK");
    }

}

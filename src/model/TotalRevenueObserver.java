package model;

/**
* An interface created for receiving notifications about observed sales.
*/
public interface TotalRevenueObserver {

    /**
     * Invoked when the total revenue is updated.
     *
     * @param totalRevenue The updated total revenue.
     */
    void updateRevenue(double totalRevenue);
    
}

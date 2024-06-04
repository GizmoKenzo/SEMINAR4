package view;

import model.TotalRevenueObserver;
import util.LogHandler;

/**
 * Implements the TotalRevenueObserver interface,
 * and logs the total revenue to a file when it is updated.
 */
public class TotalRevenueFileOutput implements TotalRevenueObserver {
    private LogHandler logger;

    /**
    * Creates a new instance and initializes the logger to be used
    * for logging updates to a file.
    */
    public TotalRevenueFileOutput() {
        this.logger = LogHandler.getLogger();
    }

    /**
     * Updates the logfile with total revenue.
     *
     * @param totalRevenue The total revenue to be logged.
     */
    @Override
    public void updateRevenue(double totalRevenue) {
        logger.logMessage("total revenue is updated: " + totalRevenue);
    }
}

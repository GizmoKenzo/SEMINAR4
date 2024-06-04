package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The receipt of a sale
 */
public class Receipt
{
    private Sale sale;
    
    /**
     * Creates a new instance.
     *
     * @param sale The sale proved by this receipt.
     */
    public Receipt(Sale sale)
    {
        this.sale = sale;
    }

    /**
     * Creates a formated string with the entire content of the receipt.
     *
     * @return The formatted receipt with details of sale.
     */
    public String generateReceipt() {
        StringBuilder receipt = new StringBuilder();
        receipt.append("-".repeat(60) + "\n");
        receipt.append("-".repeat(60) + "\n");
        receipt.append("Beginning of receipt\n");
        receipt.append("-".repeat(60) + "\n");

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime timeOfSale = LocalDateTime.now();
        receipt.append("Time of Sale: ").append(timeOfSale.format(timeFormatter)).append("\n\n");

        for (ItemDTO item : sale.getItems()) {
            receipt.append(item.getName())
            .append(", ")
            .append(item.getQuantity()).append(" x ")
            .append(String.format("%.2f", item.getPrice().getAmount())).append(", ")
            .append(String.format("%.2f", item.getPrice().getAmount() * item.getQuantity()))
            .append(" SEK\n");
        }

        receipt.append("-".repeat(60) + "\n");
        return receipt.toString();
    }
}

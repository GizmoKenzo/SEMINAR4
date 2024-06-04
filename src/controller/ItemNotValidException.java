package controller;

/**
 * Thrown when a specified item was not found in the inventory.
 */
public class ItemNotValidException extends Exception{

    public ItemNotValidException(String message){
        super(message);
    }
}

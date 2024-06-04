package startup;

import controller.Controller;
import controller.DatabaseFailureException;
import controller.ItemNotValidException;
import view.View;
import integration.Printer;

    /**
     * This is the main method that initates the entire application.
     */
public class Main {

    /**
     * Starts the application.
     *
     * @param args The application does not take any command line parameters.
     * @throws DatabaseFailureException 
     * @throws ItemNotValidException 
     */
        public static void main(String[] args) throws ItemNotValidException, DatabaseFailureException
        {
            Printer printer = new Printer();
            Controller contr = new Controller(printer); 
            View view = new View(contr);
            view.runSale();
        }
    
    
}

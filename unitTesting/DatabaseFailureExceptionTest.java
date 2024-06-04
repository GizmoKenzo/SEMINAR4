import controller.*;
import model.*;
import integration.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseFailureExceptionTest {
    private Controller contr;

    @BeforeEach
    public void setUp() {
        Printer printer = new Printer();
        contr = new Controller(printer);
        contr.startingSale();
    }

    @AfterEach
    public void tearDown() {
        contr = null;
    }

    /**
     * Test that the message is the correct one 
     * 
     * @throws DatabaseFailureException
     * @throws ItemNotValidException
     */
    @Test
    public void testDatabaseFailureException() throws DatabaseFailureException, ItemNotValidException{
    
        ItemDTO databaseFailureItem = new ItemDTO(0000, "DatabaseItem", "This item causes a database failure", 1, new AmountOfMoney(0.00), 0.0);

        try {
            contr.scanItem(databaseFailureItem);
    
        } catch (DatabaseFailureException exception) {
            assertEquals("SYSTEM FAILURE ", exception.getMessage());
        } 
    }
}

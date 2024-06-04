import controller.*;
import model.*;
import integration.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemNotValidExceptionTest {
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
     * Testing that the correct message is given when an item is not found.
     * 
     * @throws DatabaseFailureException
     * @throws ItemNotValidException 
     */
    @Test
    public void testItemNotValidException() throws ItemNotValidException, DatabaseFailureException {
        String itemName = "ItemThatDoesNotExist";
        ItemDTO itemThatDoesNotExist = new ItemDTO(0000, itemName, "This item does not exist in inventory", 1, new AmountOfMoney(0.00), 0.0);
    
        try {
            contr.scanItem(itemThatDoesNotExist);
            
        } catch(ItemNotValidException exception) {
            assertEquals("Item not found in inventory: " + itemName, exception.getMessage());
        }
    }
}


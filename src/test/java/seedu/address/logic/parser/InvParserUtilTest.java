package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.item.Quantity;

public class InvParserUtilTest {

    @Test
    public void parseIndex_success() {
        try {
            Index index = InvParserUtil.parseIndex(" 12 ");
            assertEquals(12, index.getOneBased());
        } catch (ParseException e) {
            fail();
        }
    }

    @Test
    public void parseIndex_failure() {
        assertThrows(ParseException.class, () -> InvParserUtil.parseIndex("x"));
    }

    @Test
    public void parseName() {
        assertEquals("test", InvParserUtil.parseName(" test "));
    }

    @Test
    public void parseQuantity_success() {
        try {
            Quantity qty = InvParserUtil.parseQuantity(" 123 ");
            assertEquals("123", qty.value);
        } catch (ParseException e) {
            fail();
        }
    }

    @Test
    public void parseQuantity_failure() {
        assertThrows(ParseException.class, () -> InvParserUtil.parseQuantity("10.2"));
    }

    @Test
    public void parseDescription() {
        assertEquals("test", InvParserUtil.parseDescription(" test "));
    }

    @Test
    public void parseLocation() {
        try {
            assertEquals(1, InvParserUtil.parseLocation("school"));
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    public void parseLocations() {
        try {
            ArrayList<String> arr = new ArrayList<>();
            arr.add("test");
            assertTrue(InvParserUtil.parseLocations(arr).contains(1));
        } catch (IOException e) {
            fail();
        }
    }
}

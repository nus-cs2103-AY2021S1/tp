package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.item.Quantity;
import seedu.address.model.recipe.ProductQuantity;

public class RecipeParserUtilTest {
    private static final String INVALID_PRODUCT_QUANTITY = "-1";

    private static final String VALID_PRODUCT_NAME = "Apple Pie";
    private static final String VALID_PRODUCT_QUANTITY = "1";
    private static final String VALID_DESCRIPTION = "Red and cute.";

    private static final String WHITESPACE = " \t\r\n";
    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> RecipeParserUtil.parseProductName((String) null));
    }

    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() {
        assertEquals(VALID_PRODUCT_NAME, RecipeParserUtil.parseProductName(VALID_PRODUCT_NAME));
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() {
        String nameWithWhitespace = WHITESPACE + VALID_PRODUCT_NAME + WHITESPACE;
        assertEquals(VALID_PRODUCT_NAME, RecipeParserUtil.parseProductName(nameWithWhitespace));
    }

    @Test
    public void parseQuantity_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> RecipeParserUtil.parseProductQuantity((String) null));
    }

    @Test
    public void parseQuantity_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> RecipeParserUtil.parseProductQuantity(INVALID_PRODUCT_QUANTITY));
    }

    @Test
    public void parseQuantity_validValueWithoutWhitespace_returnsQuantity() throws Exception {
        Quantity expectedQuantity = new Quantity(VALID_PRODUCT_QUANTITY);
        assertEquals(expectedQuantity, RecipeParserUtil.parseProductQuantity(VALID_PRODUCT_QUANTITY));
    }

    @Test
    public void parseQuantity_validValueWithWhitespace_returnsTrimmedQuantity() throws Exception {
        String quantityWithWhitespace = WHITESPACE + VALID_PRODUCT_QUANTITY + WHITESPACE;
        ProductQuantity expectedQuantity = new ProductQuantity(VALID_PRODUCT_QUANTITY);
        assertEquals(expectedQuantity, RecipeParserUtil.parseProductQuantity(quantityWithWhitespace));
    }

    @Test
    public void parseDescription_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> RecipeParserUtil.parseDescription((String) null));
    }

    @Test
    public void parseDescription_validValueWithoutWhitespace_returnsDescription() {
        assertEquals(VALID_DESCRIPTION, RecipeParserUtil.parseDescription(VALID_DESCRIPTION));
    }

    @Test
    public void parseDescription_validValueWithWhitespace_returnsTrimmedDescription() {
        String descriptionWithWhitespace = WHITESPACE + VALID_DESCRIPTION + WHITESPACE;
        assertEquals(VALID_DESCRIPTION, RecipeParserUtil.parseDescription(descriptionWithWhitespace));
    }
}

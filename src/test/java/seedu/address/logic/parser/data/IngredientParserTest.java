package seedu.address.logic.parser.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIngredients.POTATO1;
import static seedu.address.testutil.TypicalIngredients.VALID_INGREDIENT2;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ingredient.Ingredient;
import seedu.address.testutil.IngredientBuilder;

public class IngredientParserTest {
    private static final String VALID_INGREDIENT_STRING = "Potato -2 whole, Banana -200g";
    private static final String VALID_INGREDIENT_STRING_WITHOUTQUANTITY = "food";
    private static final String INVALID_INGREDIENT_NO_SPACE_BEFORE_HYPHEN = "banana-200g";

    @Test
    public void parser_validInputWithQuantity_success() throws ParseException {
        ArrayList<Ingredient> expectedIngredients = new ArrayList<>();
        expectedIngredients.add(POTATO1);
        expectedIngredients.add(VALID_INGREDIENT2);
        assertEquals(expectedIngredients, IngredientParser.parse(VALID_INGREDIENT_STRING));
    }

    @Test
    public void parser_validInputWithoutQuantity_success() throws ParseException {
        ArrayList<Ingredient> expectedIngredients = new ArrayList<>();
        expectedIngredients.add(new IngredientBuilder().buildWithoutQuantity());
        assertEquals(expectedIngredients, IngredientParser.parse(VALID_INGREDIENT_STRING_WITHOUTQUANTITY));
    }

    @Test
    public void parse_noSpaceBeforeHyphen_throwsParseException() {
        assertThrows(ParseException.class, Ingredient.HYPHEN_CONSTRAINTS, ()
            -> IngredientParser.getIngredientQuantity(INVALID_INGREDIENT_NO_SPACE_BEFORE_HYPHEN));
    }
}

package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.recipe.Ingredient;
import seedu.address.testutil.IngredientBuilder;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalIngredients.validIngredient1;
import static seedu.address.testutil.TypicalIngredients.validIngredient2;



public class IngredientParserTest {
    private static final String VALID_INGREDIENT_STRING = "apple -2 slices, banana -200g";
    private static final String VALID_INGREDIENT_STRING_WITHOUTQUANTITY = "food -2g";


    @Test
    public void parser_validInput_withQuantity_success() throws ParseException {
        ArrayList<Ingredient> expectedIngredients = new ArrayList<>();
        expectedIngredients.add(validIngredient1);
        expectedIngredients.add(validIngredient2);
        assertEquals(expectedIngredients, IngredientParser.parse(VALID_INGREDIENT_STRING));
    }

    @Test
    public void parser_validInput_withoutQuantity_success() throws ParseException {
        ArrayList<Ingredient> expectedIngredients = new ArrayList<>();
        expectedIngredients.add(new IngredientBuilder().build());
        assertEquals(expectedIngredients, IngredientParser.parse(VALID_INGREDIENT_STRING_WITHOUTQUANTITY));
    }
}

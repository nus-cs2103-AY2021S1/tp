package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalIngredients.VALID_INGREDIENT1;
import static seedu.address.testutil.TypicalIngredients.VALID_INGREDIENT2;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.recipe.Ingredient;
import seedu.address.testutil.IngredientBuilder;

public class IngredientParserTest {
    private static final String VALID_INGREDIENT_STRING = "Potato -2 whole, Banana -200g";
    private static final String VALID_INGREDIENT_STRING_WITHOUTQUANTITY = "food";


    @Test
    public void parser_validInputWithQuantity_success() throws ParseException {
        ArrayList<Ingredient> expectedIngredients = new ArrayList<>();
        expectedIngredients.add(VALID_INGREDIENT1);
        expectedIngredients.add(VALID_INGREDIENT2);
        assertEquals(expectedIngredients, IngredientParser.parse(VALID_INGREDIENT_STRING));
    }

    @Test
    public void parser_validInputWithoutQuantity_success() throws ParseException {
        ArrayList<Ingredient> expectedIngredients = new ArrayList<>();
        expectedIngredients.add(new IngredientBuilder().buildWithoutQuantity());
        assertEquals(expectedIngredients, IngredientParser.parse(VALID_INGREDIENT_STRING_WITHOUTQUANTITY));
    }
}

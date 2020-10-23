// AutoCompleterTest.java

package chopchop.logic.autocomplete;

import java.util.List;
import java.util.HashMap;

import chopchop.logic.parser.CommandParser;

import chopchop.model.EntryBook;
import chopchop.model.ReadOnlyEntryBook;
import chopchop.model.ingredient.Ingredient;
import chopchop.model.recipe.Recipe;

import chopchop.testutil.TypicalIngredients;
import chopchop.testutil.TypicalRecipes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;



public class AutoCompleterTest {

    @Test
    public void test_autoCompletions() {

        var parser = new CommandParser();
        var model = new ModelStub();

        // Map.of() doesn't take enough arguments.
        var tests = new HashMap<String, String>();

        // test command completion
        tests.put("a",                                      "add");
        tests.put("f",                                      "find");
        tests.put("l",                                      "list");
        tests.put("q",                                      "quit");
        tests.put("h",                                      "help");
        tests.put("m",                                      "make");
        tests.put("u",                                      "undo");
        tests.put("r",                                      "redo");
        tests.put("d",                                      "delete");

        // test... non-completion
        tests.put("add",                                    "add");

        // target completion (recipes)
        tests.put("add r",                                  "add recipe");
        tests.put("find r",                                 "find recipe");
        tests.put("list r",                                 "list recipe");
        tests.put("delete r",                               "delete recipe");

        // target completion (ingredients)
        tests.put("add i",                                  "add ingredient");
        tests.put("find i",                                 "find ingredient");
        tests.put("list i",                                 "list ingredient");
        tests.put("delete i",                               "delete ingredient");

        // argument completion
        tests.put("add recipe cake /i",                     "add recipe cake /ingredient");
        tests.put("add recipe cake /q",                     "add recipe cake /qty");
        tests.put("add ingredient milk /q",                 "add ingredient milk /qty");
        tests.put("add ingredient milk /e",                 "add ingredient milk /expiry");

        // ingredient name completion
        tests.put("add recipe cake /ingredient a",          "add recipe cake /ingredient Apricot");
        tests.put("add recipe cake /ingredient b",          "add recipe cake /ingredient Banana");
        tests.put("delete ingredient a",                    "delete ingredient Apricot");

        // recipe name completion
        tests.put("delete recipe a",                        "delete recipe Apricot Salad");
        tests.put("make cus",                               "make Custard Salad");

        // things that don't change
        tests.put("",                                       "");
        tests.put("kk",                                     "kk");
        tests.put("add recipe",                             "add recipe");
        tests.put("find recipe",                            "find recipe");




        var completer = new AutoCompleter();

        tests.forEach((k, v) -> {
            var output = completer.getCompletionForInput(parser, model, k).strip();
            assertEquals(v, output);

            completer.resetCompletionState();
        });
    }



    private class ModelStub extends chopchop.model.ModelStub {

        private final EntryBook<Recipe> recipes;
        private final EntryBook<Ingredient> ingredients;

        public ModelStub() {
            this.recipes = new EntryBook<>();
            this.ingredients = new EntryBook<>();

            this.recipes.setAll(List.of(
                TypicalRecipes.APRICOT_SALAD,
                TypicalRecipes.BANANA_SALAD,
                TypicalRecipes.CUSTARD_SALAD
            ));

            this.ingredients.setAll(List.of(
                TypicalIngredients.APRICOT,
                TypicalIngredients.BANANA,
                TypicalIngredients.CUSTARD
            ));
        }

        @Override
        public ReadOnlyEntryBook<Ingredient> getIngredientBook() {
            return this.ingredients;
        }

        @Override
        public ReadOnlyEntryBook<Recipe> getRecipeBook() {
            return this.recipes;
        }
    }

}

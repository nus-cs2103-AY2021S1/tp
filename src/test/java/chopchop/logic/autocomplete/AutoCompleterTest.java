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

    private final CommandParser parser = new CommandParser();
    private final ModelStub model = new ModelStub();

    private void runTests(HashMap<String, String> cases) {
        var completer = new AutoCompleter();

        cases.forEach((k, v) -> {
            var output = completer.getCompletionForInput(parser, model, k).strip();
            assertEquals(v, output);

            completer.resetCompletionState();
        });
    }

    @Test
    public void test_commandCompletions() {

        // Map.of() doesn't take enough arguments.
        var cases = new HashMap<String, String>();

        // test command completion
        cases.put("a",                                      "add");
        cases.put("f",                                      "find");
        cases.put("l",                                      "list");
        cases.put("q",                                      "quit");
        cases.put("h",                                      "help");
        cases.put("e",                                      "edit");
        cases.put("v",                                      "view");
        cases.put("m",                                      "make");
        cases.put("u",                                      "undo");
        cases.put("r",                                      "redo");
        cases.put("d",                                      "delete");
        cases.put("fil",                                    "filter");

        runTests(cases);
    }


    @Test
    public void test_targetCompletionRecipes() {

        var cases = new HashMap<String, String>();

        // target completion (recipes)
        cases.put("add r",                                  "add recipe");
        cases.put("find r",                                 "find recipe");
        cases.put("list r",                                 "list recipe");
        cases.put("delete r",                               "delete recipe");

        runTests(cases);
    }

    @Test
    public void test_targetCompletionIngredients() {

        var cases = new HashMap<String, String>();

        // target completion (ingredients)
        cases.put("add i",                                  "add ingredient");
        cases.put("find i",                                 "find ingredient");
        cases.put("list i",                                 "list ingredient");
        cases.put("delete i",                               "delete ingredient");

        runTests(cases);
    }


    @Test
    public void test_argumentCompletions() {

        var cases = new HashMap<String, String>();

        // argument completion
        cases.put("add recipe cake /i",                     "add recipe cake /ingredient");
        cases.put("add recipe cake /q",                     "add recipe cake /qty");
        cases.put("add ingredient milk /q",                 "add ingredient milk /qty");
        cases.put("add ingredient milk /e",                 "add ingredient milk /expiry");

        cases.put("delete ingredient milk /q",              "delete ingredient milk /qty");

        cases.put("filter recipe /i",                       "filter recipe /ingredient");
        cases.put("filter recipe /t",                       "filter recipe /tag");

        cases.put("filter ingredient /e",                   "filter ingredient /expiry");
        cases.put("filter ingredient /t",                   "filter ingredient /tag");

        // no completions
        cases.put("delete recipe cake /q",                  "delete recipe cake /q");

        runTests(cases);
    }

    @Test
    public void test_ingredientCompletions() {

        var cases = new HashMap<String, String>();

        // ingredient name completion
        cases.put("add recipe cake /ingredient a",          "add recipe cake /ingredient Apricot");
        cases.put("add recipe cake /ingredient ban",        "add recipe cake /ingredient Banana");
        cases.put("add recipe cake /ingredient baked b",    "add recipe cake /ingredient Baked beans");
        cases.put("delete ingredient a",                    "delete ingredient Apricot");

        cases.put("add ingredient c",                       "add ingredient Custard");

        runTests(cases);
    }

    @Test
    public void test_recipeCompletions() {

        var cases = new HashMap<String, String>();

        // recipe name completion
        cases.put("delete recipe a",                        "delete recipe Apricot Salad");
        cases.put("make cus",                               "make Custard Salad");
        cases.put("view b",                                 "view Banana Salad");

        // this should not complete
        cases.put("add recipe a",                           "add recipe a");

        runTests(cases);
    }

    @Test
    public void test_editCompletions() {
        var cases = new HashMap<String, String>();

        // edit completion (a whole thing on its own)
        cases.put("edit r",                                 "edit recipe");
        cases.put("edit recipe c",                          "edit recipe Custard Salad");
        cases.put("edit recipe cake /i",                    "edit recipe cake /ingredient:");
        cases.put("edit recipe cake /ingredient:a",         "edit recipe cake /ingredient:add");
        cases.put("edit recipe cake /ingredient:e",         "edit recipe cake /ingredient:edit");
        cases.put("edit recipe cake /ingredient:d",         "edit recipe cake /ingredient:delete");

        cases.put("edit recipe cake /ingredient:add a",     "edit recipe cake /ingredient:add Apricot");
        cases.put("edit recipe cake /ingredient:add Apricot /q", "edit recipe cake /ingredient:add Apricot /qty");

        cases.put("edit recipe cake /step:a",               "edit recipe cake /step:add");
        cases.put("edit recipe cake /step:e",               "edit recipe cake /step:edit:");
        cases.put("edit recipe cake /step:d",               "edit recipe cake /step:delete:");

        cases.put("edit recipe cake /tag:a",                "edit recipe cake /tag:add");
        cases.put("edit recipe cake /tag:d",                "edit recipe cake /tag:delete");

        // this one shouldn't work cos you can't do /tag:edit
        cases.put("edit recipe cake /tag:e",                "edit recipe cake /tag:e");

        cases.put("edit recipe cake /tag:a",                "edit recipe cake /tag:add");
        cases.put("edit recipe cake /tag:d",                "edit recipe cake /tag:delete");

        // no completions:
        cases.put("edit ingredient /f",                     "edit ingredient /f");
        cases.put("edit recipe /qty:e",                     "edit recipe /qty:e");
        cases.put("edit recipe /qty:e:f:g",                 "edit recipe /qty:e:f:g");
        cases.put("edit recipe cake /step:add:",            "edit recipe cake /step:add:");

        runTests(cases);
    }

    @Test
    public void test_completionCycling() {

        var cases = new HashMap<String, List<String>>();

        cases.put("f",                                      List.of("find", "filter"));
        cases.put("add recipe cake /ingredient b",          List.of("add recipe cake /ingredient Banana",
            "add recipe cake /ingredient Baked beans"));

        cases.forEach((k, v) -> {
            var completer = new AutoCompleter();

            var input = k;
            var outputs = v;

            for (var out : outputs) {
                input = completer.getCompletionForInput(parser, model, input);
                assertEquals(out.strip(), input.strip());
            }
        });
    }


    @Test
    public void test_noCompletions() {

        var cases = new HashMap<String, String>();

        // things that don't change
        cases.put("",                                       "");
        cases.put("kk",                                     "kk");
        cases.put("add",                                    "add");
        cases.put("add owo /q",                             "add owo /q");
        cases.put("add recipe",                             "add recipe");
        cases.put("find recipe",                            "find recipe");
        cases.put("find recipe /",                          "find recipe /");
        cases.put("filter owo /f",                          "filter owo /f");
        cases.put("list /f",                                "list /f");
        cases.put("list /tag t",                            "list /tag t");
        cases.put("list /qty 5",                            "list /qty 5");

        runTests(cases);
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
                TypicalIngredients.CUSTARD,
                TypicalIngredients.BAKED_BEANS
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

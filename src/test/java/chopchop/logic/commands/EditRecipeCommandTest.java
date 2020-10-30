// EditRecipeCommandTest.java

package chopchop.logic.commands;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import chopchop.commons.util.Pair;
import chopchop.commons.util.Result;
import chopchop.logic.commands.exceptions.CommandException;
import chopchop.logic.history.HistoryManager;
import chopchop.logic.parser.CommandParser;
import chopchop.model.EntryBook;
import chopchop.model.Model;
import chopchop.model.ReadOnlyEntryBook;
import chopchop.model.attributes.Step;
import chopchop.model.attributes.Tag;
import chopchop.model.attributes.units.Count;
import chopchop.model.attributes.units.Volume;
import chopchop.model.ingredient.Ingredient;
import chopchop.model.recipe.Recipe;
import chopchop.testutil.StubbedModel;
import chopchop.testutil.TypicalIngredients;
import chopchop.testutil.TypicalRecipes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class EditRecipeCommandTest {

    private CommandResult runCommand(Model m, String str) {
        var parser = new CommandParser();
        var c = parser.parse(str);

        if (c.isError()) {
            assertEquals(1, c.getError());
        }

        assertTrue(c.hasValue());

        try {
            return c.getValue().execute(m, new HistoryManager());
        } catch (CommandException e) {
            return CommandResult.error(e.getMessage());
        }
    }

    private CommandResult runCommand(String str) {
        return runCommand(new StubbedModel(), str);
    }

    @Test
    void test_nameEdits() {
        {
            var c = runCommand("edit recipe owo");
            assertTrue(c.isError());
            assertEquals("Error: No recipe named 'owo'", c.toString());
        }

        {
            var c = runCommand("edit recipe custard salad");
            assertTrue(c.didSucceed());
            assertEquals("No edits provided; recipe 'Custard Salad' was not modified", c.toString());
        }

        {
            var m = new StubbedModel();

            var c = runCommand(m, "edit recipe custard salad /name owo salad");
            assertTrue(c.didSucceed());

            assertTrue(m.findRecipeWithName("owo salad").isPresent());
        }

        {
            var m = new StubbedModel();

            var c = runCommand(m, "edit recipe custard salad /name apricot salad");
            assertTrue(c.isError());
            assertEquals("Error: Cannot rename recipe 'Custard Salad' to 'apricot salad'; the latter already exists",
                c.toString());
        }
    }

    @Test
    void test_ingredientEdits() {
        {
            var m = new StubbedModel();

            var c = runCommand(m, "edit recipe custard salad /ingredient:edit custard /qty 69");
            assertTrue(c.didSucceed());

            var r = m.findRecipeWithName("custard salad");
            assertTrue(r.isPresent());

            var recipe = r.get();
            assertEquals("custard", recipe.getIngredients().get(0).getName());
            assertEquals(Count.of(69), recipe.getIngredients().get(0).getQuantity());
        }

        {
            var m = new StubbedModel();

            var c = runCommand(m, "edit recipe custard salad /ingredient:add milk /qty 400ml");
            assertTrue(c.didSucceed());

            var r = m.findRecipeWithName("custard salad");
            assertTrue(r.isPresent());

            var recipe = r.get();
            assertEquals("milk", recipe.getIngredients().get(1).getName());
            assertEquals(Volume.litres(0.4), recipe.getIngredients().get(1).getQuantity());
        }

        {
            var m = new StubbedModel();

            var c = runCommand(m, "edit recipe custard salad /ingredient:add custard /qty 4");
            assertTrue(c.isError());
            assertEquals("Error: Recipe 'Custard Salad' already contains ingredient 'custard'", c.toString());
        }

        {
            var m = new StubbedModel();

            var c = runCommand(m, "edit recipe custard salad /ingredient:delete custard");
            assertTrue(c.didSucceed());

            var r = m.findRecipeWithName("custard salad");
            assertTrue(r.isPresent());

            var recipe = r.get();
            assertEquals(0, recipe.getIngredients().size());
        }

        {
            var m = new StubbedModel();

            var c = runCommand(m, "edit recipe custard salad /ingredient:edit monkaS /qty 1");
            assertTrue(c.isError());
            assertEquals("Error: Recipe doesn't contain an ingredient named 'monkaS'", c.toString());
        }
    }

    @Test
    void test_equals() {
        var p = new CommandParser();
        var c1 = p.parse("edit recipe custard salad /name owo salad");
        var c2 = p.parse("edit recipe custard salad /name owo salad");
        var c3 = p.parse("edit recipe custard salad /name uwu salad");
        var c4 = p.parse("edit recipe uwu salad /name owo salad");

        assertEquals(c1, c1);
        assertEquals(c1, c2);

        assertNotEquals(c1, Result.of("asdf"));
        assertNotEquals(c1, c3);
        assertNotEquals(c3, c4);
    }

    @Test
    void test_undo() {
        var m = new StubbedModel();
        var p = new CommandParser();
        p.parse("edit recipe custard salad /name owo salad")
            .map(c -> {
                try {
                    return Pair.of(c, c.execute(m, new HistoryManager()));
                } catch (CommandException e) {
                    return Pair.of(c, CommandResult.error(e.getMessage()));
                }
            })
            .map(cr -> {
                assertTrue(cr.snd().didSucceed());
                return cr;
            })
            .map(cr -> {
                ((EditRecipeCommand) cr.fst()).undo(m);
                return cr;
            });

        assertFalse(m.findRecipeWithName("owo salad").isPresent());
    }

    @Test
    void test_editSteps() {
        {
            var m = new StubbedModel();

            var c = runCommand(m, "edit recipe custard salad /step:add asdfasdjf");
            assertTrue(c.didSucceed());

            var r = m.findRecipeWithName("custard salad");
            assertTrue(r.isPresent());

            var recipe = r.get();
            assertEquals(1 + TypicalRecipes.CUSTARD_SALAD.getSteps().size(), recipe.getSteps().size());
        }

        {
            var m = new StubbedModel();

            {
                var c = runCommand(m, "edit recipe custard salad /step:add:1 asdfasdjf");
                assertTrue(c.didSucceed());

                var r = m.findRecipeWithName("custard salad");
                assertTrue(r.isPresent());

                var recipe = r.get();
                assertEquals(1 + TypicalRecipes.CUSTARD_SALAD.getSteps().size(), recipe.getSteps().size());
                assertEquals("asdfasdjf", recipe.getSteps().get(0).toString());
            }

            {
                var c = runCommand(m, "edit recipe custard salad /step:edit:1 uwuwuwuwuwu");
                assertTrue(c.didSucceed());

                var r = m.findRecipeWithName("custard salad");
                assertTrue(r.isPresent());

                var recipe = r.get();
                assertEquals(1 + TypicalRecipes.CUSTARD_SALAD.getSteps().size(), recipe.getSteps().size());
                assertEquals("uwuwuwuwuwu", recipe.getSteps().get(0).toString());
            }

            {

                var c = runCommand(m, "edit recipe custard salad /step:delete:1");
                assertTrue(c.didSucceed());

                var r = m.findRecipeWithName("custard salad");
                assertTrue(r.isPresent());

                var recipe = r.get();
                assertNotEquals("asdfasdjf", recipe.getSteps().get(0).toString());
            }
        }

        {
            var m = new StubbedModel();

            var c = runCommand(m, "edit recipe custard salad /step:edit:99999 owowowo");
            assertTrue(c.isError());
        }
    }


    @Test
    void test_tagEdits() {
        var m = new StubbedModel();

        {
            var c = runCommand(m, "edit recipe custard salad /tag:add tag 1");
            assertTrue(c.didSucceed());

            var r = m.findRecipeWithName("custard salad");
            assertTrue(r.isPresent());

            var recipe = r.get();
            assertTrue(recipe.getTags().contains(new Tag("tag 1")));
        }

        {
            var c = runCommand(m, "edit recipe custard salad /tag:delete tag 1");
            assertTrue(c.didSucceed());

            var r = m.findRecipeWithName("custard salad");
            assertTrue(r.isPresent());

            var recipe = r.get();
            assertFalse(recipe.getTags().contains(new Tag("tag 1")));
        }

        {
            var c = runCommand(m, "edit recipe custard salad /tag:delete owowowo");
            assertTrue(c.isError());
            assertEquals("Error: Recipe 'Custard Salad' does not have tag 'owowowo'", c.toString());
        }
    }
}

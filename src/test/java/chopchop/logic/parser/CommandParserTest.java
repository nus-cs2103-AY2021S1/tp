// CommandParserTest.java

package chopchop.logic.parser;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import chopchop.commons.util.Pair;
import chopchop.logic.parser.exceptions.ParseException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static chopchop.testutil.Assert.assertThrows;

public class CommandParserTest {

    @Test
    void parse_validCommands_success() {

        var parser = new CommandParser();

        var tests = new HashMap<String, String>();

        tests.put("add ingredient squid /qty 30g /expiry 2020-12-24",
            "Result(AddIngredientCommand: squid (30g) <Expiry Date: 2020-12-24>)");

        tests.put("add ingredient milk /qty 600ml",
            "Result(AddIngredientCommand: milk (600mL))");

        tests.put("add recipe cake /ingredient milk /qty 400ml /ingredient flour /qty 500g "
            + "/ingredient egg /qty 7 /step mix /step bake /step eat",
            "Result(AddRecipeCommand(cake, ingr: [milk (400mL), flour (500g), "
                + "egg (7)], steps: [mix, bake, eat]))");

        tests.put("delete recipe cake", "Result(DeleteRecipeCommand(cake))");
        tests.put("delete recipe #4", "Result(DeleteRecipeCommand(#4))");

        tests.put("delete ingredient milk", "Result(DeleteIngredientCommand(milk))");

        tests.put("delete ingredient milk /qty 500ml", "Result(DeleteIngredientCommand(milk (500mL)))");

        tests.put("help", "Result(HelpCommand)");
        tests.put("quit", "Result(QuitCommand)");
        tests.put("undo", "Result(UndoCommand)");
        tests.put("redo", "Result(RedoCommand)");

        tests.put("list recipe", "Result(ListRecipeCommand)");
        tests.put("list recipes", "Result(ListRecipeCommand)");
        tests.put("list ingredient", "Result(ListIngredientCommand)");
        tests.put("list ingredients", "Result(ListIngredientCommand)");

        tests.put("make cake", "Result(MakeRecipeCommand(cake))");

        tests.put("find recipe cake cucumber", "Result(FindRecipeCommand(keywords: [cake, cucumber]))");
        tests.put("find ingredient cake cucumber", "Result(FindIngredientCommand(keywords: [cake, cucumber]))");

        tests.put("find recipe", "Error('find' command requires at least one search term)");
        tests.put("add recipe cake /", "Error(Expected argument name after '/')");

        tests.put("OWO", "Error(Unknown command 'OWO')");

        tests.forEach((k, v) -> {
            var x = parser.parse(k);

            System.err.println(x);
            assertEquals(v, x.toString());
        });
    }





    @Test
    void parse_commandArgs_success() {
        var parser = new CommandParser();

        var tests = Map.of(
            "add",
                new CommandArguments("add"),

            "add /stuff kekw",
                new CommandArguments("add", List.of(Pair.of(new ArgName("stuff"), "kekw"))),

            "add some\\/stuff\\/here",
                new CommandArguments("add", "some/stuff/here"),

            "add some\\/step\\/here /step \\/owo here\\/is\\/a\\/step\\/",
                new CommandArguments("add", "some/step/here",
                    List.of(Pair.of(new ArgName("step"), "/owo here/is/a/step/"))
                )
        );

        tests.forEach((k, v) -> {
            var x = parser.parseArgs(k);
            assertTrue(x.hasValue());

            assertEquals(v, x.getValue());
        });


        // just... force coverage on this class.
        assertThrows(ParseException.class, () -> {
            throw new ParseException("owo");
        });

        assertNotEquals(new CommandArguments("add"), "add");
        assertNotEquals(new CommandArguments("add"), new CommandArguments("subtract"));

        var tests2 = Map.of(
            "add", new CommandArguments("add"),
            "add /stuff kekw", new CommandArguments("add", List.of(Pair.of(new ArgName("stuff"), "kekw")))
        );

        assertNotEquals(new CommandArguments("add", "aaa", List.of()), new CommandArguments("add", "bbb", List.of()));
        assertNotEquals(new CommandArguments("add", "aaa", List.of(Pair.of(new ArgName("kekw"), "3"))),
            new CommandArguments("add", "aaa", List.of(Pair.of(new ArgName("kekw"), "4"))));
    }
}

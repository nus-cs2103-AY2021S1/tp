// CommandParserTest.java

package chopchop.logic.parser;

import java.util.List;
import java.util.Map;

import chopchop.commons.util.Pair;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandParserTest {

    @Test
    void parse_validCommands_success() {

        var parser = new CommandParser();

        var tests = Map.of(
            "add ingredient squid /qty 30g /expiry 2020-12-24",
            "Result(AddIngredientCommand: squid (30g) expires: 2020-12-24 \nTags: \nNo tags attached)",

            "add ingredient milk /qty 600ml",
            "Result(AddIngredientCommand: milk (600ml) \nTags: \nNo tags attached)",

            "add recipe cake /ingredient milk /qty 400ml /ingredient flour /qty 500g "
                    + "/ingredient egg /qty 7 /step mix /step bake /step eat",
            "Result(AddRecipeCommand: cake Ingredients: milk (400ml), flour (500g), "
                    + "egg (7) Steps: 1. mix, 2. bake, 3. eat Tags: No tags attached)"
        );

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
            "add", new CommandArguments("add"),
            "add /stuff kekw", new CommandArguments("add", List.of(Pair.of(new ArgName("stuff"), "kekw")))
        );

        tests.forEach((k, v) -> {
            var x = parser.parseArgs(k);
            assertTrue(x.hasValue());

            assertEquals(v, x.getValue());
        });
    }
}

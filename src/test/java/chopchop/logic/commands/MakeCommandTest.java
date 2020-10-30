// MakeCommandTest.java

package chopchop.logic.commands;

import chopchop.logic.commands.exceptions.CommandException;
import chopchop.logic.history.HistoryManager;
import chopchop.logic.parser.CommandParser;
import chopchop.model.Model;
import chopchop.model.attributes.units.Mass;
import chopchop.testutil.StubbedModel;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MakeCommandTest {

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

    private CommandResult runCommand(Command c, Model m) {
        try {
            return c.execute(m, new HistoryManager());
        } catch (CommandException e) {
            return CommandResult.error(e.getMessage());
        }
    }

    @Test
    void test() {
        var m = new StubbedModel();
        runCommand(m, "add ingredient sprinkles /qty 400g");
        runCommand(m, "add ingredient rainbows /qty 400g");
        runCommand(m, "add recipe uwu salad /ingredient sprinkles /qty 50g /step sprinkle it");
        runCommand(m, "add recipe asdf salad /ingredient rainbows /qty 50ml /step uwu");
        runCommand(m, "add recipe rainbow salad /ingredient rainbows /qty 400g /step uwu");
        runCommand(m, "delete ingredient sprinkles");

        var c1 = runCommand(m, "make apricot salad");
        assertTrue(c1.didSucceed());

        var c2 = runCommand(m, "make owo salad");
        assertTrue(c2.isError());
        assertEquals("Error: No recipe named 'owo salad'", c2.toString());

        var c3 = runCommand(m, "make uwu salad");
        assertTrue(c3.isError());
        assertEquals("Error: Missing ingredient 'sprinkles' (not found)", c3.toString());

        var c4 = runCommand(m, "make asdf salad");
        assertTrue(c4.isError());
        assertEquals("Error: Could not make recipe 'asdf salad' (caused by ingredient 'rainbows'): "
            + "Cannot compare '50mL' with '400g' (incompatible units)", c4.toString());

        {
            var p = new CommandParser();
            var c = p.parse("make rainbow salad").getValue();

            var r1 = runCommand(c, m);
            assertTrue(r1.didSucceed());

            var u1 = ((MakeCommand) c).undo(m);
            assertTrue(u1.didSucceed());

            assertTrue(m.findIngredientWithName("rainbows").get().getQuantity().equals(Mass.grams(400)));
        }

        {
            var p = new CommandParser();
            var c = p.parse("make apricot salad").getValue();

            var r1 = runCommand(c, m);
            assertTrue(r1.didSucceed());

            var r2 = ((MakeCommand) c).undo(m);
            assertTrue(r2.didSucceed());
        }

        if (true) {
            var p = new CommandParser();
            var cc1 = p.parse("make apricot salad").getValue();
            var cc2 = p.parse("make uwu salad").getValue();
            var cc3 = p.parse("make uwu salad").getValue();
            var cc4 = p.parse("make apricot salad").getValue();

            runCommand(cc1, m);
            runCommand(cc2, m);
            runCommand(cc3, m);
            runCommand(cc4, m);

            assertEquals(cc1, cc1);
            assertEquals(cc2, cc3);

            assertNotEquals(cc1, "asdf");
            assertNotEquals(cc1, cc3);
            assertNotEquals(cc1, cc4);
        }
    }
}

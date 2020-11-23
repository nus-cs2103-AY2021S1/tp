package seedu.stock.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.stock.testutil.TypicalStocks.APPLE;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

public class CommandResultTest {
    @Test
    public void equals() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns true
        assertTrue(commandResult.equals(new CommandResult("feedback")));
        assertTrue(commandResult.equals(new CommandResult("feedback", null,
                false, false, null, false, null,
                false, false)));

        // same object -> returns true
        assertTrue(commandResult.equals(commandResult));

        // null -> returns false
        assertFalse(commandResult.equals(null));

        // different types -> returns false
        assertFalse(commandResult.equals(0.5f));

        // different feedbackToUser value -> returns false
        assertFalse(commandResult.equals(new CommandResult("different")));

        Map<String, Integer> nonNullStatisticsData = new HashMap<>();
        nonNullStatisticsData.put("different", 1);

        //different statisticsData value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", new HashMap<>(),
                false, false, null, false, null,
                false, false)));

        // different showHelp value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", null,
                true, false, null, false, null,
                false, false)));

        // different showStockView value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", null,
                true, true, null, false, null,
                false, false)));

        // different stockToView value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", null,
                true, false, APPLE, false, null,
                false, false)));

        //different showStatistics value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", null,
                false, false, null, true, null,
                false, false)));

        // different otherStatisticsData value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", null,
                true, false, APPLE, false, new String[]{},
                false, false)));

        //different isSwitchTab value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", null,
                false, false, null, false, null,
                true, false)));

        // different exit value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", null,
                false, false, null, false, null,
                false, true)));
    }

    @Test
    public void hashcode() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns same hashcode
        assertEquals(commandResult.hashCode(), new CommandResult("feedback").hashCode());

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("different").hashCode());

        Map<String, Integer> nonNullStatisticsData = new HashMap<>();
        nonNullStatisticsData.put("data", 1);

        //different statisticsData value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", nonNullStatisticsData,
                false, false, null, false, null,
                false, false).hashCode());

        // different showHelp value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", null,
                true, false, null, false, null,
                false, false).hashCode());

        // different showStockView value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", null,
                true, true, null, false, null,
                false, false).hashCode());

        // different stockToView value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", null,
                true, false, APPLE, false, null,
                false, false).hashCode());

        //different showStatistics value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", null,
                false, false, null, true, null,
                false, false).hashCode());

        // different otherStatisticsData value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", null,
                true, false, APPLE, false, new String[]{},
                false, false).hashCode());

        //different isSwitchTab value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", null,
                false, false, null, false, null,
                true, false).hashCode());

        // different exit value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", null,
                false, false, null, false, null,
                false, true).hashCode());

    }
}

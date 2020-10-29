package chopchop.ui.testutil;

import static org.junit.jupiter.api.Assertions.assertEquals;

import guitests.guihandles.CommandOutputHandle;
import guitests.guihandles.RecipeCardHandle;

/**
 * A set of assertion methods useful for writing GUI tests.
 */
public class GuiTestAssert {
    /**
     * Asserts that {@code actualCard} displays the same values as {@code expectedCard}.
     */
    public static void assertRecipeCardEquals(RecipeCardHandle expectedCard, RecipeCardHandle actualCard) {
        assertEquals(expectedCard.getName(), actualCard.getName());
    }

    /**
     * Asserts the message shown in {@code commandOutputHandle} equals to {@code expected}.
     */
    public static void assertResultMessage(CommandOutputHandle commandOutputHandle, String expected) {
        assertEquals(expected, commandOutputHandle.getText());
    }

}

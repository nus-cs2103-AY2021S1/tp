package seedu.bagel.commons.util;

import java.util.ArrayList;
import java.util.Arrays;

public class CommandSuggestionUtil {
    /**
     * Creates a list of available command formats.
     */
    public static ArrayList<String> initSuggestions() {
        ArrayList<String> commandFormats = new ArrayList<>();
        commandFormats.addAll(
                Arrays.asList(
                        "add t/TITLE d/DESCRIPTION s/SET [l/LINK] [tag/TAG]…",
                        "clear",
                        "delete INDEX",
                        "edit INDEX [t/TITLE] [d/DESCRIPTION] [s/SET] [l/LINK] [tag/TAG]…",
                        "exit",
                        "flip",
                        "help",
                        "list [s/SET_NUMBER]",
                        "view INDEX",
                        "search k/KEYWORD",
                        "sort r/tag",
                        "sort r/atitle",
                        "sort r/dtitle"
                )
        );
        return commandFormats;
    }
}

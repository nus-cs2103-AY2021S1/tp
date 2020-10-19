package seedu.stock.logic.commands;

import java.util.ArrayList;
import java.util.List;

public class CommandWords {
<<<<<<< HEAD
    public static final String ADD_COMMAND_WORD = AddCommand.COMMAND_WORD;
    public static final String CLEAR_COMMAND_WORD = ClearCommand.COMMAND_WORD;
    public static final String DELETE_COMMAND_WORD = DeleteCommand.COMMAND_WORD;
    public static final String EXIT_COMMAND_WORD = ExitCommand.COMMAND_WORD;
    public static final String FIND_COMMAND_WORD = FindCommand.COMMAND_WORD;
    public static final String FIND_EXACT_COMMAND_WORD = FindExactCommand.COMMAND_WORD;
    public static final String HELP_COMMAND_WORD = HelpCommand.COMMAND_WORD;
    public static final String LIST_COMMAND_WORD = ListCommand.COMMAND_WORD;
    public static final String UPDATE_COMMAND_WORD = UpdateCommand.COMMAND_WORD;
    public static final String NOTE_COMMAND_WORD = NoteCommand.COMMAND_WORD;
=======
    public static final String ADD_COMMAND_WORD = "add";
    public static final String CLEAR_COMMAND_WORD = "clear";
    public static final String DELETE_COMMAND_WORD = "delete";
    public static final String STATISTICS_COMMAND_WORD = "stats";
    public static final String EXIT_COMMAND_WORD = "exit";
    public static final String FIND_COMMAND_WORD = "find";
    public static final String FIND_EXACT_COMMAND_WORD = "findexact";
    public static final String HELP_COMMAND_WORD = "help";
    public static final String LIST_COMMAND_WORD = "list";
    public static final String UPDATE_COMMAND_WORD = "update";
>>>>>>> edf3ef60739d8fbddafbdae256c8fd9ef53a0f01

    /**
     * Returns all command words existing in Warenager.
     *
     * @return A list of all command words in Warenager.
     */
    public static List<String> getAllCommandWords() {
        List<String> allCommandWords = new ArrayList<>();
        allCommandWords.add(ADD_COMMAND_WORD);
        allCommandWords.add(CLEAR_COMMAND_WORD);
        allCommandWords.add(DELETE_COMMAND_WORD);
        allCommandWords.add(STATISTICS_COMMAND_WORD);
        allCommandWords.add(EXIT_COMMAND_WORD);
        allCommandWords.add(FIND_COMMAND_WORD);
        allCommandWords.add(FIND_EXACT_COMMAND_WORD);
        allCommandWords.add(HELP_COMMAND_WORD);
        allCommandWords.add(LIST_COMMAND_WORD);
        allCommandWords.add(UPDATE_COMMAND_WORD);
        allCommandWords.add(NOTE_COMMAND_WORD);
        return allCommandWords;
    }
}

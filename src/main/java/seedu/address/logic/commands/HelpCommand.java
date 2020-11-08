package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.LinkedHashMap;
import java.util.Set;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_HELP_MESSAGE = "Opened help window.";

    public static final String NO_COMMAND_MESSAGE = "There are no commands containing this keyword. "
            + "A brief description of all possible commands is listed below.\n\n";

    private static final String separator = "-----------------------------------------------------------------------"
            + "-----------------------------------------------------------------------------------------------------\n";

    public static final String HELP_MESSAGE = "help:\n"
            + "Shows program usage instructions and format for all commands.\n"
            + "Additionally, use a keyword to search for a group of commands.\n"
            + "Format: help OR help [COMMAND_KEYWORD]\n"
            + separator;
    public static final String CLEAR_MESSAGE = "clear:\n"
            + "Clears all data entries from fitNUS.\n"
            + "Format: clear\n"
            + separator;
    public static final String EXIT_MESSAGE = "exit:\n"
            + "Terminates the program.\n"
            + "Format: exit\n"
            + separator;
    public static final String ADD_HEIGHT_MESSAGE = "height:\n"
            + "Adds user's height (in cm) to fitNUS.\n"
            + "Format: height h/HEIGHT\n"
            + separator;
    public static final String ADD_WEIGHT_MESSAGE = "weight:\n"
            + "Adds user's weight (in kg) to fitNUS.\n"
            + "Format: weight w/WEIGHT\n"
            + separator;
    public static final String BMI_MESSAGE = "bmi:\n"
            + "Views the user's BMI.\n"
            + "Format: bmi\n"
            + separator;
    public static final String CALORIE_ADD_MESSAGE = "calorie_add:\n"
            + "Adds the user's caloric intake to today's sum.\n"
            + "Format: calorie_add c/CALORIE\n"
            + separator;
    public static final String CALORIE_MINUS_MESSAGE = "calorie_minus:\n"
            + "Deducts the specified calorie amount from today's sum.\n"
            + "Format: calorie_minus c/CALORIE\n"
            + separator;
    public static final String EXERCISE_ADD_MESSAGE = "exercise_add:\n"
            + "Adds an exercise to fitNUS.\n"
            + "Format: exercise_add e/EXERCISE [t/TAG]...\n"
            + separator;
    public static final String EXERCISE_DELETE_MESSAGE = "exercise_delete:\n"
            + "Deletes the exercise identified by the index number used in the displayed exercise list.\n"
            + "Format: exercise_delete INDEX (must be a positive integer)\n"
            + separator;
    public static final String EXERCISE_EDIT_MESSAGE = "exercise_edit:\n"
            + "Edits the details of the exercise identified by the index number used in the displayed exercise list.\n"
            + "Existing values will be overwritten by the input values.\n"
            + "Format: exercise_edit INDEX (must be a positive integer) [e/EXERCISE] [t/TAG]...\n"
            + separator;
    public static final String EXERCISE_FIND_MESSAGE = "exercise_find:\n"
            + "Finds all exercises whose names contain all of the specified keywords (case-insensitive) and displays\n"
            + "them as a list with index numbers.\n"
            + "Format: exercise_find KEYWORD [MORE_KEYWORDS]...\n"
            + separator;
    public static final String EXERCISE_LIST_MESSAGE = "exercise_list:\n"
            + "Displays a list of all exercises in fitNUS.\n"
            + "Format: exercise_list\n"
            + separator;
    public static final String ROUTINE_CREATE_MESSAGE = "routine_create:\n"
            + "Creates a new routine in fitNUS.\n"
            + "Format: routine_create r/ROUTINE\n"
            + separator;
    public static final String ROUTINE_DELETE_MESSAGE = "routine_delete:\n"
            + "Deletes the routine identified by the index number used in the displayed routine list.\n"
            + "Format: routine_delete INDEX (must be a positive integer)\n"
            + separator;
    public static final String ROUTINE_FIND_MESSAGE = "routine_find:\n"
            + "Finds all routines whose names contain all of the specified keywords (case-insensitive) and displays\n"
            + "them as a list with index numbers.\n"
            + "Format: routine_find KEYWORD [MORE_KEYWORDS]...\n"
            + separator;
    public static final String ROUTINE_LIST_MESSAGE = "routine_list\n"
            + "Displays a list of all routines in fitNUS.\n"
            + "Format: routine_list\n"
            + separator;
    public static final String ROUTINE_ADD_EXERCISE_MESSAGE = "routine_add_exercise:\n"
            + "Adds an existing exercise to a routine in fitNUS.\n"
            + "Format: routine_add_exercise r/ROUTINE e/EXERCISE\n"
            + separator;
    public static final String ROUTINE_DELETE_EXERCISE_MESSAGE = "routine_delete_exercise:\n"
            + "Deletes the exercise from the specified routine.\n"
            + "Format: routine_delete_exercise r/ROUTINE e/EXERCISE\n"
            + separator;
    public static final String ROUTINE_VIEW_MESSAGE = "routine_view:\n"
            + "Views all exercises contained in a routine in fitNUS.\n"
            + "Format: routine_view INDEX (must be a positive integer)\n"
            + separator;
    public static final String LESSON_ADD_MESSAGE = "lesson_add:\n"
            + "Adds a lesson to fitNUS.\n"
            + "Format: lesson_add n/LESSON [t/TAG]...\n"
            + separator;
    public static final String LESSON_DELETE_MESSAGE = "lesson_delete:\n"
            + "Deletes the lesson identified by the index number used in the displayed lesson list.\n"
            + "Format: lesson_delete INDEX (must be a positive integer)\n"
            + separator;
    public static final String LESSON_EDIT_MESSAGE = "lesson_edit:\n"
            + "Edits the details of the lesson identified by the index number used in the displayed lesson list.\n"
            + "Existing values will be overwritten by the input values.\n"
            + "Format: lesson_edit INDEX (must be a positive integer) [n/LESSON] [t/TAG]...\n"
            + separator;
    public static final String LESSON_FIND_MESSAGE = "lesson_find:\n"
            + "Finds all lessons whose names contain any of the specified keywords (case-insensitive) and displays\n"
            + "them as a list with index numbers.\n"
            + "Format: lesson_find KEYWORD [MORE_KEYWORDS]...\n"
            + separator;
    public static final String LESSON_LIST_MESSAGE = "lesson_list:\n"
            + "Displays a list of all lessons in fitNUS.\n"
            + "Format: lesson_list\n"
            + separator;
    public static final String TIMETABLE_ADD_ROUTINE_MESSAGE = "timetable_add_routine:\n"
            + "Adds an existing routine to the timetable in fitNUS.\n"
            + "Format: timetable_add_routine r/ROUTINE D/DAY T/TIME\n"
            + separator;
    public static final String TIMETABLE_ADD_LESSON_MESSAGE = "timetable_add_lesson:\n"
            + "Adds an existing lesson to the timetable in fitNUS.\n"
            + "Format: timetable_add_lesson n/LESSON D/DAY T/TIME\n"
            + separator;
    public static final String TIMETABLE_DELETE_SLOT_MESSAGE = "timetable_delete_slot:\n"
            + "Deletes the slot identified by its day and time.\n"
            + "Format: timetable_delete_slot D/Day T/TIME\n"
            + separator;

    public static final String STANDARDIZED_HELP_MESSAGE = HELP_MESSAGE
            + CLEAR_MESSAGE
            + EXIT_MESSAGE
            + ADD_HEIGHT_MESSAGE
            + ADD_WEIGHT_MESSAGE
            + BMI_MESSAGE
            + CALORIE_ADD_MESSAGE
            + CALORIE_MINUS_MESSAGE
            + EXERCISE_ADD_MESSAGE
            + EXERCISE_DELETE_MESSAGE
            + EXERCISE_EDIT_MESSAGE
            + EXERCISE_FIND_MESSAGE
            + EXERCISE_LIST_MESSAGE
            + ROUTINE_CREATE_MESSAGE
            + ROUTINE_DELETE_MESSAGE
            + ROUTINE_FIND_MESSAGE
            + ROUTINE_LIST_MESSAGE
            + ROUTINE_ADD_EXERCISE_MESSAGE
            + ROUTINE_DELETE_EXERCISE_MESSAGE
            + ROUTINE_VIEW_MESSAGE
            + LESSON_ADD_MESSAGE
            + LESSON_DELETE_MESSAGE
            + LESSON_EDIT_MESSAGE
            + LESSON_FIND_MESSAGE
            + LESSON_LIST_MESSAGE
            + TIMETABLE_ADD_ROUTINE_MESSAGE
            + TIMETABLE_ADD_LESSON_MESSAGE
            + TIMETABLE_DELETE_SLOT_MESSAGE;

    private static final LinkedHashMap<String, String> privateMap = new LinkedHashMap<>();
    private static Set<String> privateSet;

    private static String filteredSearch;

    private String keyword;

    public HelpCommand() {
        // for testing in HelpCommandTest class
    }

    /**
     * Creates a HelpCommand to filter out the {@code String}
     */
    public HelpCommand(String keyword) {
        this.keyword = keyword.trim();
        addMessagesToMap();
        privateSet = keySet();
    }

    public static String getFilteredSearch() {
        return filteredSearch;
    }

    /**
     * Adds all help messages to the private HashMap.
     */
    private void addMessagesToMap() {
        privateMap.put("help", HELP_MESSAGE);
        privateMap.put("clear", CLEAR_MESSAGE);
        privateMap.put("exit", EXIT_MESSAGE);
        privateMap.put("height", ADD_HEIGHT_MESSAGE);
        privateMap.put("weight", ADD_WEIGHT_MESSAGE);
        privateMap.put("bmi", BMI_MESSAGE);
        privateMap.put("calorie_add", CALORIE_ADD_MESSAGE);
        privateMap.put("calorie_minus", CALORIE_MINUS_MESSAGE);
        privateMap.put("exercise_add", EXERCISE_ADD_MESSAGE);
        privateMap.put("exercise_delete", EXERCISE_DELETE_MESSAGE);
        privateMap.put("exercise_edit", EXERCISE_EDIT_MESSAGE);
        privateMap.put("exercise_find", EXERCISE_FIND_MESSAGE);
        privateMap.put("exercise_list", EXERCISE_LIST_MESSAGE);
        privateMap.put("routine_create", ROUTINE_CREATE_MESSAGE);
        privateMap.put("routine_delete", ROUTINE_DELETE_MESSAGE);
        privateMap.put("routine_find", ROUTINE_FIND_MESSAGE);
        privateMap.put("routine_list", ROUTINE_LIST_MESSAGE);
        privateMap.put("routine_add_exercise", ROUTINE_ADD_EXERCISE_MESSAGE);
        privateMap.put("routine_delete_exercise", ROUTINE_DELETE_EXERCISE_MESSAGE);
        privateMap.put("routine_view_exercise", ROUTINE_VIEW_MESSAGE);
        privateMap.put("lesson_add", LESSON_ADD_MESSAGE);
        privateMap.put("lesson_delete", LESSON_DELETE_MESSAGE);
        privateMap.put("lesson_edit", LESSON_EDIT_MESSAGE);
        privateMap.put("lesson_find", LESSON_FIND_MESSAGE);
        privateMap.put("lesson_list", LESSON_LIST_MESSAGE);
        privateMap.put("timetable_add_routine", TIMETABLE_ADD_ROUTINE_MESSAGE);
        privateMap.put("timetable_add_lesson", TIMETABLE_ADD_LESSON_MESSAGE);
        privateMap.put("timetable_delete_slot", TIMETABLE_DELETE_SLOT_MESSAGE);
    }

    /**
     * Creates a set out of the key elements contained in the private HashMap
     * @return a set having the keys of the HashMap
     */
    private Set<String> keySet() {
        return privateMap.keySet();
    }

    /**
     * Gives a String of usage instructions for all commands containing the keyword as a char sequence.
     * @param keyword is the char sequence
     * @return String that shows all commands containing the keyword
     */
    private String printIfCharSequence(String keyword) {
        requireNonNull(privateSet);
        String result = "";

        for (String command : privateSet) {
            if (!command.contains(keyword)) {
                continue;
            }
            result = result + privateMap.get(command);
        }
        return result;
    }

    private void setFilteredSearch() {
        if (keyword == null) {
            filteredSearch = STANDARDIZED_HELP_MESSAGE;
        } else {
            filteredSearch = printIfCharSequence(keyword);
        }

        if (filteredSearch.isEmpty()) {
            filteredSearch = NO_COMMAND_MESSAGE + STANDARDIZED_HELP_MESSAGE;
        }
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        setFilteredSearch();

        return new CommandResult(SHOWING_HELP_MESSAGE, true, false);
    }
}

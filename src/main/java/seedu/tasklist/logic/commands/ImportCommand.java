package seedu.tasklist.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.tasklist.logic.parser.CliSyntax.PREFIX_TIMETABLE_URL;

import seedu.tasklist.logic.commands.exceptions.CommandException;
import seedu.tasklist.model.Model;
import seedu.tasklist.timetable.TimetableData;

public class ImportCommand extends Command {

    public static final String COMMAND_WORD = "import";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Imports a NUSMods timetable to ProductiveNus. "
            + "Parameters: "
            + PREFIX_TIMETABLE_URL + "TIMETABLE URL "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TIMETABLE_URL + "https://nusmods.com/timetable/sem-1/share?CS2100=TUT:01,LAB:11,LEC:1 ";
    public static final String MESSAGE_SUCCESS = "Timetable imported.";

    private final TimetableData data;

    /**
     * Creates an ImportCommand to add the specified {@code Assignment}
     */
    public ImportCommand(TimetableData data) {
        requireNonNull(data);
        this.data = data;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.importTimetable(data);
        System.out.println(data);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}

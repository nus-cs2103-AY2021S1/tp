package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.task.Task;

import java.util.ArrayList;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;

public class LessonCommand extends Command {
    public static final String COMMAND_WORD = "lesson";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a lesson to PlaNus. "
            + "Parameters: "
            + PREFIX_TITLE + "TITLE "
            + PREFIX_DESCRIPTION + "DESCRIPTION "
            + PREFIX_DURATION + "DAY_TIME "
            + PREFIX_START_DATE + "DATE "
            + PREFIX_END_DATE + "DATE "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TITLE + "Lesson "
            + PREFIX_DESCRIPTION + "CS2103T Lecture "
            + PREFIX_DURATION + "MON 12:00-14:00 "
            + PREFIX_START_DATE + "01-01-2020 "
            + PREFIX_END_DATE + "01-05-2020 ";

    public static final String MESSAGE_SUCCESS = "New lesson added: %1$s";
    public static final String MESSAGE_DUPLICATE_TASK = "This lesson already exists in PlaNus.";

    private final Lesson lesson;

    /**
     * Creates an LessonCommand to add the specified {@code Task}
     */
    public LessonCommand(Lesson lesson) {
        requireNonNull(lesson);
        this.lesson = lesson;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        ArrayList<Task> tasksToAdd = lesson.createRecurringTasks();
        //some for loop
        for (Task taskToAdd: tasksToAdd) {
            if (model.hasTask(taskToAdd)) {
                throw new CommandException(MESSAGE_DUPLICATE_TASK);
            }
            model.addTask(taskToAdd);
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, lesson));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && lesson.equals(((LessonCommand) other).lesson));
    }
}

package team.serenity.logic.commands.studentinfo;

import static java.util.Objects.requireNonNull;
import static team.serenity.logic.parser.CliSyntax.PREFIX_GRP;

import team.serenity.logic.commands.Command;
import team.serenity.logic.commands.CommandResult;
import team.serenity.logic.commands.exceptions.CommandException;
import team.serenity.model.Model;
import team.serenity.model.group.Group;
import team.serenity.model.group.GroupContainsKeywordPredicate;

/**
 * Marks the attendance of a class or a student in the class.
 */
public class ExportAttCommand extends Command {

    public static final String COMMAND_WORD = "exportatt";
    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Exports the attendance sheet of a specified tutorial group as a new XLSX file. "
        + "Parameters: "
        + PREFIX_GRP + "GROUP "
        + "Example: " + COMMAND_WORD + " "
        + PREFIX_GRP + "G04";

    public static final String MESSAGE_SUCCESS
        = "Attendance sheet of tutorial group %s has been exported as %s_attendance.xlsx";
    public static final String MESSAGE_GROUP_DOES_NOT_EXIST = "Specified Tutorial Group does not exist!";

    private final GroupContainsKeywordPredicate grpPredicate;

    /**
     * Creates an ExportAttCommand to add the specified {@code Group}.
     */
    public ExportAttCommand(GroupContainsKeywordPredicate target) {
        this.grpPredicate = target;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        model.updateFilteredGroupList(this.grpPredicate);

        if (model.getFilteredGroupList().isEmpty()) {
            throw new CommandException(MESSAGE_GROUP_DOES_NOT_EXIST);
        }

        Group trgtGrp = model.getFilteredGroupList().get(0);

        model.exportGroup(trgtGrp);
        return new CommandResult(String.format(MESSAGE_SUCCESS, trgtGrp.getGroupName().toString(), trgtGrp.getGroupName().toString()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof ExportAttCommand // instanceof handles nulls
            && this.grpPredicate.equals(((ExportAttCommand) other).grpPredicate));
    }

}
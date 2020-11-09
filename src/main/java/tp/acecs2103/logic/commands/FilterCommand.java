package tp.acecs2103.logic.commands;

import static java.util.Objects.requireNonNull;

import tp.acecs2103.commons.core.Messages;
import tp.acecs2103.logic.parser.exceptions.ParseException;
import tp.acecs2103.model.Model;
import tp.acecs2103.model.task.WeekNumber;

public class FilterCommand extends Command {
    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Filter the task list according to given criteria\n"
            + "Parameters: w/WEEK_NUMBER(optional)  "
            + " k/KEYWORD(done or pending) "
            + " l/DEADLINE_TYPE(official or customised)\n "
            + "Example:\n filter k/done \n"
            + "filter w/1 k/done\n"
            + "filter k/pending l/official\n"
            + "filter w/11 k/pending l/official";

    public static final String MESSAGE_SUCCESS = "Filtered task list displayed according to given criteria: ";

    private final String keyword;
    private final String ddlType; // optional, only for pending tasks
    private final WeekNumber weekNumber; //optional

    /**
     * Creates an FilterCommand to display tasks that fulfill given criteria.
     * Only for display of completed tasks
     */
    public FilterCommand(String keyword) throws ParseException {
        requireNonNull(keyword);

        if (!keyword.equals("done")) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    FilterCommand.MESSAGE_USAGE));
        }
        this.keyword = keyword;
        this.ddlType = null;
        this.weekNumber = null;
    }

    /**
     * Creates an FilterCommand to display tasks that fulfill given criteria.
     * For display of completed tasks when week number is given
     */
    public FilterCommand(String keyword, WeekNumber weekNumber) throws ParseException {
        requireNonNull(keyword);
        if (!keyword.equals("done")) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    FilterCommand.MESSAGE_USAGE));
        }
        this.keyword = keyword;
        this.ddlType = null;
        this.weekNumber = weekNumber;
    }

    /**
     * Creates an FilterCommand to display tasks that fulfill given criteria.
     * For pending tasks with ddl type specified.
     */
    public FilterCommand(String keyword, String ddlType) throws ParseException {
        requireNonNull(keyword);
        if (!keyword.equals("pending") || (!ddlType.equals("official")
                && !ddlType.equals("customised"))) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    FilterCommand.MESSAGE_USAGE));
        }
        this.keyword = keyword;
        this.ddlType = ddlType;
        this.weekNumber = null;
    }

    /**
     * Creates an FilterCommand to display tasks that fulfill given criteria.
     * For pending tasks with ddl type specified and week number given.
     */
    public FilterCommand(String keyword, String ddlType, WeekNumber weekNumber) throws ParseException {
        requireNonNull(keyword);
        if (!keyword.equals("pending") || (!ddlType.equals("official")
                && !ddlType.equals("customised"))) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    FilterCommand.MESSAGE_USAGE));
        }
        this.keyword = keyword;
        this.ddlType = ddlType;
        this.weekNumber = weekNumber;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        boolean isDone = (keyword.equals("done"));
        boolean byOfficialDdl = (ddlType == null) ? false : ddlType.equals("official");


        model.filterTasks(isDone, byOfficialDdl, weekNumber);
        model.getUiTaskList();
        String returnMessage = MESSAGE_SUCCESS + "\nStatus: " + keyword;
        if (ddlType != null) {
            returnMessage += ("\nBy: " + ddlType + " deadline");
        }

        if (weekNumber != null) {
            returnMessage += "\nDuration: week " + weekNumber.value;
        }
        returnMessage += "\n-->" + String.format(Messages.MESSAGE_TASKS_LISTED_OVERVIEW, model.getUiTaskList().size());

        return new CommandResult(returnMessage);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof FilterCommand)) {
            return false;
        }
        FilterCommand otherObj = (FilterCommand) other;
        if (this.ddlType == null) {
            if (otherObj.ddlType != null) {
                return false;
            } else {
                if (this.weekNumber == null) {
                    if (otherObj.weekNumber != null) {
                        return false;
                    } else {
                        return keyword.equals(((FilterCommand) other).keyword);
                    }
                } else {
                    return keyword.equals(((FilterCommand) other).keyword)
                            && weekNumber.equals(((FilterCommand) other).weekNumber);
                }
            }
        } else if (this.weekNumber == null) {
            if (otherObj.weekNumber != null) {
                return false;
            } else {
                return keyword.equals(((FilterCommand) other).keyword)
                        && ddlType.equals(((FilterCommand) other).ddlType);
            }
        } else {
            return keyword.equals(((FilterCommand) other).keyword)
                    && ddlType.equals(((FilterCommand) other).ddlType)
                    && weekNumber.equals(((FilterCommand) other).weekNumber);
        }
    }
}

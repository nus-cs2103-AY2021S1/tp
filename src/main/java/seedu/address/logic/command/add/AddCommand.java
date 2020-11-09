package seedu.address.logic.command.add;

import seedu.address.logic.command.Command;

public abstract class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = AddTravelPlanCommand.MESSAGE_FORMAT + "\n"
            + AddActivityCommand.MESSAGE_FORMAT + "\n"
            + AddAccommodationCommand.MESSAGE_FORMAT + "\n"
            + AddFriendCommand.MESSAGE_FORMAT;
}

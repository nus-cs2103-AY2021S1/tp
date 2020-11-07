package com.eva.logic.commands;

import static com.eva.commons.core.PanelState.STAFF_LIST;
import static com.eva.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static com.eva.logic.parser.CliSyntax.PREFIX_COMMENT;
import static com.eva.logic.parser.CliSyntax.PREFIX_EMAIL;
import static com.eva.logic.parser.CliSyntax.PREFIX_NAME;
import static com.eva.logic.parser.CliSyntax.PREFIX_PHONE;
import static com.eva.logic.parser.CliSyntax.PREFIX_TAG;
import static java.util.Objects.requireNonNull;

import com.eva.logic.commands.exceptions.CommandException;
import com.eva.model.Model;
import com.eva.model.person.staff.Staff;


public class AddStaffCommand extends Command {
    public static final String COMMAND_WORD = "adds";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a staff to Eva. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + "[" + PREFIX_TAG + "TAG]"
            + "[" + PREFIX_COMMENT + "COMMENT]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_TAG + "business dev "
            + PREFIX_TAG + "performing well "
            + PREFIX_COMMENT + "ti/ Leave Request d/12/02/2020 desc: pending";
    public static final String MESSAGE_SUCCESS = "New staff added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This staff already exists in the eva database";

    private final Staff toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Staff}
     */
    public AddStaffCommand(Staff staff) {
        requireNonNull(staff);
        toAdd = staff;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasStaff(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.addStaff(toAdd);
        model.setPanelState(STAFF_LIST);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd), false, false, true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddStaffCommand // instanceof handles nulls
                && toAdd.equals(((AddStaffCommand) other).toAdd));
    }
}

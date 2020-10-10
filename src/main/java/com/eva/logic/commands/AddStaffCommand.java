package com.eva.logic.commands;

import static com.eva.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static com.eva.logic.parser.CliSyntax.PREFIX_EMAIL;
import static com.eva.logic.parser.CliSyntax.PREFIX_NAME;
import static com.eva.logic.parser.CliSyntax.PREFIX_PHONE;
import static com.eva.logic.parser.CliSyntax.PREFIX_TAG;
import static java.util.Objects.requireNonNull;

import com.eva.logic.commands.exceptions.CommandException;
import com.eva.model.Model;
import com.eva.model.person.staff.Staff;


public class AddStaffCommand extends Command {
    public static final String COMMAND_WORD = "addstaff";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a staff to Eva. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_TAG + "friends "
            + PREFIX_TAG + "owesMoney";
    public static final String MESSAGE_SUCCESS = "New staff added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This staff already exists in the address book";

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

        if (model.hasPerson(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.addPerson(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }
}

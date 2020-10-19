package com.eva.logic.commands;

import com.eva.logic.commands.exceptions.CommandException;
import com.eva.model.Model;
import com.eva.model.person.applicant.Applicant;
import com.eva.model.person.staff.Staff;

import static com.eva.logic.parser.CliSyntax.*;
import static java.util.Objects.requireNonNull;

public class AddApplicantCommand extends Command {
    public static final String COMMAND_WORD = "addapplicant";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a applicant to Eva. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "[" + PREFIX_COMMENT + "COMMENT]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_TAG + "role:business dev"
            + PREFIX_COMMENT + "t: Good applicant d: {Date} desc: has knowledge on business model frameworks";
    public static final String MESSAGE_SUCCESS = "New applicant added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This applicant already exists in the eva database";

    private final Applicant toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Staff}
     */
    public AddApplicantCommand(Applicant applicant) {
        requireNonNull(applicant);
        toAdd = applicant;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasApplicant(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.addApplicant(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }
}

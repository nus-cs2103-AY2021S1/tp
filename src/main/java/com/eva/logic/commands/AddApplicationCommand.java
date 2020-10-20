package com.eva.logic.commands;

import static java.util.Objects.requireNonNull;

import com.eva.logic.commands.exceptions.CommandException;
import com.eva.model.Model;
import com.eva.model.person.applicant.application.Application;

public class AddApplicationCommand extends Command {
    public static final String COMMAND_WORD = "addapplication";

    public static final String MESSAGE_USAGE =  COMMAND_WORD + " Adds an application to an applicant. "
            + "Parameters: "
            + "FILEPATH "
            + "Example: " + COMMAND_WORD + " "
            + "C://Users...";

    public static final String MESSAGE_SUCCESS = "Application added to applicant: %1$s ";
    public static final String MESSAGE_OVERRIDE = "Application overridden for applicant.";

    private final Application toAdd;

    public AddApplicationCommand(Application application) {
        requireNonNull(application);
        toAdd = application;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        // if (model.hasApplication(toAdd)) // MESSAGE_OVERRIDE

        model.addApplication(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }
}

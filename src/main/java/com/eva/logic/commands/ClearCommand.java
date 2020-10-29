package com.eva.logic.commands;

import static java.util.Objects.requireNonNull;

import com.eva.model.EvaDatabase;
import com.eva.model.Model;

/**
 * Clears the eva database.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "eva database has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setApplicantDatabase(new EvaDatabase<>());
        model.setStaffDatabase(new EvaDatabase<>());
        model.setPersonDatabase(new EvaDatabase<>());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}

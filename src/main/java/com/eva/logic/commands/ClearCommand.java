package com.eva.logic.commands;

import static com.eva.commons.core.PanelState.*;
import static com.eva.logic.parser.CliSyntax.PREFIX_APPLICANT;
import static com.eva.logic.parser.CliSyntax.PREFIX_STAFF;
import static java.util.Objects.requireNonNull;

import com.eva.commons.core.Messages;
import com.eva.commons.core.PanelState;
import com.eva.logic.commands.exceptions.CommandException;
import com.eva.logic.parser.Prefix;
import com.eva.model.EvaDatabase;
import com.eva.model.Model;

/**
 * Clears the eva database.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "All %1$s has been cleared!";
    public static final String MESSAGE_USAGE = " ";
    private static final String MESSAGE_WRONG_PANEL = "Please switch to %1$s list with list %2$s.";
    private Prefix type;

    public ClearCommand(Prefix type) {
        this.type = type;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        String contentsCleared = "";
        PanelState panelState = model.getPanelState();
        if (panelState.equals(APPLICANT_PROFILE) || panelState.equals(STAFF_PROFILE)) {
            throw new CommandException(String.format(Messages.MESSAGE_INVALID_COMMAND_AT_PANEL,
                    String.format(MESSAGE_WRONG_PANEL, type.equals(PREFIX_APPLICANT) ? "applicants" : "staff", type)));
        }
        if (type.equals(PREFIX_STAFF)) {
            contentsCleared = contentsCleared.concat("staff");
            if (panelState.equals(APPLICANT_LIST)) {
                throw new CommandException(String.format(Messages.MESSAGE_INVALID_COMMAND_AT_PANEL,
                        String.format(MESSAGE_WRONG_PANEL, contentsCleared, type)));
            }
            model.setStaffDatabase(new EvaDatabase<>());

        } else if (type.equals(PREFIX_APPLICANT)) {
            contentsCleared = contentsCleared.concat("applicants");
            if (panelState.equals(STAFF_LIST)) {
                throw new CommandException(String.format(Messages.MESSAGE_INVALID_COMMAND_AT_PANEL,
                        String.format(MESSAGE_WRONG_PANEL, contentsCleared, type)));
            }
            model.setApplicantDatabase(new EvaDatabase<>());

        } else {
            assert false;
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, contentsCleared));
    }
}

package team.baymax.logic.commands.general;

import static java.util.Objects.requireNonNull;

import team.baymax.logic.commands.Command;
import team.baymax.logic.commands.CommandResult;
import team.baymax.model.Model;
import team.baymax.model.modelmanagers.AppointmentManager;
import team.baymax.model.modelmanagers.PatientManager;
import team.baymax.model.util.TabId;

/**
 * Clears the patient manager and appointment manager.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Patients and appointments have been cleared!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setPatientManager(new PatientManager());
        model.setAppointmentManager(new AppointmentManager());
        return new CommandResult(MESSAGE_SUCCESS, TabId.DASHBOARD);
    }

}

package seedu.address.logic;

import static java.util.Objects.requireNonNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.CliniCalParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyCliniCal;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.Patient;
import seedu.address.model.patient.Phone;
import seedu.address.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final CliniCalParser cliniCalParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        cliniCalParser = new CliniCalParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        //Logging, safe to ignore
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        //Parse user input from String to a Command
        Command command = cliniCalParser.parseCommand(commandText);
        //Executes the Command and stores the result
        commandResult = command.execute(model);

        try {
            //We can deduce that the previous line of code modifies model in some way
            // since it's being stored here.
            storage.saveCliniCal(model.getCliniCal());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public CommandResult runImageTransfer(Patient patient, File profilePic) throws CommandException,
                                                                                   IllegalValueException {
        requireNonNull(patient);
        requireNonNull(profilePic);
        assert patient != null || profilePic != null : "Patient and profile picture cannot be null";
        int patientIndex = 1;
        ObservableList<Patient> listOfPatients = model.getFilteredPatientList();
        for (Patient thisPatient: listOfPatients) {
            Name thisPatientName = thisPatient.getName();
            Name selectedPatientName = patient.getName();
            Phone thisPatientPhone = thisPatient.getPhone();
            Phone selectedPatientPhone = patient.getPhone();
            if (thisPatientName.equals(selectedPatientName) && thisPatientPhone.equals(selectedPatientPhone)) {
                break;
            } else {
                patientIndex++;
            }
        }
        String filePath = profilePic.getPath();
        String commandToRun = "addpicture " + patientIndex + " f/" + filePath;
        logger.info("----------------[USER COMMAND][" + commandToRun + "]");

        CommandResult commandResult = execute(commandToRun);
        return commandResult;
    }

    @Override
    public ReadOnlyCliniCal getCliniCal() {
        return model.getCliniCal();
    }

    @Override
    public ObservableList<Patient> getFilteredPatientList() {
        return model.getFilteredPatientList();
    }

    @Override
    public ObservableList<Appointment> getFilteredAppointmentList() {
        return model.getFilteredAppointmentList();
    }

    @Override
    public Path getCliniCalFilePath() {
        return model.getCliniCalFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }
}

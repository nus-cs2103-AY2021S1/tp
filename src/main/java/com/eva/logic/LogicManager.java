package com.eva.logic;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import com.eva.commons.core.GuiSettings;
import com.eva.commons.core.LogsCenter;
import com.eva.logic.commands.Command;
import com.eva.logic.commands.CommandResult;
import com.eva.logic.commands.exceptions.CommandException;
import com.eva.logic.parser.AddressBookParser;
import com.eva.logic.parser.exceptions.ParseException;
import com.eva.model.Model;
import com.eva.model.ReadOnlyEvaDatabase;
import com.eva.model.person.Person;
import com.eva.model.person.applicant.Applicant;
import com.eva.model.person.staff.Staff;
import com.eva.storage.Storage;

import javafx.collections.ObservableList;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final AddressBookParser addressBookParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        addressBookParser = new AddressBookParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException, FileNotFoundException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = addressBookParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.savePersonDatabase(model.getPersonDatabase());
            storage.saveStaffDatabase(model.getStaffDatabase());
            storage.saveApplicantDatabase(model.getApplicantDatabase());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyEvaDatabase<Person> getEvaDatabase() {
        return model.getPersonDatabase();
    }

    @Override
    public ReadOnlyEvaDatabase<Staff> getStaffDataBase() {
        return model.getStaffDatabase();
    }


    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return model.getFilteredPersonList();
    }

    @Override
    public ObservableList<Staff> getFilteredStaffList() {
        return model.getFilteredStaffList();
    }

    @Override
    public ObservableList<Applicant> getFilteredApplicantList() {
        return model.getFilteredApplicantList();
    }

    @Override
    public Path getEvaDatabaseFilePath() {
        return model.getPersonDatabaseFilePath();
    }

    @Override
    public Path getStaffDatabaseFilePath() {
        return model.getStaffDatabaseFilePath();
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

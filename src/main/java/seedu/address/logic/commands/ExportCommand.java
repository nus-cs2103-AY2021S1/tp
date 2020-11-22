package seedu.address.logic.commands;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Exports the email of all existing residents.
 */
public class ExportCommand extends Command {

    public static final String COMMAND_WORD = "export";
    public static final String MESSAGE_EMAIL_SUCCESS = "List of emails exported";
    public static final String MESSAGE_PHONE_SUCCESS = "List of phone numbers exported";
    public static final String MESSAGE_USAGE = "export email: Exports the emails of the"
            + " current residents list to a text file\n";
    public static final String FILENAME = "./data/hally.txt";
    public static final String DIRECTORY_NAME = "./data/";
    private final String extractType;

    /**
     * Creates an ExportCommand to handle the specified information
     */
    public ExportCommand(String type) {
        extractType = type;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        String message;
        switch (extractType) {
        case "email":
            handleEmail(model.getFilteredPersonList());
            message = MESSAGE_EMAIL_SUCCESS;
            break;
        case "phone":
            handlePhone(model.getFilteredPersonList());
            message = MESSAGE_PHONE_SUCCESS;
            break;
        default:
            throw new CommandException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
        }

        return new CommandResult(message);
    }

    //@@author yanchenglee98-reused
    //Solution below reused from https://github.com/yanchenglee98/ip/blob/master/src/main/java/duke/Storage.java
    /**
     * Writes the email of all residents into a txt file
     * @param personList list of residents the command should operate on.
     * @throws CommandException if file IO is interrupted
     */
    private void handleEmail(ObservableList<Person> personList) throws CommandException {
        createDir();

        try {
            FileWriter fw = new FileWriter(FILENAME);

            for (Person person : personList) {
                fw.write(person.getEmail().toString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            throw new CommandException("File is missing");
        }
    }

    //@@author yanchenglee98-reused
    //Solution below reused from https://github.com/yanchenglee98/ip/blob/master/src/main/java/duke/Storage.java
    /**
     * Writes the phone numbers of all residents into a txt file
     * @param personList list of residents the command should operate on.
     * @throws CommandException If file IO is interrupted
     */
    private void handlePhone(ObservableList<Person> personList) throws CommandException {
        createDir();

        try {
            FileWriter fw = new FileWriter(FILENAME);

            for (Person person : personList) {
                fw.write(person.getPhone().toString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            throw new CommandException("File is missing");
        }
    }

    //@@author yanchenglee98-reused
    //Solution below reused from https://github.com/yanchenglee98/ip/blob/master/src/main/java/duke/Storage.java
    /**
     * Creates a directory at ./data/.
     */
    private void createDir() {
        File directory = new File(DIRECTORY_NAME);

        // checks if directory exists, else directory will be created
        if (!directory.exists()) {
            directory.mkdir();
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ExportCommand // instanceof handles nulls
                && extractType.equals(((ExportCommand) other).extractType)); // state check
    }
}


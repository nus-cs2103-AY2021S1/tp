package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.LogicManager.FILE_OPS_ERROR_MESSAGE;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.storage.AddressBookStorage;
import seedu.address.storage.JsonAddressBookStorage;


/**
 * Archives all current data of the students in a separate file at /data.
 */
public class ArchiveCommand extends Command {
    public static final String COMMAND_WORD = "archive";

    public static final String MESSAGE_SUCCESS = "Archived all data!";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        // get current date & time
        LocalDateTime time = LocalDateTime.now();
        LocalDate currentDate = time.toLocalDate();
        String currentTime = time.format(DateTimeFormatter.ofPattern("HHmm"));
        // archive file path
        Path filePath = Paths.get(String.format("data/tasker_%s_%s.json", currentDate, currentTime));
        // initialise new TAsker storage
        AddressBookStorage addressBookStorage = new JsonAddressBookStorage(filePath);
        ReadOnlyAddressBook addressBookToArchive = model.getAddressBook();
        assert (addressBookToArchive != null);
        try {
            addressBookStorage.saveAddressBook(addressBookToArchive, filePath);
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }
        return new CommandResult(MESSAGE_SUCCESS);
    }
}



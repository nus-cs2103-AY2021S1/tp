package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;


public class ArchiveAllCommand extends Command {

    public static final String COMMAND_WORD = "c-archive-all";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Archives all employees in the displayed Employee Directory.\n";

    public static final String MESSAGE_ARCHIVE_PERSON_SUCCESS = "Archived all employees in the displayed"
            + " Employee Directory ";

    public static final String MESSAGE_NOTHING_TO_ARCHIVE = "There is no employee in the current displayed Employee"
            + "Directory";

    @Override
    public CommandResult execute(Model model) throws CommandException {

        requireNonNull(model);

        List<Person> lastShownList = model.getFilteredPersonList();


        int listSize = lastShownList.size();
        if (listSize == 0) {
            throw new CommandException(String.format(MESSAGE_NOTHING_TO_ARCHIVE));
        }
        for (int i = 0; i < listSize; i++) {
            Person personToArchive = lastShownList.get(0);
            Person archivedPerson = personToArchive.archive();
            model.setPerson(personToArchive, archivedPerson);
        }
        model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_ACTIVE_PERSONS);
        return new CommandResult(MESSAGE_ARCHIVE_PERSON_SUCCESS);
    }
}

package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.module.ModuleName;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonPredicate;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "contact delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by the name used in the displayed person list.\n"
            + "Parameters: "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_MODULE + "MODULE] "
            + "[" + PREFIX_TAG + "TAG]\n"
            + "Example: " + COMMAND_WORD + " n/Roy t/friend";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted People: %1$s";

    private final PersonPredicate predicate;
    private final List<ModuleName> moduleNames;

    /**
     * @param predicate the predicate based on the names, modules and tags given
     */
    public DeleteCommand(PersonPredicate predicate, List<ModuleName> moduleNames) {
        this.predicate = predicate;
        this.moduleNames = moduleNames;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> people;
        if (moduleNames.isEmpty()) {
            // finds Persons that match the predicate only
            people = model.getUpdatedFilteredPersonList(predicate);
        } else {
            // finds Persons that match the predicate and Persons that have the given Modules
            people = model.getUpdatedFilteredPersonList(predicate, moduleNames);
        }

        // Update address book
        people.stream().forEach(
                p -> {
                    List<Person> filteredList = model.getFilteredPersonList().stream()
                            .filter(person -> person.isSameName(p.getName())).collect(Collectors.toList());
                    assert filteredList.size() == 1;
                    Person personToDelete = filteredList.get(0);
                    model.deletePerson(personToDelete);
                    List<Meeting> filteredMeetingList = model.getFilteredMeetingList().stream()
                            .filter(meeting -> meeting.getParticipants().contains(personToDelete)).map(meeting -> {
                                Set<Person> updatedMembers = new HashSet<>(meeting.getParticipants());
                                updatedMembers.remove(personToDelete);
                                Meeting updatedMeeting = new Meeting(meeting.getModule(), meeting.getMeetingName(),
                                        meeting.getDate(), meeting.getTime(), updatedMembers);
                                model.setMeeting(meeting, updatedMeeting);
                                return updatedMeeting;
                            }).collect(Collectors.toList());
                    model.updatePersonInMeetingBook(personToDelete);
                }
        );

        String deletedNames = people.stream()
                .map(p -> p.getName().toString())
                .reduce("", (x, y) -> x + y + " ");

        // todo update module book
        //        List<Meeting> filteredModuleList = model.getFilteredModuleList().stream()
        //                .filter(module -> module.getClassmates().contains(personToDelete)).map(module -> {
        //                    Set<Person> updatedClassmates = new HashSet<>(module.getClassmates());
        //                    updatedClassmates.remove(personToDelete);
        //                    Module updatedModule = new Module(module.getModuleName(), updatedClassmates);
        //                    model.setModule(module, updatedModule);
        //                    return updatedModule;
        //                }).collect(Collectors.toList());

        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, deletedNames));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && predicate.equals(((DeleteCommand) other).predicate)
                && moduleNames.equals(((DeleteCommand) other).moduleNames)); // state check
    }
}

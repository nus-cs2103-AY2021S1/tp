package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.commons.SpecialName;
import seedu.address.model.meeting.*;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

/**
 * Adds a meeting to the meeting book.
 */
public class AddMeetingCommand extends Command {

    public static final String COMMAND_WORD = "meeting add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a meeting to the meeting book. "
            + "Parameters: "
            + PREFIX_NAME + "MEETING NAME "
            + PREFIX_DATE + "DATE "
            + PREFIX_TIME + "TIME "
            + "[" + PREFIX_MEMBER + "MEMBERS]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "CS2103 weekly meeting "
            + PREFIX_DATE + "2020-09-20 "
            + PREFIX_TIME + "10:00 "
            + PREFIX_MEMBER + "Alex "
            + PREFIX_MEMBER + "Roy"
            + PREFIX_AGENDA + "Discuss project direction"
            + PREFIX_NOTE + "Alex will be coming late";

    public static final String MESSAGE_SUCCESS = "New meeting added: %1$s";
    public static final String MESSAGE_DUPLICATE_MEETING = "This meeting already exists in the meeting book";
    public static final String MESSAGE_NONEXISTENT_PERSON = "The following person(s): %s are not in your contacts";

    private final MeetingName meetingName;
    private final Date date;
    private final Time time;
    private final Set<Name> nameList;
    private final Set<SpecialName> agendaList;
    private final Set<SpecialName> noteList;


    /**
     * Creates an AddMeetingCommand to add a meeting with specified params
     */
    public AddMeetingCommand(MeetingName meetingName, Date date, Time time, Set<Name> nameList,
                             Set<SpecialName> agendaList, Set<SpecialName> noteList) {
        requireAllNonNull(meetingName, date, time, nameList);
        this.meetingName = meetingName;
        this.date = date;
        this.time = time;
        this.nameList = nameList;
        this.agendaList = agendaList;
        this.noteList = noteList;
    }

    /**
     * Creates an AddMeetingCommand to add the specified {@code Meeting}
     * For Testing.
     */
    public AddMeetingCommand(Meeting toAdd) {
        requireAllNonNull(toAdd);
        this.meetingName = toAdd.getMeetingName();
        this.date = toAdd.getDate();
        this.time = toAdd.getTime();
        this.nameList = toAdd.getMembers().stream().map(person -> person.getName()).collect(Collectors.toSet());
        this.agendaList = toAdd.getAgendas();
        this.noteList = toAdd.getNotes();
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasMeetingName(meetingName)) {
            throw new CommandException(MESSAGE_DUPLICATE_MEETING);
        }

        List<Name> nonExistentPersonNames = new ArrayList<>();
        for (Name name : nameList) {
            if (!model.hasPersonName(name)) {
                nonExistentPersonNames.add(name);
            }
        }

        if (!nonExistentPersonNames.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (Name name : nonExistentPersonNames) {
                sb.append(name + ", ");
            }
            String nonExistentPersonNamesString = sb.substring(0, sb.length() - 2);
            throw new CommandException(String.format(MESSAGE_NONEXISTENT_PERSON, nonExistentPersonNamesString));
        }

        Set<Person> personSet = new HashSet<>();
        for (Name name : nameList) {
            List<Person> filteredList = model.getFilteredPersonList().stream()
                    .filter(person -> person.isSameName(name)).collect(Collectors.toList());
            personSet.addAll(filteredList);
        }

        Meeting toAdd = new Meeting(meetingName, date, time, personSet, agendaList, noteList);

        model.addMeeting(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddMeetingCommand // instanceof handles nulls
                && meetingName.equals(((AddMeetingCommand) other).meetingName)
                && date.equals(((AddMeetingCommand) other).date)
                && time.equals(((AddMeetingCommand) other).time)
                && nameList.equals(((AddMeetingCommand) other).nameList));
    }
}


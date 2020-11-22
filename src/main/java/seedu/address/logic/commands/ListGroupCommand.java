package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javafx.collections.ObservableList;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.studentgroup.StudentGroup;

/**
 * Lists all student groups of residents.
 */
public class ListGroupCommand extends Command {

    public static final String COMMAND_WORD = "list-group";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all student groups.\n"
        + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Listed all student groups";

    private static final Logger logger = Logger.getLogger(ListGroupCommand.class.getName());


    @Override
    public CommandResult execute(Model model) {
        assert model != null : "Model is null";
        requireNonNull(model);

        // retrieve a list of all residents
        ObservableList<Person> residentList = model.getPersonList();
        logger.log(Level.INFO, "The number of residents is " + residentList.size());

        // use Set to avoid storing duplicate elements
        Set<String> groupNameSet = new HashSet<>();
        for (Person resident : residentList) {
            Set<StudentGroup> studentGroupSet = resident.getStudentGroups();
            for (StudentGroup studentGroup : studentGroupSet) {
                groupNameSet.add(studentGroup.studentGroupName);
            }
        }
        logger.log(Level.INFO, "The number of student groups is " + groupNameSet.size());

        // sort the student groups alphabetically
        List<String> sortedGroupNameList = groupNameSet.stream().sorted().collect(Collectors.toList());
        // add number prefix for every student group
        String groupNamesWithNumber = IntStream.range(0, sortedGroupNameList.size()).boxed()
            .reduce("", (groupNames, index) ->
                groupNames.concat("\n").concat(String.valueOf(index + 1))
                    .concat(". ").concat(sortedGroupNameList.get(index)), String::concat);
        String feedbackToUser = MESSAGE_SUCCESS.concat(groupNamesWithNumber);
        return new CommandResult(feedbackToUser);
    }
}

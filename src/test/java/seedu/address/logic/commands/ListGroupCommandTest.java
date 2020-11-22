package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalPersons.getTypicalStudentGroups;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListGroupCommand.
 */
public class ListGroupCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_listGroup_showsAllStudentGroups() {
        List<String> groupList = getTypicalStudentGroups();
        String expectedGroupMessage = formatStudentGroupsWithNumber(groupList);
        String expectedCommandMessage = ListGroupCommand.MESSAGE_SUCCESS.concat(expectedGroupMessage);
        assertCommandSuccess(new ListGroupCommand(), model, expectedCommandMessage, expectedModel);
    }

    private static String formatStudentGroupsWithNumber(List<String> groupNameList) {
        return IntStream.range(0, groupNameList.size()).boxed()
            .reduce("", (groupNames, index) ->
                groupNames.concat("\n").concat(String.valueOf(index + 1))
                    .concat(". ").concat(groupNameList.get(index)), String::concat);
    }
}

package seedu.address.logic.parser;

import static seedu.address.logic.commands.TeammateTestUtil.VALID_TEAMMATE_GIT_USERNAME_A;
import static seedu.address.logic.commands.TeammateTestUtil.VALID_TEAMMATE_NAME_A;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.DESC_A;
import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.project.NewTeammateCommand;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class NewTeammateCommandParsertest {
    private NewTeammateCommandParser parser = new NewTeammateCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Person expectedPerson = DESC_A.build();
        final NewTeammateCommand teammateCommand = new NewTeammateCommand(
            VALID_TEAMMATE_NAME_A,
            VALID_TEAMMATE_GIT_USERNAME_A,
            )




    }

}

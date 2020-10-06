package jimmy.mcgymmy.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import jimmy.mcgymmy.commons.core.Messages;
import jimmy.mcgymmy.logic.parser.CommandParserTestUtil;
import jimmy.mcgymmy.model.Model;
import jimmy.mcgymmy.model.ModelManager;
import jimmy.mcgymmy.model.UserPrefs;
import jimmy.mcgymmy.model.person.NameContainsKeywordsPredicate;
import jimmy.mcgymmy.testutil.TypicalPersons;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(TypicalPersons.getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(TypicalPersons.getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        String expectedMessage = String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        NameContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindCommand command = new FindCommand();
        command.setParameters(new CommandParserTestUtil.ParameterStub<>("", predicate));
        expectedModel.updateFilteredPersonList(predicate);
        CommandTestUtil.assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        NameContainsKeywordsPredicate predicate = preparePredicate("Kurz Elle Kunz");
        FindCommand command = new FindCommand();
        command.setParameters(new CommandParserTestUtil.ParameterStub<>("", predicate));
        expectedModel.updateFilteredPersonList(predicate);
        CommandTestUtil.assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(TypicalPersons.CARL, TypicalPersons.ELLE, TypicalPersons.FIONA),
                model.getFilteredPersonList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}

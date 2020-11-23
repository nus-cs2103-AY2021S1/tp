package seedu.address.logic.commands.deck;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.entry.TypicalEntries.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.deck.Deck;
import seedu.address.testutil.deck.DeckBuilder;

/**
 * Contains integration tests (interaction with the Deck) for {@code NewDeckCommand}.
 */
public class NewDeckCommandIntegrationTest {
    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newDeck_success() {
        Deck validDeck = new DeckBuilder().build();

        Model expectedModel = new ModelManager(model.getWordBank(), new UserPrefs());
        expectedModel.addDeck(validDeck);

        assertCommandSuccess(new NewDeckCommand(validDeck), model,
                String.format(NewDeckCommand.MESSAGE_SUCCESS, validDeck), expectedModel);
    }

    @Test
    public void execute_duplicateDeck_throwsCommandException() {
        Deck validDeck = new DeckBuilder().build();
        model.addDeck(validDeck);
        Deck deckInList = model.getWordBank().getDeckList().get(0);
        assertCommandFailure(new NewDeckCommand(deckInList), model, NewDeckCommand.MESSAGE_DUPLICATE_DECK);
    }
}

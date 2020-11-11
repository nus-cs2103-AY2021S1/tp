package seedu.bookmark.logic.commands;

import static seedu.bookmark.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.bookmark.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.bookmark.testutil.TypicalBooks.getTypicalLibrary;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.bookmark.model.Model;
import seedu.bookmark.model.ModelManager;
import seedu.bookmark.model.UserPrefs;
import seedu.bookmark.model.book.Book;
import seedu.bookmark.testutil.BookBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalLibrary(), new UserPrefs());
    }

    @Test
    public void execute_newBook_success() {
        Book validBook = new BookBuilder().withName("TESTER BOOK").build();

        Model expectedModel = new ModelManager(model.getLibrary(), new UserPrefs());
        expectedModel.addBook(validBook);

        assertCommandSuccess(new AddCommand(validBook), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validBook), expectedModel);
    }

    @Test
    public void execute_duplicateBook_throwsCommandException() {
        Book bookInList = model.getLibrary().getBookList().get(0);
        assertCommandFailure(new AddCommand(bookInList), model, AddCommand.MESSAGE_DUPLICATE_BOOK);
    }

}

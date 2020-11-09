package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CATEGORY_MATH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CATEGORY_SCIENCE;
import static seedu.address.testutil.TypicalBooks.getTypicalLibrary;

import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.Library;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.book.Book;
import seedu.address.model.category.Category;



/**
 * Contains integration tests (interaction with the Model) for {@code RandomCommand}.
 */
public class RandomCommandTest {
    private Model model = new ModelManager(getTypicalLibrary(), new UserPrefs());

    @Test
    public void execute_uniqueBookFilteredList_success() {
        // every book in the model(Typical Library) is the unique one of its own category.
        Book book = model.getFilteredBookList().get(0);
        Set<Category> categories = book.getCategories();
        Category category = categories.iterator().next();
        RandomCommand randomCommand = new RandomCommand(category);
        // As the book is the only one of its category, it shall be selected after random
        randomCommand.execute(model);
        Model newModel = new ModelManager(new Library(model.getLibrary()), new UserPrefs());
        Book expectedBook = newModel.getFilteredBookList().get(0);
        // expectedBook shall be the same with the book selected after random Command
        assertEquals(book, expectedBook);
    }

    @Test
    public void equals() {
        final RandomCommand standardCommand = new RandomCommand(new Category(VALID_CATEGORY_MATH));

        // same values -> returns true
        RandomCommand commandWithSameValues = new RandomCommand(new Category(VALID_CATEGORY_MATH));
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> retuArns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different values -> return false
        RandomCommand commandWithDifferentValues = new RandomCommand(new Category(VALID_CATEGORY_SCIENCE));
        assertFalse(standardCommand.equals(commandWithDifferentValues));
    }
}

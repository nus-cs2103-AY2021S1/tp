package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CATEGORY_MATH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CATEGORY_SCIENCE;
import static seedu.address.testutil.TypicalBooks.getTypicalLibrary;

import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.book.Book;
import seedu.address.model.category.Category;
import seedu.address.testutil.BookBuilder;




/**
 * Contains integration tests (interaction with the Model) for {@code FindMostPopularCommand}.
 */
public class FindMostPopularCommandTest {
    private static final String LARGE_POPULAR_TIMES = String.valueOf(Integer.MAX_VALUE);
    private Model model = new ModelManager(getTypicalLibrary(), new UserPrefs());


    @Test
    public void execute_mostPopularBookFilteredList_success() {
        // create a new book with the maximum borrowed number of times
        Book book = new BookBuilder().withCategories(VALID_CATEGORY_MATH).withTimes(LARGE_POPULAR_TIMES).build();
        Set<Category> categories = book.getCategories();
        Category category = categories.iterator().next();
        model.addBook(book);
        FindMostPopularCommand findPopCommand = new FindMostPopularCommand(category);
        // As the book is the most popular one in the model, it shall be selected by FindMostPopularCommand
        findPopCommand.execute(model);
        Book expectedBook = model.getFilteredBookList().get(0);
        // expectedBook shall be the same with the most popular book after FindMostPopularCommand Command
        assertEquals(book, expectedBook);
    }

    @Test
    public void equals() {
        final FindMostPopularCommand standardCommand = new FindMostPopularCommand(new Category(VALID_CATEGORY_MATH));

        // same values -> returns true
        FindMostPopularCommand commandWithSameValues = new FindMostPopularCommand(new Category(VALID_CATEGORY_MATH));
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different values -> return false
        FindMostPopularCommand commandWithDifferentValues =
                new FindMostPopularCommand(new Category(VALID_CATEGORY_SCIENCE));
        assertFalse(standardCommand.equals(commandWithDifferentValues));
    }
}

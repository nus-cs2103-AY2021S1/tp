package seedu.bookmark.testutil;

import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_BOOKMARK;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_GENRE;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_TOTAL_PAGES;

import java.util.Set;

import seedu.bookmark.logic.commands.AddCommand;
import seedu.bookmark.logic.commands.EditCommand;
import seedu.bookmark.model.book.Book;
import seedu.bookmark.model.tag.Tag;



/**
 * A utility class for Book.
 */
public class BookUtil {

    /**
     * Returns an add command string for adding the {@code book}.
     */
    public static String getAddCommand(Book book) {
        return AddCommand.COMMAND_WORD + " " + getBookDetails(book);
    }

    /**
     * Returns the part of command string for the given {@code book}'s details.
     */
    public static String getBookDetails(Book book) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + book.getName().fullName + " ");
        sb.append(PREFIX_GENRE + book.getGenre().value + " ");
        sb.append(PREFIX_TOTAL_PAGES + book.getTotalPages().value + " ");
        sb.append(PREFIX_BOOKMARK + book.getBookmark().value + " ");
        book.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditBookDescriptor}'s details.
     */
    public static String getEditBookDescriptorDetails(EditCommand.EditBookDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getGenre().ifPresent(genre -> sb.append(PREFIX_GENRE).append(genre.value).append(" "));
        descriptor.getTotalPages().ifPresent(totalPages -> sb.append(PREFIX_TOTAL_PAGES)
                                                                   .append(totalPages.value).append(" "));
        descriptor.getBookmark().ifPresent(bookmark -> sb.append(PREFIX_BOOKMARK).append(bookmark.value).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }
}

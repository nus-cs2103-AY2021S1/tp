package seedu.bookmark.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.bookmark.logic.commands.EditCommand;
import seedu.bookmark.logic.commands.EditCommand.EditBookDescriptor;
import seedu.bookmark.model.book.Book;
import seedu.bookmark.model.book.Bookmark;
import seedu.bookmark.model.book.Genre;
import seedu.bookmark.model.book.Name;
import seedu.bookmark.model.book.TotalPages;
import seedu.bookmark.model.tag.Tag;

/**
 * A utility class to help with building EditBookDescriptor objects.
 */
public class EditBookDescriptorBuilder {

    private EditBookDescriptor descriptor;

    public EditBookDescriptorBuilder() {
        descriptor = new EditBookDescriptor();
    }

    public EditBookDescriptorBuilder(EditCommand.EditBookDescriptor descriptor) {
        this.descriptor = new EditBookDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditBookDescriptor} with fields containing {@code book}'s details
     */
    public EditBookDescriptorBuilder(Book book) {
        descriptor = new EditBookDescriptor();
        descriptor.setName(book.getName());
        descriptor.setGenre(book.getGenre());
        descriptor.setTags(book.getTags());
        descriptor.setTotalPages(book.getTotalPages());
        descriptor.setBookmark(book.getBookmark());
    }

    /**
     * Sets the {@code Name} of the {@code EditBookDescriptor} that we are building.
     */
    public EditBookDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Genre} of the {@code EditBookDescriptor} that we are building.
     */
    public EditBookDescriptorBuilder withGenre(String genre) {
        descriptor.setGenre(new Genre(genre));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditBookDescriptor}
     * that we are building.
     */
    public EditBookDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    /**
     * Sets the {@code TotalPages} of the {@code EditBookDescriptor} that we are building.
     */
    public EditBookDescriptorBuilder withTotalPages(String totalPages) {
        descriptor.setTotalPages(new TotalPages(totalPages));
        return this;
    }

    /**
     * Sets the {@code Bookmark} of the {@code EditBookDescriptor} that we are building.
     */
    public EditBookDescriptorBuilder withBookmark(String bookmark) {
        descriptor.setBookmark(new Bookmark(bookmark));
        return this;
    }

    public EditBookDescriptor build() {
        return descriptor;
    }
}

package seedu.expense.model.expense.exceptions;

import static seedu.expense.commons.core.Messages.MESSAGE_INVALID_CATEGORY;

import seedu.expense.model.tag.Tag;

/**
 * Signals that the operation is unable to find the specified category (internal non-user-visible exception)
 */
public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(Tag category) {
        super(String.format(MESSAGE_INVALID_CATEGORY, category));
    }

    public CategoryNotFoundException(String message) {
        super(message);
    }
}

package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_BOOK;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddReviewCommand;
import seedu.address.model.review.Rating;
import seedu.address.model.review.Review;
import seedu.address.model.review.ReviewContent;

class AddReviewCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, AddReviewCommand.MESSAGE_USAGE);

    private AddReviewCommandParser addReviewCommandParser = new AddReviewCommandParser();

    private final String validRating = " " + CliSyntax.PREFIX_RATING + "5";
    private final String previousValidRating = " " + CliSyntax.PREFIX_RATING + "4";
    private final String validReviewContent = " " + CliSyntax.PREFIX_REVIEW + "The book is interesting";
    private final String previousValidReviewContent = " " + CliSyntax.PREFIX_REVIEW + "The book is inspiring";
    private final String invalidRating = " " + CliSyntax.PREFIX_RATING + "6";
    private final String invalidReviewContent = " " + CliSyntax.PREFIX_REVIEW + "";

    @Test
    void parse_allFieldSpecified_success() {
        Index index = INDEX_FIRST_BOOK;
        String enteredCommand = index.getOneBased() + validRating + validReviewContent;

        Rating expectedRating = new Rating(5);
        ReviewContent expectedReviewContent = new ReviewContent("The book is interesting");
        AddReviewCommand expectedReviewCommand = new AddReviewCommand(index,
                new Review(expectedRating, expectedReviewContent));
        assertParseSuccess(addReviewCommandParser, enteredCommand, expectedReviewCommand);
    }

    @Test
    void parse_repeatedField_success() {
        Index index = INDEX_FIRST_BOOK;
        String enteredCommand = index.getOneBased() + previousValidRating + validRating + validReviewContent;

        Rating expectedRating = new Rating(5);
        ReviewContent expectedReviewContent = new ReviewContent("The book is interesting");
        AddReviewCommand expectedReviewCommand = new AddReviewCommand(index,
                new Review(expectedRating, expectedReviewContent));
        assertParseSuccess(addReviewCommandParser, enteredCommand, expectedReviewCommand);

        enteredCommand = index.getOneBased() + validRating + previousValidReviewContent + validReviewContent;
        assertParseSuccess(addReviewCommandParser, enteredCommand, expectedReviewCommand);
    }

    @Test
    void parse_validFieldAfterInvalidField_success() {
        Index index = INDEX_FIRST_BOOK;
        String enteredCommand = index.getOneBased() + invalidRating + validRating + validReviewContent;

        Rating expectedRating = new Rating(5);
        ReviewContent expectedReviewContent = new ReviewContent("The book is interesting");
        AddReviewCommand expectedReviewCommand = new AddReviewCommand(index,
                new Review(expectedRating, expectedReviewContent));
        assertParseSuccess(addReviewCommandParser, enteredCommand, expectedReviewCommand);

        enteredCommand = index.getOneBased() + validRating + invalidReviewContent + validReviewContent;
        assertParseSuccess(addReviewCommandParser, enteredCommand, expectedReviewCommand);
    }

    @Test
    void parse_invalidFieldAfterValidField_failure() {
        Index index = INDEX_FIRST_BOOK;
        String enteredCommand = index.getOneBased() + validRating + invalidRating + validReviewContent;

        assertParseFailure(addReviewCommandParser, enteredCommand, Rating.MESSAGE_CONSTRAINTS);

        enteredCommand = index.getOneBased() + validRating + validReviewContent + invalidReviewContent;
        assertParseFailure(addReviewCommandParser, enteredCommand, ReviewContent.MESSAGE_CONSTRAINTS);
    }

    @Test
    void parse_missingField_failure() {
        Index index = INDEX_FIRST_BOOK;
        String enteredCommand = validRating;

        assertParseFailure(addReviewCommandParser, enteredCommand, MESSAGE_INVALID_FORMAT);

        enteredCommand = validReviewContent;
        assertParseFailure(addReviewCommandParser, enteredCommand, MESSAGE_INVALID_FORMAT);

        enteredCommand = String.valueOf(index.getOneBased());
        assertParseFailure(addReviewCommandParser, enteredCommand, MESSAGE_INVALID_FORMAT);

        enteredCommand = index.getOneBased() + validRating;
        assertParseFailure(addReviewCommandParser, enteredCommand, MESSAGE_INVALID_FORMAT);

        enteredCommand = index.getOneBased() + validReviewContent;
        assertParseFailure(addReviewCommandParser, enteredCommand, MESSAGE_INVALID_FORMAT);

        enteredCommand = validRating + validReviewContent;
        assertParseFailure(addReviewCommandParser, enteredCommand, MESSAGE_INVALID_FORMAT);
    }

    @Test
    void parse_invalidField_failure() {
        Index index = INDEX_FIRST_BOOK;
        String enteredCommand = index.getOneBased() + invalidRating + validReviewContent;

        assertParseFailure(addReviewCommandParser, enteredCommand, Rating.MESSAGE_CONSTRAINTS);

        enteredCommand = index.getOneBased() + validRating + invalidReviewContent;
        assertParseFailure(addReviewCommandParser, enteredCommand, ReviewContent.MESSAGE_CONSTRAINTS);
    }
}

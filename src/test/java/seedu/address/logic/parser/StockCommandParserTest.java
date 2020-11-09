package seedu.address.logic.parser;

import static seedu.address.logic.commands.CommandTestUtil.INVALID_ISBN_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.ISBN_DESC_BOOK1;
import static seedu.address.logic.commands.CommandTestUtil.ISBN_DESC_BOOK2;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOOK1;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOOK2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ISBN_BOOK1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ISBN_BOOK2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOOK1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOOK2;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.StockCommand;
import seedu.address.model.book.Isbn;
import seedu.address.model.book.Name;

class StockCommandParserTest {

    private StockCommandParser stockCommandParser = new StockCommandParser();

    @Test
    void parse_validArgs_success() {
        assertParseSuccess(stockCommandParser,
                NAME_DESC_BOOK1,
                new StockCommand(Arrays.asList(VALID_NAME_BOOK1.split("\\s+")), null));

        assertParseSuccess(stockCommandParser,
                ISBN_DESC_BOOK1,
                new StockCommand(null, Arrays.asList(VALID_ISBN_BOOK1.split("\\s+"))));
    }

    @Test
    void parse_duplicateValidArgs_success() {
        assertParseSuccess(stockCommandParser,
                NAME_DESC_BOOK1 + NAME_DESC_BOOK2,
                new StockCommand(Arrays.asList(VALID_NAME_BOOK2.split("\\s+")), null));

        assertParseSuccess(stockCommandParser,
                ISBN_DESC_BOOK1 + ISBN_DESC_BOOK2,
                new StockCommand(null, Arrays.asList(VALID_ISBN_BOOK2.split("\\s+"))));
    }

    @Test
    void parse_validAfterInvalidArgs_success() {
        assertParseSuccess(stockCommandParser,
                INVALID_NAME_DESC + NAME_DESC_BOOK1,
                new StockCommand(Arrays.asList(VALID_NAME_BOOK1.split("\\s+")), null));

        assertParseSuccess(stockCommandParser,
                INVALID_ISBN_DESC + ISBN_DESC_BOOK1,
                new StockCommand(null, Arrays.asList(VALID_ISBN_BOOK1.split("\\s+"))));
    }

    @Test
    void parse_noArgs_success() {
        assertParseSuccess(stockCommandParser, "", new StockCommand(null, null));
    }

    @Test
    void parse_invalidAfterValidArgs_failure() {
        assertParseFailure(stockCommandParser, NAME_DESC_BOOK1 + INVALID_NAME_DESC,
                Name.MESSAGE_CONSTRAINTS);

        assertParseFailure(stockCommandParser, ISBN_DESC_BOOK1 + INVALID_ISBN_DESC,
                Isbn.MESSAGE_CONSTRAINTS);
    }

    @Test
    void parse_invalidArgs_failure() {
        assertParseFailure(stockCommandParser, INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS);

        assertParseFailure(stockCommandParser, INVALID_ISBN_DESC, Isbn.MESSAGE_CONSTRAINTS);
    }
}

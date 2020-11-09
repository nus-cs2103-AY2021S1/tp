package seedu.address.logic.parser.todolistparsers;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.todolistcommands.SortTaskCommand;
import seedu.address.model.task.Criterion;

public class SortTaskParserTest {

    private SortTaskParser parser = new SortTaskParser();

    @Test
    public void parse_validArgs_returnsSortTaskCommand() throws Exception {

        // for name, abbreviated, non-reversed
        assertDoesNotThrow(() -> parser.parse("n"));
        assertDoesNotThrow(() -> parser.parse("N"));

        // for name, abbreviated, non-reversed
        assertDoesNotThrow(() -> parser.parse("r n"));
        assertDoesNotThrow(() -> parser.parse("r N"));

        // for name, non-reversed
        assertDoesNotThrow(() -> parser.parse("name"));
        assertDoesNotThrow(() -> parser.parse("NAME"));
        assertDoesNotThrow(() -> parser.parse("Name"));
        assertDoesNotThrow(() -> parser.parse("nAmE"));
        assertDoesNotThrow(() -> parser.parse("NAme"));

        // for name, reversed
        assertDoesNotThrow(() -> parser.parse("r name"));
        assertDoesNotThrow(() -> parser.parse("r NAME"));
        assertDoesNotThrow(() -> parser.parse("r Name"));
        assertDoesNotThrow(() -> parser.parse("r nAmE"));
        assertDoesNotThrow(() -> parser.parse("r NAme"));

        // for priority, abbreviated, non-reversed
        assertDoesNotThrow(() -> parser.parse("p"));
        assertDoesNotThrow(() -> parser.parse("p"));
        assertDoesNotThrow(() -> parser.parse("prio"));
        assertDoesNotThrow(() -> parser.parse("Prio"));
        assertDoesNotThrow(() -> parser.parse("PRIO"));
        assertDoesNotThrow(() -> parser.parse("prIo"));
        assertDoesNotThrow(() -> parser.parse("prIO"));

        // for priority, non-abbreviated, non-reversed
        assertDoesNotThrow(() -> parser.parse("priority"));
        assertDoesNotThrow(() -> parser.parse("Priority"));
        assertDoesNotThrow(() -> parser.parse("PRIORITY"));
        assertDoesNotThrow(() -> parser.parse("pRioRiTy"));
        assertDoesNotThrow(() -> parser.parse("PRIOrity"));

        // for priority, abbreviated, reversed
        assertDoesNotThrow(() -> parser.parse("r p"));
        assertDoesNotThrow(() -> parser.parse("r P"));
        assertDoesNotThrow(() -> parser.parse("r prio"));
        assertDoesNotThrow(() -> parser.parse("r Prio"));
        assertDoesNotThrow(() -> parser.parse("r PRIO"));
        assertDoesNotThrow(() -> parser.parse("r prIo"));
        assertDoesNotThrow(() -> parser.parse("r PRio"));

        // for priority, non-abbreviated, reversed
        assertDoesNotThrow(() -> parser.parse("r priority"));
        assertDoesNotThrow(() -> parser.parse("r Priority"));
        assertDoesNotThrow(() -> parser.parse("r PRIORITY"));
        assertDoesNotThrow(() -> parser.parse("r pRioRiTy"));
        assertDoesNotThrow(() -> parser.parse("r PRIOrity"));

        // for date, abbreviated, non-reversed
        assertDoesNotThrow(() -> parser.parse("d"));
        assertDoesNotThrow(() -> parser.parse("D"));

        // for date, abbreviated, reversed
        assertDoesNotThrow(() -> parser.parse("r d"));
        assertDoesNotThrow(() -> parser.parse("r D"));

        // for date, with "date", non-reversed
        assertDoesNotThrow(() -> parser.parse("date"));
        assertDoesNotThrow(() -> parser.parse("DATE"));
        assertDoesNotThrow(() -> parser.parse("Date"));
        assertDoesNotThrow(() -> parser.parse("dAtE"));
        assertDoesNotThrow(() -> parser.parse("DAte"));

        // for date, with "date", reversed
        assertDoesNotThrow(() -> parser.parse("r date"));
        assertDoesNotThrow(() -> parser.parse("r DATE"));
        assertDoesNotThrow(() -> parser.parse("r Date"));
        assertDoesNotThrow(() -> parser.parse("r dAtE"));
        assertDoesNotThrow(() -> parser.parse("r DAte"));

        // for date, with "deadline", non-reversed
        assertDoesNotThrow(() -> parser.parse("r deadline"));
        assertDoesNotThrow(() -> parser.parse("r DEADLINE"));
        assertDoesNotThrow(() -> parser.parse("r Deadline"));
        assertDoesNotThrow(() -> parser.parse("r dEaDlInE"));
        assertDoesNotThrow(() -> parser.parse("r DeadLINE"));

        // for date, with "deadline", reversed
        assertDoesNotThrow(() -> parser.parse("r deadline"));
        assertDoesNotThrow(() -> parser.parse("r DEADLINE"));
        assertDoesNotThrow(() -> parser.parse("r Deadline"));
        assertDoesNotThrow(() -> parser.parse("r dEaDlInE"));
        assertDoesNotThrow(() -> parser.parse("r DeadLINE"));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {

        // empty input
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            SortTaskCommand.MESSAGE_USAGE));

        assertParseFailure(parser, " ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            SortTaskCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            SortTaskCommand.MESSAGE_USAGE));

        // invalid input that only has one word
        assertParseFailure(parser, "a", Criterion.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "pr", Criterion.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "prior", Criterion.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "na", Criterion.MESSAGE_CONSTRAINTS);

        // invalid input that has more than one word

        // invalid [r]
        assertParseFailure(parser, "a na", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                SortTaskCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "b prior", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                SortTaskCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "p deadline", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                SortTaskCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "n date", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                SortTaskCommand.MESSAGE_USAGE));

        // invalid criterion
        assertParseFailure(parser, "r pri", String.format(Criterion.MESSAGE_CONSTRAINTS));

        assertParseFailure(parser, "r prior", String.format(Criterion.MESSAGE_CONSTRAINTS));

        assertParseFailure(parser, "r datee", String.format(Criterion.MESSAGE_CONSTRAINTS));

        assertParseFailure(parser, "r nameee", String.format(Criterion.MESSAGE_CONSTRAINTS));
    }
}

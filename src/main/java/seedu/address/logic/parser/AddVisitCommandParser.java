package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VISIT_DATE;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.AddVisitCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class AddVisitCommandParser implements Parser<AddVisitCommand> {

    @Override
    public AddVisitCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_VISIT_DATE);
        Index index;
        String date;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
            // Take date from '/vd' prefix or use current timing for report date.
            date = ParserUtil.parseVisitReport(argMultimap.getValue(PREFIX_VISIT_DATE)
                .orElse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                AddVisitCommand.MESSAGE_USAGE), ive);
        }

        return new AddVisitCommand(index, date);


    }
}

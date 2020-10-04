package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.AddTGCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.TutorialGroup;

import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL_GRP;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import static java.util.Objects.requireNonNull;

public class AddTGCommandParser implements Parser<AddTGCommand> {
    public AddTGCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_TUTORIAL_GRP);

//        try {
//            id = ParserUtil.parseIndex(argMultimap.getPreamble());
//        } catch (IllegalValueException ive) {
//            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
//                    AddTGCommand.MESSAGE_USAGE), ive);
//        }

        String id = argMultimap.getValue(PREFIX_TUTORIAL_GRP).orElse("");

        return new AddTGCommand(new TutorialGroup(id));
    }

}

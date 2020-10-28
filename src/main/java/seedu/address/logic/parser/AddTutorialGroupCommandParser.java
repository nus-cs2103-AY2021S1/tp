package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL_GRP;

import seedu.address.logic.commands.AddTutorialGroupCommand;
//import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tutorialgroup.TutorialGroup;
import seedu.address.model.tutorialgroup.TutorialGroupId;

import java.time.LocalTime;

public class AddTutorialGroupCommandParser implements Parser<AddTutorialGroupCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddTutorialGroupCommand
     * and returns a AddTutorialGroupCommand object for execution.
     */
    public AddTutorialGroupCommand parse(String args) /*throws ParseException*/ {
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

        // todo: change the LocalTime
        return new AddTutorialGroupCommand(new TutorialGroup(new TutorialGroupId(id),
                LocalTime.now(), LocalTime.now()));
    }

}

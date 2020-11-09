package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.UNKNOWN_FIND_COMMAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Arrays;
import java.util.stream.Stream;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.find.FindContactCommand;
import seedu.address.logic.commands.find.FindEventCommand;
import seedu.address.logic.commands.find.FindTodoCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.ContactMatchesFindKeywordPredicate;
import seedu.address.model.task.TaskMatchesFindKeywordPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        String[] splitArgs = args.trim().split(" ", 2);
        if (splitArgs[0].equals("contact")) {
            if (splitArgs.length < 2 || splitArgs[1].trim().isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindContactCommand.MESSAGE_USAGE));
            }

            ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(" " + splitArgs[1], PREFIX_NAME, PREFIX_TAG);

            if (areAllPrefixesNotPresent(argMultimap, PREFIX_NAME, PREFIX_TAG)
                || !argMultimap.getPreamble().isEmpty()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    FindContactCommand.MESSAGE_USAGE));
            }
            if (arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_TAG)) {
                String name = argMultimap.getValue(PREFIX_NAME).get();
                if (name.trim().equals("")) {
                    throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        FindContactCommand.MESSAGE_USAGE));
                }
                String[] nameKeywords = name.split("\\s+");
                String[] tagKeywords = argMultimap.getValue(PREFIX_TAG).get().trim().split("\\s+");

                if (tagKeywords.length != 1 || tagKeywords[0].trim().equals("")) {
                    throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        FindContactCommand.MESSAGE_USAGE));
                }
                return new FindContactCommand(new ContactMatchesFindKeywordPredicate(
                    Arrays.asList(nameKeywords), tagKeywords[0]));
            } else if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
                String name = argMultimap.getValue(PREFIX_NAME).get();
                if (name.trim().equals("")) {
                    throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        FindContactCommand.MESSAGE_USAGE));
                }
                String[] nameKeywords = name.split("\\s+");
                return new FindContactCommand(new ContactMatchesFindKeywordPredicate(Arrays.asList(nameKeywords)));
            } else {
                String[] keywords = argMultimap.getValue(PREFIX_TAG).get().trim().split("\\s+");

                if (keywords.length != 1 || keywords[0].trim().equals("")) {
                    throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        FindContactCommand.MESSAGE_USAGE));
                }
                return new FindContactCommand(new ContactMatchesFindKeywordPredicate(keywords[0]));
            }
        } else if (splitArgs[0].equals("todo")) {
            if (splitArgs.length < 2 || splitArgs[1].trim().isEmpty()) {
                throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindTodoCommand.MESSAGE_USAGE));
            }

            ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(" " + splitArgs[1], PREFIX_DESCRIPTION, PREFIX_TAG);

            if (areAllPrefixesNotPresent(argMultimap, PREFIX_DESCRIPTION, PREFIX_TAG)
                || !argMultimap.getPreamble().isEmpty()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    FindTodoCommand.MESSAGE_USAGE));
            }

            if (arePrefixesPresent(argMultimap, PREFIX_DESCRIPTION, PREFIX_TAG)) {
                String desc = argMultimap.getValue(PREFIX_DESCRIPTION).get();
                if (desc.trim().equals("")) {
                    throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        FindTodoCommand.MESSAGE_USAGE));
                }
                String[] descKeywords = desc.split("\\s+");
                String[] tagKeywords = argMultimap.getValue(PREFIX_TAG).get().trim().split("\\s+");

                if (tagKeywords.length != 1 || tagKeywords[0].trim().equals("")) {
                    throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        FindTodoCommand.MESSAGE_USAGE));
                }
                return new FindTodoCommand(new TaskMatchesFindKeywordPredicate(
                    Arrays.asList(descKeywords), tagKeywords[0]));
            } else if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
                String desc = argMultimap.getValue(PREFIX_DESCRIPTION).get();
                if (desc.trim().equals("")) {
                    throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        FindTodoCommand.MESSAGE_USAGE));
                }
                String[] descKeywords = desc.split("\\s+");
                return new FindTodoCommand(new TaskMatchesFindKeywordPredicate(Arrays.asList(descKeywords)));
            } else {
                String[] keywords = argMultimap.getValue(PREFIX_TAG).get().trim().split("\\s+");

                if (keywords.length != 1 || keywords[0].trim().equals("")) {
                    throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        FindTodoCommand.MESSAGE_USAGE));
                }
                return new FindTodoCommand(new TaskMatchesFindKeywordPredicate(keywords[0]));
            }
        } else if (splitArgs[0].equals("event")) {
            if (splitArgs.length < 2 || splitArgs[1].trim().isEmpty()) {
                throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindEventCommand.MESSAGE_USAGE));
            }

            ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(" " + splitArgs[1], PREFIX_DESCRIPTION, PREFIX_TAG);

            if (areAllPrefixesNotPresent(argMultimap, PREFIX_DESCRIPTION, PREFIX_TAG)
                || !argMultimap.getPreamble().isEmpty()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    FindEventCommand.MESSAGE_USAGE));
            }

            if (arePrefixesPresent(argMultimap, PREFIX_DESCRIPTION, PREFIX_TAG)) {
                String desc = argMultimap.getValue(PREFIX_DESCRIPTION).get();
                if (desc.trim().equals("")) {
                    throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        FindEventCommand.MESSAGE_USAGE));
                }
                String[] descKeywords = desc.split("\\s+");
                String[] tagKeywords = argMultimap.getValue(PREFIX_TAG).get().trim().split("\\s+");

                if (tagKeywords.length != 1 || tagKeywords[0].trim().equals("")) {
                    throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        FindEventCommand.MESSAGE_USAGE));
                }
                return new FindEventCommand(new TaskMatchesFindKeywordPredicate(
                    Arrays.asList(descKeywords), tagKeywords[0]));
            } else if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
                String desc = argMultimap.getValue(PREFIX_DESCRIPTION).get();
                if (desc.trim().equals("")) {
                    throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        FindEventCommand.MESSAGE_USAGE));
                }
                String[] descKeywords = desc.split("\\s+");
                return new FindEventCommand(new TaskMatchesFindKeywordPredicate(Arrays.asList(descKeywords)));
            } else {
                String[] keywords = argMultimap.getValue(PREFIX_TAG).get().trim().split("\\s+");

                if (keywords.length != 1 || keywords[0].trim().equals("")) {
                    throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        FindEventCommand.MESSAGE_USAGE));
                }
                return new FindEventCommand(new TaskMatchesFindKeywordPredicate(keywords[0]));
            }
        } else {
            throw new ParseException(UNKNOWN_FIND_COMMAND);
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Returns true if all of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean areAllPrefixesNotPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> !argumentMultimap.getValue(prefix).isPresent());
    }

}

package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.FindCommand.FindPatientDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;

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
        requireNonNull(args);

        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE)
            );
        }

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_NRIC);

        FindPatientDescriptor findPatientDescriptor = new FindPatientDescriptor();

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            String nameArguments = argMultimap.getValue(PREFIX_NAME).get();
            if (nameArguments.isBlank()) {
                throw new ParseException(FindCommand.MESSAGE_NO_NAME);
            }
            String[] nameKeywords = nameArguments.split("\\s+");
            findPatientDescriptor.setNamePredicate(nameKeywords);
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            String phoneArguments = argMultimap.getValue(PREFIX_PHONE).get();
            if (phoneArguments.isBlank()) {
                throw new ParseException(FindCommand.MESSAGE_NO_PHONE);
            }
            String[] phones = phoneArguments.split("\\s+");
            findPatientDescriptor.setPhonePredicate(phones);
        }
        if (argMultimap.getValue(PREFIX_NRIC).isPresent()) {
            String nricArguments = argMultimap.getValue(PREFIX_NRIC).get();
            if (nricArguments.isBlank()) {
                throw new ParseException(FindCommand.MESSAGE_NO_NRIC);
            }
            String[] nrics = nricArguments.split("\\s+");
            findPatientDescriptor.setNricPredicate(nrics);
        }

        if (!findPatientDescriptor.isAnyFieldToFind()) {
            throw new ParseException(FindCommand.MESSAGE_NO_FIND);
        }

        return new FindCommand(findPatientDescriptor);
    }

}

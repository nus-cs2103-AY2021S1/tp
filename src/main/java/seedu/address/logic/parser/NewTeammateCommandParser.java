package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.project.NewTeammateCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new AssignCommand object
 */
public class NewTeammateCommandParser implements Parser<NewTeammateCommand> {



    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public NewTeammateCommand parse(String args) throws ParseException {
        requireNonNull(args);

        try {
            String input = args.trim();

            final int lowerBound = 0;
            int upperBound = input.indexOf(" ");
            if (upperBound <= 0) {
                throw new ParseException("");
            }

            String name = input.substring(lowerBound, upperBound);
            String afterNameSubstring = input.substring(upperBound + 1);

            upperBound = afterNameSubstring.indexOf(" ");
            String gitUserName = afterNameSubstring.substring(lowerBound, upperBound);
            String afterGitUserNameSubstring = afterNameSubstring.substring(upperBound + 1);

            upperBound = afterGitUserNameSubstring.indexOf(" ");
            String phone = afterGitUserNameSubstring.substring(lowerBound, upperBound);
            String afterPhoneSubstring = afterGitUserNameSubstring.substring(upperBound + 1);

            upperBound = afterPhoneSubstring.indexOf(" ");
            String email = afterPhoneSubstring.substring(lowerBound, upperBound);

            String address = afterPhoneSubstring.substring(upperBound + 1);


            return new NewTeammateCommand(name, gitUserName, phone, email, address);
        } catch (ParseException | StringIndexOutOfBoundsException e) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, NewTeammateCommand.MESSAGE_USAGE), e);
        }
    }

}

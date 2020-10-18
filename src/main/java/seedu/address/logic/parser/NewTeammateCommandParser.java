package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TEAMMATE_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TEAMMATE_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TEAMMATE_GIT_USERNAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TEAMMATE_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TEAMMATE_PHONE;

import java.util.stream.Stream;

import seedu.address.logic.commands.project.NewTeammateCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.GitUserName;
import seedu.address.model.person.PersonName;
import seedu.address.model.person.Phone;

/**
 * Parses input arguments and creates a new AssignCommand object
 */
public class NewTeammateCommandParser implements Parser<NewTeammateCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the NewTeammateCommand
     * and returns a NewTeammateCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public NewTeammateCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_TEAMMATE_NAME, PREFIX_TEAMMATE_GIT_USERNAME,
                PREFIX_TEAMMATE_PHONE, PREFIX_TEAMMATE_EMAIL, PREFIX_TEAMMATE_ADDRESS);

        if (!arePrefixesPresent(argMultimap, PREFIX_TEAMMATE_NAME, PREFIX_TEAMMATE_GIT_USERNAME,
            PREFIX_TEAMMATE_PHONE, PREFIX_TEAMMATE_EMAIL, PREFIX_TEAMMATE_ADDRESS)
            || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, NewTeammateCommand
                .MESSAGE_USAGE));
        }

        PersonName name = ParsePersonUtil.parsePersonName(argMultimap
            .getValue(PREFIX_TEAMMATE_NAME).get());

        GitUserName gitUserName =
            ParsePersonUtil.parseGitUserName(argMultimap
                .getValue(PREFIX_TEAMMATE_GIT_USERNAME).get());

        Phone phone = ParsePersonUtil.parsePhone(argMultimap
            .getValue(PREFIX_TEAMMATE_PHONE).get());

        Email email = ParsePersonUtil.parseEmail(argMultimap
            .getValue(PREFIX_TEAMMATE_EMAIL).get());

        Address address = ParsePersonUtil.parseAddress(argMultimap
            .getValue(PREFIX_TEAMMATE_ADDRESS).get());

        return new NewTeammateCommand(name, gitUserName, phone, email, address);
    }


    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
    //        try {
    //            String input = args.trim();
    //
    //            final int lowerBound = 0;
    //            int upperBound = input.indexOf(" ");
    //            if (upperBound <= 0) {
    //                throw new ParseException("");
    //            }
    //
    //            String name = input.substring(lowerBound, upperBound);
    //            String afterNameSubstring = input.substring(upperBound + 1);
    //
    //            upperBound = afterNameSubstring.indexOf(" ");
    //            String gitUserName = afterNameSubstring.substring(lowerBound, upperBound);
    //            String afterGitUserNameSubstring = afterNameSubstring.substring(upperBound + 1);
    //
    //            upperBound = afterGitUserNameSubstring.indexOf(" ");
    //            String phone = afterGitUserNameSubstring.substring(lowerBound, upperBound);
    //            String afterPhoneSubstring = afterGitUserNameSubstring.substring(upperBound + 1);
    //
    //            upperBound = afterPhoneSubstring.indexOf(" ");
    //            String email = afterPhoneSubstring.substring(lowerBound, upperBound);
    //
    //            String address = afterPhoneSubstring.substring(upperBound + 1);
    //
    //
    //            return new NewTeammateCommand(name, gitUserName, phone, email, address);
    //        } catch (ParseException | StringIndexOutOfBoundsException e) {
    //            throw new ParseException(
    //                String.format(MESSAGE_INVALID_COMMAND_FORMAT, NewTeammateCommand.MESSAGE_USAGE), e);
    //        }

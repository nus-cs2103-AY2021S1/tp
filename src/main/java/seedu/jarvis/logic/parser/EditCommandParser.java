package seedu.jarvis.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.jarvis.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.jarvis.commons.util.StringUtil.pad;
import static seedu.jarvis.logic.parser.CliSyntax.EDIT_LOGIN;
import static seedu.jarvis.logic.parser.CliSyntax.EDIT_MASTERY_CHECK;
import static seedu.jarvis.logic.parser.CliSyntax.EDIT_STUDENT;
import static seedu.jarvis.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.jarvis.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.jarvis.logic.parser.CliSyntax.PREFIX_PASSWORD;
import static seedu.jarvis.logic.parser.CliSyntax.PREFIX_SCORE;
import static seedu.jarvis.logic.parser.CliSyntax.PREFIX_TELEGRAM;
import static seedu.jarvis.logic.parser.CliSyntax.PREFIX_USERNAME;

import java.util.Arrays;

import seedu.jarvis.commons.core.index.Index;
import seedu.jarvis.logic.commands.edit.EditCommand;
import seedu.jarvis.logic.commands.edit.EditLoginCommand;
import seedu.jarvis.logic.commands.edit.EditLoginCommand.EditLoginDescriptor;
import seedu.jarvis.logic.commands.edit.EditMasteryCheckCommand;
import seedu.jarvis.logic.commands.edit.EditMasteryCheckCommand.EditMasteryCheckDescriptor;
import seedu.jarvis.logic.commands.edit.EditStudentCommand;
import seedu.jarvis.logic.commands.edit.EditStudentCommand.EditPersonDescriptor;
import seedu.jarvis.logic.parser.exceptions.ParseException;
import seedu.jarvis.model.flag.Flag;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);

        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
        }

        // split the string trimmedArgs with regex of one or more whitespace characters.
        // result will be as such: {-s, Alex, Yeoh}
        String[] inputsAfterCommandType = trimmedArgs.split("\\s+");
        assert inputsAfterCommandType.length > 0 : "String array of the arguments is empty";

        Flag commandFlag;
        try {
            commandFlag = ParserUtil.parseFlag(inputsAfterCommandType[0]);
        } catch (ParseException ex) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
        }

        String editArgs = String.join(" ", Arrays.copyOfRange(inputsAfterCommandType, 1,
                inputsAfterCommandType.length));

        ArgumentMultimap argMultimap;

        switch (commandFlag.getFlag()) {
        case EDIT_STUDENT:
            argMultimap = ArgumentTokenizer.tokenize(editArgs, PREFIX_NAME, PREFIX_TELEGRAM, PREFIX_EMAIL);

            Index index;

            try {
                index = ParserUtil.parseIndex(argMultimap.getPreamble());
            } catch (ParseException pe) {
                throw pe;
            }

            EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
            if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
                editPersonDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
            }
            if (argMultimap.getValue(PREFIX_TELEGRAM).isPresent()) {
                editPersonDescriptor.setTelegram(ParserUtil.parseTelegram(argMultimap.getValue(PREFIX_TELEGRAM).get()));
            }
            if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
                editPersonDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
            }

            if (!editPersonDescriptor.isAnyFieldEdited()) {
                throw new ParseException(EditStudentCommand.MESSAGE_NOT_EDITED);
            }

            return new EditStudentCommand(index, editPersonDescriptor);

        case EDIT_LOGIN:
            argMultimap = ArgumentTokenizer.tokenize(pad(editArgs), PREFIX_USERNAME, PREFIX_PASSWORD);

            EditLoginDescriptor editLoginDescriptor = new EditLoginDescriptor();
            if (argMultimap.getValue(PREFIX_USERNAME).isPresent()) {
                editLoginDescriptor.setUsername(ParserUtil.parseUsername(argMultimap.getValue(PREFIX_USERNAME).get()));
            }
            if (argMultimap.getValue(PREFIX_PASSWORD).isPresent()) {
                editLoginDescriptor.setPassword(argMultimap.getValue(PREFIX_PASSWORD).get());
            }

            if (!editLoginDescriptor.isAnyFieldEdited()) {
                throw new ParseException(EditLoginCommand.MESSAGE_NOT_EDITED);
            }

            return new EditLoginCommand(editLoginDescriptor);

        case EDIT_MASTERY_CHECK:
            argMultimap = ArgumentTokenizer.tokenize(pad(editArgs), PREFIX_SCORE);
            String[] split = editArgs.split("\\s+");

            Index masteryCheckIndex;

            if (split.length <= 1) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                                EditMasteryCheckCommand.ONE_OR_MORE_PARAMS_MISSING));
            }

            try {
                masteryCheckIndex = ParserUtil.parseIndex(split[0]);
            } catch (ParseException pe) {
                throw new ParseException(pe.getMessage());
            }

            EditMasteryCheckDescriptor editMasteryCheckDescriptor = new EditMasteryCheckDescriptor();
            if (argMultimap.getValue(PREFIX_SCORE).isPresent()) {
                editMasteryCheckDescriptor.setPassed(ParserUtil.parseScore(argMultimap.getValue(PREFIX_SCORE).get()));
            } else {
                throw new ParseException(EditMasteryCheckCommand.NO_SCORE_PARAMETER);
            }

            if (!editMasteryCheckDescriptor.isAnyFieldEdited()) {
                throw new ParseException(EditMasteryCheckCommand.MESSAGE_NOT_EDITED);
            }

            return new EditMasteryCheckCommand(masteryCheckIndex, editMasteryCheckDescriptor);

        default:
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditMasteryCheckCommand.MESSAGE_USAGE));
        }
    }
}


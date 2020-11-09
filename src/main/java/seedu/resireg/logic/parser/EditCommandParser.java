package seedu.resireg.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.resireg.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.resireg.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.resireg.logic.parser.CliSyntax.PREFIX_FACULTY;
import static seedu.resireg.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.resireg.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.resireg.logic.parser.CliSyntax.PREFIX_STUDENT_ID;
import static seedu.resireg.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.resireg.logic.parser.ParserUtil.parseTagsForEdit;

import seedu.resireg.commons.core.index.Index;
import seedu.resireg.logic.commands.EditCommand;
import seedu.resireg.logic.commands.EditCommand.EditStudentDescriptor;
import seedu.resireg.logic.parser.exceptions.ParseException;

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
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_FACULTY,
                    PREFIX_STUDENT_ID, PREFIX_TAG);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.HELP.getFullMessage()),
                    pe);
        }

        EditStudentDescriptor editStudentDescriptor = new EditStudentDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editStudentDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editStudentDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editStudentDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_FACULTY).isPresent()) {
            editStudentDescriptor.setFaculty(ParserUtil.parseFaculty(argMultimap.getValue(PREFIX_FACULTY).get()));
        }
        if (argMultimap.getValue(PREFIX_STUDENT_ID).isPresent()) {
            editStudentDescriptor.setStudentId(ParserUtil.parseStudentId(argMultimap
                .getValue(PREFIX_STUDENT_ID).get()));
        }
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editStudentDescriptor::setTags);

        if (!editStudentDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editStudentDescriptor);
    }


}

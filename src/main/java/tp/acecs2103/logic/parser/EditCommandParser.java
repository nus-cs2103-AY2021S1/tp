package tp.acecs2103.logic.parser;

import static java.util.Objects.requireNonNull;
import static tp.acecs2103.logic.parser.ParserUtil.parseWeekNumber;

import java.time.LocalDate;

import tp.acecs2103.commons.core.Messages;
import tp.acecs2103.logic.commands.EditCommand;
import tp.acecs2103.logic.parser.exceptions.ParseException;
import tp.acecs2103.model.task.CustomizedDeadline;
import tp.acecs2103.model.task.Description;
import tp.acecs2103.model.task.Index;
import tp.acecs2103.model.task.Remark;
import tp.acecs2103.model.task.WeekNumber;

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
                ArgumentTokenizer.tokenize(args, CliSyntax.PREFIX_WEEK_NUMBER, CliSyntax.PREFIX_INDEX,
                        CliSyntax.PREFIX_DESCRIPTION,
                        CliSyntax.PREFIX_CUSTOMIZED_DEADLINE, CliSyntax.PREFIX_REMARK);

        Index index;

        try {
            String indexParsed = ParserUtil.parseIndex(argMultimap.getValue(CliSyntax.PREFIX_INDEX).get());
            index = new Index(indexParsed);
        } catch (ParseException pe) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    EditCommand.MESSAGE_USAGE), pe);
        }

        EditCommand.EditTaskDescriptor editTaskDescriptor = new EditCommand.EditTaskDescriptor();

        if (!argMultimap.getValue(CliSyntax.PREFIX_WEEK_NUMBER).get().equals("")) {
            int weekNumberParsed = parseWeekNumber(argMultimap.getValue(CliSyntax.PREFIX_WEEK_NUMBER).get());
            editTaskDescriptor.setWeekNumber(new WeekNumber(Integer.toString(weekNumberParsed)));
        }
        if (!argMultimap.getValue(CliSyntax.PREFIX_DESCRIPTION).get().equals("")) {
            String descriptionParsed = ParserUtil.parseDescription(
                    argMultimap.getValue(CliSyntax.PREFIX_DESCRIPTION).get());
            editTaskDescriptor.setDescription(new Description(descriptionParsed));
        }

        if (!argMultimap.getValue(CliSyntax.PREFIX_CUSTOMIZED_DEADLINE).get().equals("")) {
            LocalDate customizedDeadlineParsed = ParserUtil.parseCustomizedDeadline(
                    argMultimap.getValue(CliSyntax.PREFIX_CUSTOMIZED_DEADLINE).get());
            editTaskDescriptor.setCustomizedDeadline(
                    new CustomizedDeadline(customizedDeadlineParsed.toString(), customizedDeadlineParsed));
        }
        if (!argMultimap.getValue(CliSyntax.PREFIX_REMARK).get().equals("")) {
            String remarkParsed = ParserUtil.parseRemark(argMultimap.getValue(CliSyntax.PREFIX_REMARK).get());
            editTaskDescriptor.setRemark(new Remark(remarkParsed));
        }

        if (!editTaskDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editTaskDescriptor);
    }
}

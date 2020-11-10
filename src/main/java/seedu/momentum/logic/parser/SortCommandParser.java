//@@author kkangs0226

package seedu.momentum.logic.parser;

import static seedu.momentum.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.momentum.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.momentum.logic.commands.SortCommand.INPUT_ALPHA_TYPE;
import static seedu.momentum.logic.commands.SortCommand.INPUT_ASCENDING_ORDER;
import static seedu.momentum.logic.commands.SortCommand.INPUT_CREATED_TYPE;
import static seedu.momentum.logic.commands.SortCommand.INPUT_DEADLINE_TYPE;
import static seedu.momentum.logic.commands.SortCommand.INPUT_DESCENDING_ORDER;
import static seedu.momentum.logic.parser.CliSyntax.PREFIX_COMPLETION_STATUS;
import static seedu.momentum.logic.parser.CliSyntax.SORT_ORDER;
import static seedu.momentum.logic.parser.CliSyntax.SORT_TYPE;

import seedu.momentum.logic.commands.SortCommand;
import seedu.momentum.logic.parser.exceptions.ParseException;
import seedu.momentum.model.Model;
import seedu.momentum.model.project.comparators.SortType;

/**
 * Parses input arguments and creates an appropriate SortCommand object.
 */
public class SortCommandParser implements Parser<SortCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SortCommand.
     * and returns a SortCommand object for execution.
     *
     * @param args Arguments to parse.
     * @param model The current model, to provide context for parsing the arguments.
     * @return A new sort command with the parsed arguments.
     * @throws ParseException If the user does not conform to the expected format.
     */
    public SortCommand parse(String args, Model model) throws ParseException {
        requireAllNonNull(args, model);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, SORT_TYPE, SORT_ORDER, PREFIX_COMPLETION_STATUS);

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }

        String sortOrder = parseSortOrder(argMultimap);
        boolean isAscending = sortOrder.equals(INPUT_ASCENDING_ORDER);
        SortType sortType = parseSortType(argMultimap);
        boolean isDefault = false;

        if (argMultimap.getValue(SORT_TYPE).isEmpty() && argMultimap.getValue(SORT_ORDER).isEmpty()) {
            isDefault = true;
            sortType = SortType.NULL;
        }

        boolean changeSortByCompletionStatus = false;
        if (argMultimap.getValue(PREFIX_COMPLETION_STATUS).isPresent()) {
            changeSortByCompletionStatus = true;
        }

        return new SortCommand(sortType, isAscending, isDefault, changeSortByCompletionStatus);

    }

    /**
     * Parses the sort order.
     *
     * @param argMultimap The parsed argument tokens, containing the sort order token.
     * @return A String representing the order of the sort entered by the user.
     * @throws ParseException If Ihe user does not conform to the expceted format.
     */
    private String parseSortOrder(ArgumentMultimap argMultimap) throws ParseException {

        if (argMultimap.getValue(SORT_ORDER).isEmpty()) {
            return INPUT_ASCENDING_ORDER;
        }

        String sortOrder = argMultimap.getValue(SORT_ORDER).get();
        sortOrder = sortOrder.trim();

        if (sortOrder.equals(INPUT_ASCENDING_ORDER) || sortOrder.equals(INPUT_DESCENDING_ORDER)) {
            return sortOrder;
        }
        throw new ParseException(String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_INVALID_SORT_TYPE_OR_ORDER));
    }

    /**
     * Parses the sort type and returns the corresponding enum value.
     *
     * @param argMultimap The parsed argument tokens, containing the sort type token.
     * @return A SortType representing the type of the sort entered by the user.
     * @throws ParseException If the user does not conform to the expected format.
     */
    private SortType parseSortType(ArgumentMultimap argMultimap) throws ParseException {

        if (argMultimap.getValue(SORT_TYPE).isEmpty()) {
            return SortType.NULL;
        }

        String sortType = argMultimap.getValue(SORT_TYPE).get();
        sortType = sortType.trim();

        switch (sortType) {
        case INPUT_ALPHA_TYPE:
            return SortType.ALPHA;
        case INPUT_DEADLINE_TYPE:
            return SortType.DEADLINE;
        case INPUT_CREATED_TYPE:
            return SortType.CREATED;
        default:
            throw new ParseException(String.format(
                    MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_INVALID_SORT_TYPE_OR_ORDER));
        }
    }

}

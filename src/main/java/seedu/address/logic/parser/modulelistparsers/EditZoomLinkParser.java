package seedu.address.logic.parser.modulelistparsers;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ZOOM_LINK;

import java.util.logging.Logger;
import java.util.stream.Stream;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.modulelistcommands.EditZoomLinkCommand;
import seedu.address.logic.commands.modulelistcommands.ZoomLinkCommand.ZoomDescriptor;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditZoomLinkCommand object.
 */
public class EditZoomLinkParser implements Parser<EditZoomLinkCommand> {

    private final Logger logger = LogsCenter.getLogger(EditZoomLinkParser.class);

    /**
     * Parses the given {@code String} of arguments in the context of the EditZoomLinkCommand
     * and returns an EditZoomLinkCommand object for execution.
     *
     * @throws ParseException If the user input does not conform the expected format.
     */
    public EditZoomLinkCommand parse(String args) throws ParseException {
        requireNonNull(args);
        logger.info("Parsing command arguments: " + args);

        ArgumentTokenizer tokenizer = new ArgumentTokenizer(args, PREFIX_NAME, PREFIX_ZOOM_LINK);
        ArgumentMultimap argMultimap = tokenizer.tokenize();

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ZOOM_LINK)
                || argMultimap.getPreamble().isBlank()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditZoomLinkCommand.MESSAGE_USAGE));
        }

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditZoomLinkCommand.MESSAGE_USAGE), pe);
        }
        ZoomDescriptor zoomDescriptor = new ZoomDescriptor();

        zoomDescriptor.setZoomLink(ParserUtil.parseZoomLink(argMultimap.getValue(PREFIX_ZOOM_LINK).get()));
        zoomDescriptor.setModuleLesson(ParserUtil.parseModuleLesson(argMultimap.getValue(PREFIX_NAME).get()));

        return new EditZoomLinkCommand(index, zoomDescriptor);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}

package seedu.zookeep.logic.parser;

import static seedu.zookeep.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.nio.file.Files;
import java.nio.file.Path;

import seedu.zookeep.logic.commands.SnapCommand;
import seedu.zookeep.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new SnapCommand object
 */
public class SnapCommandParser implements Parser<SnapCommand> {
    private static final String VALIDATION_REGEX = "^[a-zA-Z0-9_-]*$";

    /**
     * Parses the given {@code String} of arguments in the context of the SnapCommand
     * and returns a SnapCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public SnapCommand parse(String args) throws ParseException {
        String fileName = args.trim();
        Path savePath = Path.of("data", fileName + SnapCommand.FILE_FORMAT);
        int maxFileNameLength = 100;

        if (fileName.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SnapCommand.MESSAGE_USAGE));
        } else if (!fileName.matches(VALIDATION_REGEX) || fileName.length() > maxFileNameLength) {
            throw new ParseException(SnapCommand.MESSAGE_CONSTRAINTS);
        } else if (Files.exists(savePath)) {
            throw new ParseException(String.format(SnapCommand.MESSAGE_WARNING, fileName + SnapCommand.FILE_FORMAT));
        }

        return new SnapCommand(savePath, fileName);
    }

}

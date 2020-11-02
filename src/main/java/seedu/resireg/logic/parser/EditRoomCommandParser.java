package seedu.resireg.logic.parser.exceptions;

import static java.util.Objects.requireNonNull;
import static seedu.resireg.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.resireg.logic.parser.CliSyntax.PREFIX_ROOM_FLOOR;
import static seedu.resireg.logic.parser.CliSyntax.PREFIX_ROOM_NUMBER;
import static seedu.resireg.logic.parser.CliSyntax.PREFIX_ROOM_TYPE;
import static seedu.resireg.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.resireg.logic.parser.ParserUtil.parseTagsForEdit;

import seedu.resireg.commons.core.index.Index;
import seedu.resireg.logic.commands.EditRoomCommand;
import seedu.resireg.logic.commands.EditRoomCommand.EditRoomDescriptor;
import seedu.resireg.logic.parser.ArgumentMultimap;
import seedu.resireg.logic.parser.ArgumentTokenizer;
import seedu.resireg.logic.parser.Parser;
import seedu.resireg.logic.parser.ParserUtil;

public class EditRoomCommandParser implements Parser<EditRoomCommand> {
    @Override
    public EditRoomCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_ROOM_FLOOR, PREFIX_ROOM_NUMBER,
                PREFIX_ROOM_TYPE, PREFIX_TAG);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditRoomCommand.HELP.getFullMessage()), pe);
        }

        EditRoomDescriptor editRoomDescriptor = new EditRoomDescriptor();
        if (argMultimap.getValue(PREFIX_ROOM_FLOOR).isPresent()) {
            editRoomDescriptor.setFloor(ParserUtil.parseFloor(argMultimap.getValue(PREFIX_ROOM_FLOOR).get()));
        }
        if (argMultimap.getValue(PREFIX_ROOM_NUMBER).isPresent()) {
            editRoomDescriptor.setRoomNumber(
                    ParserUtil.parseRoomNumber(argMultimap.getValue(PREFIX_ROOM_NUMBER).get()));
        }
        if (argMultimap.getValue(PREFIX_ROOM_TYPE).isPresent()) {
            editRoomDescriptor.setRoomType(ParserUtil.parseRoomType(argMultimap.getValue(PREFIX_ROOM_TYPE).get()));
        }
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editRoomDescriptor::setTags);

        if (!editRoomDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditRoomCommand.MESSAGE_NOT_EDITED);
        }

        return new EditRoomCommand(index, editRoomDescriptor);
    }
}

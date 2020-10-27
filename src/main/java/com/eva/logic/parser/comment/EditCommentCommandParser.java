package com.eva.logic.parser.comment;

import static com.eva.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static com.eva.logic.parser.CliSyntax.PREFIX_APPLICANT;
import static com.eva.logic.parser.CliSyntax.PREFIX_STAFF;
import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

import com.eva.commons.core.index.Index;
import com.eva.logic.commands.DeleteCommentCommand;
import com.eva.logic.commands.EditCommand;
import com.eva.logic.commands.EditCommentCommand;
import com.eva.logic.parser.ArgumentMultimap;
import com.eva.logic.parser.ArgumentTokenizer;
import com.eva.logic.parser.Parser;
import com.eva.logic.parser.ParserUtil;
import com.eva.logic.parser.exceptions.ParseException;
import com.eva.model.comment.Comment;

public class EditCommentCommandParser implements Parser<EditCommentCommand> {

    /**
     * Creates edit comment command object
     * @param args
     * @return EditCommentCommand object
     * @throws ParseException When comment input does not exist
     */
    public EditCommentCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_APPLICANT, PREFIX_STAFF);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditCommentCommand.MESSAGE_EDIT_COMMENT_USAGE), pe);
        }

        EditCommand.EditPersonDescriptor editPersonDescriptor =
                new EditCommand.EditPersonDescriptor();
        if (argMultimap.getAllValues(PREFIX_APPLICANT).size() != 0) {
            parseCommentsForEdit(argMultimap.getAllValues(PREFIX_APPLICANT))
                    .ifPresent(editPersonDescriptor::setComments);
        } else if (argMultimap.getAllValues(PREFIX_STAFF).size() != 0) {
            parseCommentsForEdit(argMultimap.getAllValues(PREFIX_STAFF))
                    .ifPresent(editPersonDescriptor::setComments);
        } else {
            throw new ParseException(DeleteCommentCommand.MISSING_PERSONTYPE_MESSAGE);
        }

        if (!editPersonDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        if (!argMultimap.getValue(PREFIX_APPLICANT).isEmpty()) {
            return new EditCommentCommand(index, editPersonDescriptor, "applicant");
        } else if (!argMultimap.getValue(PREFIX_STAFF).isEmpty()) {
            return new EditCommentCommand(index, editPersonDescriptor, "staff");
        }
        return new EditCommentCommand(index, editPersonDescriptor, "staff");
    }
    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Comment>> parseCommentsForEdit(Collection<String> comments) throws ParseException {
        assert comments != null;

        if (comments.isEmpty() || comments.size() == 1
                && comments.contains("")) {
            throw new ParseException(EditCommentCommand.MESSAGE_EDIT_COMMENT_USAGE);
        }
        return Optional.of(ParserUtil.parseComments(comments));
    }
}

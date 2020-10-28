package com.eva.logic.parser.comment;

import static com.eva.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static com.eva.logic.parser.CliSyntax.PREFIX_APPLICANT;
import static com.eva.logic.parser.CliSyntax.PREFIX_STAFF;
import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

import com.eva.commons.core.index.Index;
import com.eva.logic.commands.AddCommentCommand;
import com.eva.logic.commands.CommentCommand;
import com.eva.logic.commands.EditCommand;
import com.eva.logic.parser.ArgumentMultimap;
import com.eva.logic.parser.ArgumentTokenizer;
import com.eva.logic.parser.ParserUtil;
import com.eva.logic.parser.exceptions.ParseException;
import com.eva.model.comment.Comment;



public class AddCommentCommandParser {
    /**
     * For consolidated add commands.
     * @param args
     * @return commentCommand
     * @throws ParseException when there are missing fields
     */
    public CommentCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args,
                        PREFIX_APPLICANT, PREFIX_STAFF);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddCommentCommand.MESSAGE_ADDCOMMENT_USAGE), pe);
        }


        CommentCommand.CommentPersonDescriptor commentPersonDescriptor = new CommentCommand.CommentPersonDescriptor();
        if (argMultimap.getAllValues(PREFIX_APPLICANT).size() != 0) {
            parseCommentsForEdit(argMultimap.getAllValues(PREFIX_APPLICANT))
                    .ifPresent(commentPersonDescriptor::setComments);
        } else if (argMultimap.getAllValues(PREFIX_STAFF).size() != 0) {
            parseCommentsForEdit(argMultimap.getAllValues(PREFIX_STAFF))
                    .ifPresent(commentPersonDescriptor::setComments);
        } else {
            throw new ParseException(AddCommentCommand.MISSING_PERSONTYPE_MESSAGE);
        }

        if (!commentPersonDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }
        if (!argMultimap.getValue(PREFIX_APPLICANT).isEmpty()) {
            return new AddCommentCommand(index, commentPersonDescriptor, "applicant");
        } else if (!argMultimap.getValue(PREFIX_STAFF).isEmpty()) {
            return new AddCommentCommand(index, commentPersonDescriptor, "staff");
        }
        return new AddCommentCommand(index, commentPersonDescriptor, "staff");
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
            throw new ParseException(AddCommentCommand.MESSAGE_ADDCOMMENT_USAGE);
        }
        return Optional.of(ParserUtil.parseComments(comments));
    }
}

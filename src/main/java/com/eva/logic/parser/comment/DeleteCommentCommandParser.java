package com.eva.logic.parser.comment;

import static com.eva.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static com.eva.logic.parser.CliSyntax.PREFIX_COMMENT;
import static com.eva.logic.parser.ParserUtil.arePrefixesPresent;
import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

import com.eva.commons.core.Messages;
import com.eva.commons.core.index.Index;
import com.eva.logic.commands.CommentCommand;
import com.eva.logic.commands.DeleteCommentCommand;
import com.eva.logic.parser.ArgumentMultimap;
import com.eva.logic.parser.ArgumentTokenizer;
import com.eva.logic.parser.ParserUtil;
import com.eva.logic.parser.exceptions.IndexParseException;
import com.eva.logic.parser.exceptions.ParseException;
import com.eva.model.comment.Comment;

public class DeleteCommentCommandParser {
    /**
     * For consolidated delete commands
     * @param args
     * @throws ParseException
     */
    public CommentCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_COMMENT);

        Index index;

        if (!arePrefixesPresent(argMultimap, PREFIX_COMMENT)
                || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                            DeleteCommentCommand.MESSAGE_USAGE));
        }

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteCommentCommand.MESSAGE_DELETECOMMENT_USAGE), pe);
        } catch (IndexParseException pe) {
            throw new ParseException(pe.getMessage());
        }

        CommentCommand.CommentPersonDescriptor commentPersonDescriptor =
                new CommentCommand.CommentPersonDescriptor();
        if (argMultimap.getAllValues(PREFIX_COMMENT).size() != 0) {
            parseCommentsForEdit(argMultimap.getAllValues(PREFIX_COMMENT))
                    .ifPresent(commentPersonDescriptor::setComments);
        } else {
            throw new ParseException(DeleteCommentCommand.MESSAGE_DELETECOMMENT_USAGE);
        }

        if (!commentPersonDescriptor.isAnyFieldEdited()) {
            throw new ParseException(DeleteCommentCommand.MESSAGE_DELETECOMMENT_USAGE);
        }
        return new DeleteCommentCommand(index, commentPersonDescriptor);
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
            throw new ParseException(DeleteCommentCommand.MESSAGE_DELETECOMMENT_USAGE);
        }
        for (String comment : comments) {
            String trimmedComment = comment.trim();
            if (!Comment.isValidDeleteComment(" " + trimmedComment)) {
                throw new ParseException(DeleteCommentCommand.MESSAGE_DELETECOMMENT_USAGE);
            }
        }
        return Optional.of(ParserUtil.parseComments(comments));
    }
}

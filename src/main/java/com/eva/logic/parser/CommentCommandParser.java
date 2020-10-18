package com.eva.logic.parser;

import static com.eva.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static com.eva.logic.parser.CliSyntax.PREFIX_ADDORDELETE_COMMENT;
import static com.eva.logic.parser.CliSyntax.PREFIX_COMMENT;
import static com.eva.model.comment.CommentCliSyntax.PREFIX_ADD;
import static com.eva.model.comment.CommentCliSyntax.PREFIX_DELETE;
import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import com.eva.commons.core.index.Index;
import com.eva.logic.commands.AddCommentCommand;
import com.eva.logic.commands.CommentCommand;
import com.eva.logic.commands.DeleteCommentCommand;
import com.eva.logic.commands.EditCommand;
import com.eva.logic.parser.exceptions.ParseException;
import com.eva.model.comment.Comment;

public class CommentCommandParser {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public CommentCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_COMMENT);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, CommentCommand.MESSAGE_USAGE), pe);
        }

        ArgumentMultimap addCommands = ArgumentTokenizer.tokenize(args, PREFIX_ADD);
        ArgumentMultimap deleteCommands = ArgumentTokenizer.tokenize(args, PREFIX_DELETE);

        CommentCommand.CommentPersonDescriptor commentPersonDescriptor = new CommentCommand.CommentPersonDescriptor();
        parseCommentsForEdit(argMultimap.getAllValues(PREFIX_COMMENT)).ifPresent(commentPersonDescriptor::setComments);
        if (!commentPersonDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }
        if (addCommands.getAllValues(PREFIX_ADD).size() != 0) {
            return new AddCommentCommand(index, commentPersonDescriptor);
        } else if (deleteCommands.getAllValues(PREFIX_DELETE).size() != 0) {
            return new DeleteCommentCommand(index, commentPersonDescriptor);
        } else {
            throw new ParseException("Comment has no such commands.");
        }
    }

    /**
     * For consolidated add commands.
     * @param args
     * @return commentCommand
     * @throws ParseException when there are missing fields
     */
    public CommentCommand parseAdd(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ADDORDELETE_COMMENT);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, CommentCommand.MESSAGE_USAGE), pe);
        }


        CommentCommand.CommentPersonDescriptor commentPersonDescriptor = new CommentCommand.CommentPersonDescriptor();
        parseCommentsForEdit(argMultimap.getAllValues(PREFIX_ADDORDELETE_COMMENT))
                .ifPresent(commentPersonDescriptor::setComments);
        if (!commentPersonDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }
        return new AddCommentCommand(index, commentPersonDescriptor);
    }

    /**
     * For consolidated delete commands
     * @param args
     * @throws ParseException
     */
    public CommentCommand parseDelete(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ADDORDELETE_COMMENT);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, CommentCommand.MESSAGE_USAGE), pe);
        }

        CommentCommand.CommentPersonDescriptor commentPersonDescriptor =
                new CommentCommand.CommentPersonDescriptor();
        parseCommentsForEdit(argMultimap.getAllValues(PREFIX_ADDORDELETE_COMMENT))
                .ifPresent(commentPersonDescriptor::setComments);
        if (!commentPersonDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
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

        if (comments.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> commentSet = comments.size() == 1
                && comments.contains("") ? Collections.emptySet() : comments;
        return Optional.of(ParserUtil.parseComments(commentSet));
    }
}

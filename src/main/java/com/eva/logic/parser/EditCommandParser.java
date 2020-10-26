package com.eva.logic.parser;

import static com.eva.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static com.eva.logic.commands.EditCommand.MESSAGE_NO_APPLICANTORSTAFF;
import static com.eva.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static com.eva.logic.parser.CliSyntax.PREFIX_APPLICANT;
import static com.eva.logic.parser.CliSyntax.PREFIX_COMMENT;
import static com.eva.logic.parser.CliSyntax.PREFIX_EMAIL;
import static com.eva.logic.parser.CliSyntax.PREFIX_INTERVIEW_DATE;
import static com.eva.logic.parser.CliSyntax.PREFIX_LEAVE;
import static com.eva.logic.parser.CliSyntax.PREFIX_NAME;
import static com.eva.logic.parser.CliSyntax.PREFIX_PHONE;
import static com.eva.logic.parser.CliSyntax.PREFIX_STAFF;
import static com.eva.logic.parser.CliSyntax.PREFIX_TAG;
import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import com.eva.commons.core.index.Index;
import com.eva.logic.commands.EditCommand;
import com.eva.logic.commands.EditCommand.EditPersonDescriptor;
import com.eva.logic.parser.exceptions.ParseException;
import com.eva.model.comment.Comment;
import com.eva.model.person.applicant.ApplicationStatus;
import com.eva.model.person.staff.leave.Leave;
import com.eva.model.tag.Tag;


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
        ArgumentMultimap argMultimap;

        ArgumentMultimap typeMap =
                ArgumentTokenizer.tokenize(args, PREFIX_APPLICANT, PREFIX_STAFF);

        boolean isApplicant = (!typeMap.getValue(PREFIX_APPLICANT).isEmpty());
        boolean isStaff = (!typeMap.getValue(PREFIX_STAFF).isEmpty());

        if (isApplicant) {
            argMultimap =
                    ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                            PREFIX_ADDRESS, PREFIX_TAG, PREFIX_APPLICANT, PREFIX_COMMENT, PREFIX_INTERVIEW_DATE);
        } else {
            argMultimap =
                    ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                            PREFIX_ADDRESS, PREFIX_TAG, PREFIX_STAFF, PREFIX_COMMENT, PREFIX_LEAVE);
        }

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        EditPersonDescriptor editPersonDescriptor = new EditCommand.EditPersonDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editPersonDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editPersonDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editPersonDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            editPersonDescriptor.setAddress(ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()));
        }
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editPersonDescriptor::setTags);

        parseCommentsForEdit(argMultimap.getAllValues(PREFIX_COMMENT)).ifPresent(editPersonDescriptor::setComments);

        if (argMultimap.getValue(PREFIX_INTERVIEW_DATE).isPresent()) {
            editPersonDescriptor.setInterviewDate(Optional.ofNullable(ParserUtil
                    .parseInterviewDate(argMultimap.getValue(PREFIX_INTERVIEW_DATE).get())));
        }

        if (argMultimap.getAllValues(PREFIX_LEAVE).size() > 0) {
            editPersonDescriptor.setLeaves((Set<Leave>) ParserUtil
                    .parseLeaveArgs(argMultimap.getAllValues(PREFIX_LEAVE)));
        }

        if (!editPersonDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }
        if (!argMultimap.getValue(PREFIX_APPLICANT).isEmpty()) {
            ApplicationStatus applicationStatus = new ApplicationStatus("received");
            editPersonDescriptor.setApplicationStatus(applicationStatus);
            return new EditCommand(index, editPersonDescriptor, "applicant");
        } else if (!argMultimap.getValue(PREFIX_STAFF).isEmpty()) {
            return new EditCommand(index, editPersonDescriptor, "staff");
        } else {
            throw new ParseException(MESSAGE_NO_APPLICANTORSTAFF);
        }
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Tag>> parseTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }

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

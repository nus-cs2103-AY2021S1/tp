package com.eva.logic.parser;

import static com.eva.commons.util.StringUtil.formatForParse;
import static com.eva.logic.parser.CliSyntax.PREFIX_DATE;
import static com.eva.logic.parser.CliSyntax.PREFIX_DESC;
import static com.eva.logic.parser.CliSyntax.PREFIX_TITLE;
import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import com.eva.commons.core.index.Index;
import com.eva.commons.util.DateUtil;
import com.eva.commons.util.StringUtil;
import com.eva.logic.commands.AddLeaveCommand;
import com.eva.logic.parser.exceptions.ParseException;
import com.eva.model.comment.Comment;
import com.eva.model.person.Address;
import com.eva.model.person.Email;
import com.eva.model.person.Name;
import com.eva.model.person.Phone;
import com.eva.model.person.applicant.ApplicationStatus;
import com.eva.model.person.applicant.InterviewDate;
import com.eva.model.person.staff.leave.Leave;
import com.eva.model.tag.Tag;


/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     *
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }

    /**
     * Parses the commands inside comment input
     *
     * @param comment comment input
     * @return Comment object created with input
     * @throws ParseException
     */
    public static Comment parseComment(String comment) throws ParseException {
        System.out.println("reached here");
        requireNonNull(comment);
        String trimmedComment = comment.trim();
        if (!Comment.isValidAddComment(" " + trimmedComment)
                && !Comment.isValidDeleteComment(" " + trimmedComment)) {
            throw new ParseException(Comment.MESSAGE_CONSTRAINTS);
        }
        ArgumentMultimap argMultiMap = ArgumentTokenizer.tokenize(" " + comment,
                PREFIX_DATE, PREFIX_TITLE, PREFIX_DESC);
        if (argMultiMap.getAllValues(PREFIX_DATE).size() != 0
                && argMultiMap.getAllValues(PREFIX_TITLE).size() != 0
                && argMultiMap.getAllValues(PREFIX_DESC).size() != 0) {
            String date = argMultiMap.getValue(PREFIX_DATE).get();
            if (!DateUtil.isValidDate(date)) {
                throw new ParseException(DateUtil.MESSAGE_CONSTRAINTS);
            }
            String title = argMultiMap.getValue(PREFIX_TITLE).get();
            String desc = argMultiMap.getValue(PREFIX_DESC).get();
            return new Comment(DateUtil.dateParsed(date), desc, title);
        } else {
            String title = argMultiMap.getValue(PREFIX_TITLE).get();
            return new Comment(title);
        }
    }

    /**
     * Parses {@code String leaveDates} into a {@code Leave}.
     * Leading and trailing whitespaces will be trimmed.
     * Assumption: no more than 2 leave dates are entered.
     *
     * @throws ParseException if the given {@code leaveDate} is invalid.
     */
    public static Leave parseLeave(List<String> leaveDates) throws ParseException {
        requireNonNull(leaveDates);
        if (leaveDates.size() > 2 || leaveDates.size() < 1) {
            throw new ParseException(AddLeaveCommand.MESSAGE_USAGE);
        }
        String trimmedDate = leaveDates.get(0).trim();
        if (!DateUtil.isValidDate(trimmedDate)) {
            throw new ParseException(DateUtil.MESSAGE_CONSTRAINTS);
        }
        if (leaveDates.size() > 1) {
            String trimmedEndDate = leaveDates.get(1).trim();
            return new Leave(trimmedDate, trimmedEndDate);
        } else {
            return new Leave(trimmedDate);
        }
    }

    /**
     * Parses the date arguments inside individual leave input
     *
     * @param leaveArgsList add leave input
     * @return list of leaves created.
     * @throws ParseException if the any leaveArgs in {@code leaveArgsList} is invalid.
     */
    public static List<Leave> parseLeaveArgs(List<String> leaveArgsList) throws ParseException {
        requireNonNull(leaveArgsList);
        ArrayList<Leave> leaves = new ArrayList<>();
        for (String leaveArgs : leaveArgsList) {
            ArgumentMultimap dateArgMultimap = ArgumentTokenizer.tokenize(formatForParse(leaveArgs), PREFIX_DATE);
            List<String> leaveDates = dateArgMultimap.getAllValues(PREFIX_DATE);
            Leave leave = parseLeave(leaveDates);
            leaves.add(leave);
        }
        return leaves;
    }

    /**
     * Converts string with details of comments into a Comment object
     *
     * @param comments strings of details
     * @return Set of comment objects
     * @throws ParseException
     */
    public static Set<Comment> parseComments(Collection<String> comments) throws ParseException {
        requireNonNull(comments);
        final Set<Comment> commentSet = new HashSet<>();
        for (String comment : comments) {
            commentSet.add(parseComment(comment));
        }
        return commentSet;
    }


    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}
     */
    public static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Parses {@code String leaveDates} into a {@code Leave}.
     * Leading and trailing whitespaces will be trimmed.
     * Assumption: no more than 2 leave dates are entered.
     */
    public static InterviewDate parseInterviewDate(String value) throws ParseException {
        if (!DateUtil.isValidDate(value.trim())) {
            throw new ParseException(DateUtil.MESSAGE_CONSTRAINTS);
        }
        return new InterviewDate(value.trim());
    }

    /**
     * Parses {@code String value} into a {@code Application Status}.
     */
    public static ApplicationStatus parseApplicationStatus(String value) throws ParseException {
        if (!ApplicationStatus.isValidApplicationStatus(value)) {
            throw new ParseException(ApplicationStatus.MESSAGE_CONSTRAINTS);
        }
        return new ApplicationStatus(value);
    }
}

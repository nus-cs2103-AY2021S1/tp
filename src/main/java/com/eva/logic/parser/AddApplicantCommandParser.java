package com.eva.logic.parser;

import static com.eva.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static com.eva.logic.parser.CliSyntax.PREFIX_COMMENT;
import static com.eva.logic.parser.CliSyntax.PREFIX_EMAIL;
import static com.eva.logic.parser.CliSyntax.PREFIX_INTERVIEW_DATE;
import static com.eva.logic.parser.CliSyntax.PREFIX_NAME;
import static com.eva.logic.parser.CliSyntax.PREFIX_PHONE;
import static com.eva.logic.parser.CliSyntax.PREFIX_TAG;
import static com.eva.logic.parser.ParserUtil.arePrefixesPresent;

import java.util.Optional;
import java.util.Set;

import com.eva.commons.core.Messages;
import com.eva.logic.commands.AddApplicantCommand;
import com.eva.logic.parser.exceptions.ParseException;
import com.eva.model.comment.Comment;
import com.eva.model.person.Address;
import com.eva.model.person.Email;
import com.eva.model.person.Name;
import com.eva.model.person.Phone;
import com.eva.model.person.applicant.Applicant;
import com.eva.model.person.applicant.ApplicationStatus;
import com.eva.model.person.applicant.InterviewDate;
import com.eva.model.tag.Tag;


public class AddApplicantCommandParser implements Parser<AddApplicantCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddApplicantCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                        PREFIX_ADDRESS, PREFIX_TAG, PREFIX_COMMENT, PREFIX_INTERVIEW_DATE);

        // compulsory information of a applicant
        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_PHONE, PREFIX_EMAIL)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    AddApplicantCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        Set<Comment> commentList = ParserUtil.parseComments(argMultimap.getAllValues(PREFIX_COMMENT));
        ApplicationStatus applicationStatus = new ApplicationStatus("received");
        Optional<InterviewDate> interviewDate = arePrefixesPresent(argMultimap, PREFIX_INTERVIEW_DATE)
                ? Optional.of(ParserUtil.parseInterviewDate(argMultimap.getValue(PREFIX_INTERVIEW_DATE).get()))
                : Optional.empty();

        Applicant applicant = new Applicant(name, phone, email, address, tagList,
                commentList, interviewDate, applicationStatus);

        return new AddApplicantCommand(applicant);
    }
}

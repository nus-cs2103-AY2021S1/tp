package seedu.medibook.logic.parser;

import static seedu.medibook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.medibook.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.medibook.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.medibook.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_BLOOD_TYPE;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_HEIGHT;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_IC;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_WEIGHT;
import static seedu.medibook.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.medibook.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.medibook.logic.commands.FindCommand;
import seedu.medibook.model.patient.FieldContainsKeywordsPredicate;
import seedu.medibook.model.patient.Patient;

public class FindCommandParserTest {
    private static final String SINGLE_WHITESPACE = " ";

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        String[] nameKeywords = new String[]{ "Alice", "Bob" };
        String[] icKeywords = new String[]{ "T0232323I", "S9234567A" };
        String[] dobKeywords = new String[]{ "14-07-2002", "01-03-1992" };
        String[] phoneKeywords = new String[]{ "94351253", "95352563" };
        String[] addressKeywords = new String[]{ "Clementi", "Changi" };
        String[] emailKeywords = new String[]{ "foo@bar.com", "bar@foo.com" };
        String[] heightKeywords = new String[]{ "178", "159" };
        String[] weightKeywords = new String[]{ "70.2", "54.9" };
        String[] bloodTypeKeywords = new String[]{ "A+", "B+" };

        String nameArg = generateArgument(PREFIX_NAME, nameKeywords);
        String icArg = generateArgument(PREFIX_IC, icKeywords);
        String dobArg = generateArgument(PREFIX_DATE, dobKeywords);
        String phoneArg = generateArgument(PREFIX_PHONE, phoneKeywords);
        String addressArg = generateArgument(PREFIX_ADDRESS, addressKeywords);
        String emailArg = generateArgument(PREFIX_EMAIL, emailKeywords);
        String heightArg = generateArgument(PREFIX_HEIGHT, heightKeywords);
        String weightArg = generateArgument(PREFIX_WEIGHT, weightKeywords);
        String bloodTypeArg = generateArgument(PREFIX_BLOOD_TYPE, bloodTypeKeywords);

        String[] nameKeywordsAlt = new String[]{ "Charlie", "Donald" };
        String[] icKeywordsAlt = new String[]{ "G1812399T", "S8892300F" };
        String[] dobKeywordsAlt = new String[]{ "07-08-1977", "19-01-1990" };
        String[] phoneKeywordsAlt = new String[]{ "82314003", "64308920" };
        String[] addressKeywordsAlt = new String[]{ "Woodlands", "Punggol" };
        String[] emailKeywordsAlt = new String[]{ "testing@example.org", "exaple@testing.org" };
        String[] heightKeywordsAlt = new String[]{ "171", "167" };
        String[] weightKeywordsAlt = new String[]{ "66.6", "63.9" };
        String[] bloodTypeKeywordsAlt = new String[]{ "AB-", "B-" };

        String nameArgAlt = generateArgument(PREFIX_NAME, nameKeywordsAlt);
        String icArgAlt = generateArgument(PREFIX_IC, icKeywordsAlt);
        String dobArgAlt = generateArgument(PREFIX_DATE, dobKeywordsAlt);
        String phoneArgAlt = generateArgument(PREFIX_PHONE, phoneKeywordsAlt);
        String addressArgAlt = generateArgument(PREFIX_ADDRESS, addressKeywordsAlt);
        String emailArgAlt = generateArgument(PREFIX_EMAIL, emailKeywordsAlt);
        String heightArgAlt = generateArgument(PREFIX_HEIGHT, heightKeywordsAlt);
        String weightArgAlt = generateArgument(PREFIX_WEIGHT, weightKeywordsAlt);
        String bloodTypeArgAlt = generateArgument(PREFIX_BLOOD_TYPE, bloodTypeKeywordsAlt);

        Predicate<Patient> namePred =
                new FieldContainsKeywordsPredicate(Arrays.asList(nameKeywords), PREFIX_NAME);
        Predicate<Patient> icPred =
                new FieldContainsKeywordsPredicate(Arrays.asList(icKeywords), PREFIX_IC);
        Predicate<Patient> dobPred =
                new FieldContainsKeywordsPredicate(Arrays.asList(dobKeywords), PREFIX_DATE);
        Predicate<Patient> phonePred =
                new FieldContainsKeywordsPredicate(Arrays.asList(phoneKeywords), PREFIX_PHONE);
        Predicate<Patient> addressPred =
                new FieldContainsKeywordsPredicate(Arrays.asList(addressKeywords), PREFIX_ADDRESS);
        Predicate<Patient> emailPred =
                new FieldContainsKeywordsPredicate(Arrays.asList(emailKeywords), PREFIX_EMAIL);
        Predicate<Patient> heightPred =
                new FieldContainsKeywordsPredicate(Arrays.asList(heightKeywords), PREFIX_HEIGHT);
        Predicate<Patient> weightPred =
                new FieldContainsKeywordsPredicate(Arrays.asList(weightKeywords), PREFIX_WEIGHT);
        Predicate<Patient> bloodTypePred =
                new FieldContainsKeywordsPredicate(Arrays.asList(bloodTypeKeywords), PREFIX_BLOOD_TYPE);

        List<Predicate<Patient>> predicates = Arrays.asList(namePred, icPred, dobPred, phonePred, addressPred,
                emailPred, heightPred, weightPred, bloodTypePred);

        FindCommand expectedFindCommand = new FindCommand(predicates);

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + nameArg + icArg + dobArg + phoneArg + addressArg
                + emailArg + heightArg + weightArg + bloodTypeArg,
                expectedFindCommand);

        // calling with arguments in different order returns the same FindCommand
        assertParseSuccess(parser, phoneArg + icArg + dobArg + nameArg + bloodTypeArg + emailArg
                         + addressArg + heightArg + weightArg,
                expectedFindCommand);

        // multiple whitespaces between, before and after keywords
        String nameArgWithWhitespaces = "  \t " + nameArg.replaceAll(" ", " \n \t ");
        String icArgWithWhitespaces = "  " + icArg.replaceAll(" ", " \n   \r \t ") + "  \t ";
        String dobArgWithWhitespaces = dobArg.replaceAll(" ", " \n \r   ");
        String phoneArgWithWhitespaces = phoneArg.replaceAll(" ", "  \r  \t  ");

        assertParseSuccess(parser, nameArgWithWhitespaces + icArgWithWhitespaces + dobArgWithWhitespaces
                + phoneArgWithWhitespaces + addressArg + emailArg + heightArg + weightArg + bloodTypeArg,
                expectedFindCommand);

        // multiple names - last name accepted
        assertParseSuccess(parser, nameArgAlt + nameArg + icArg + dobArg + phoneArg + addressArg
                        + emailArg + heightArg + weightArg + bloodTypeArg,
                expectedFindCommand);

        // multiple ics - last ic accepted
        assertParseSuccess(parser, nameArg + icArgAlt + icArg + dobArg + phoneArg + addressArg
                        + emailArg + heightArg + weightArg + bloodTypeArg,
                expectedFindCommand);

        // multiple dates of birth - last date of birth accepted
        assertParseSuccess(parser, nameArg + icArg + dobArgAlt + dobArg + phoneArg + addressArg
                        + emailArg + heightArg + weightArg + bloodTypeArg,
                expectedFindCommand);

        // multiple phones - last phone accepted
        assertParseSuccess(parser, nameArg + icArg + dobArg + phoneArgAlt + phoneArg + addressArg
                        + emailArg + heightArg + weightArg + bloodTypeArg,
                expectedFindCommand);

        // multiple address - last address accepted
        assertParseSuccess(parser, nameArg + icArg + dobArg + phoneArg + addressArgAlt + addressArg
                        + emailArg + heightArg + weightArg + bloodTypeArg,
                expectedFindCommand);

        // multiple emails - last emails accepted
        assertParseSuccess(parser, nameArg + icArg + dobArg + phoneArg + addressArg
                        + emailArgAlt + emailArg + heightArg + weightArg + bloodTypeArg,
                expectedFindCommand);

        // multiple heights - last heights accepted
        assertParseSuccess(parser, nameArg + icArg + dobArg + phoneArg + addressArg
                        + emailArg + heightArgAlt + heightArg + weightArg + bloodTypeArg,
                expectedFindCommand);

        // multiple weights - last weights accepted
        assertParseSuccess(parser, nameArg + icArg + dobArg + phoneArg + addressArg
                        + emailArg + heightArg + weightArgAlt + weightArg + bloodTypeArg,
                expectedFindCommand);

        // multiple blood types - last blood types accepted
        assertParseSuccess(parser, nameArg + icArg + dobArg + phoneArg + addressArg
                        + emailArg + heightArg + weightArg + bloodTypeArgAlt + bloodTypeArg,
                expectedFindCommand);
    }

    @Test
    public void parse_fieldsMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE);

        // whitespace as argument
        assertParseFailure(parser, "       ", expectedMessage);

        // no prefixes provided
        assertParseFailure(parser, "Alice", expectedMessage);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_AMY, expectedMessage);
    }

    private String generateArgument(Prefix prefix, String... keywords) {
        return SINGLE_WHITESPACE + prefix + String.join(" ", keywords);
    }

}

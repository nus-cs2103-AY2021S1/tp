package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_IRAS;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_MAYBANK;
import static seedu.address.logic.commands.CommandTestUtil.COMPANY_NAME_DESC_IRAS;
import static seedu.address.logic.commands.CommandTestUtil.COMPANY_NAME_DESC_MAYBANK;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_IRAS;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_MAYBANK;
import static seedu.address.logic.commands.CommandTestUtil.EMPTY_COMPANY_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_JOB_PRIORITY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_JOB_TITLE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_VACANCY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.JOB_TITLE_DESC_MAYBANK;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_IRAS;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_MAYBANK;
import static seedu.address.logic.commands.CommandTestUtil.PRIORITY_DESC_IRAS;
import static seedu.address.logic.commands.CommandTestUtil.PRIORITY_DESC_MAYBANK;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_IRAS;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_MAYBANK;
import static seedu.address.logic.commands.CommandTestUtil.VACANCY_DESC_IRAS;
import static seedu.address.logic.commands.CommandTestUtil.VACANCY_DESC_MAYBANK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_IRAS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_MAYBANK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_NAME_IRAS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_NAME_MAYBANK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_IRAS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_MAYBANK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_JOB_TITLE_IRAS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_JOB_TITLE_MAYBANK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_IRAS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_MAYBANK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRIORITY_IRAS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRIORITY_MAYBANK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_IRAS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_MAYBANK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_VACANCY_IRAS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_VACANCY_MAYBANK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_JOB;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_JOB;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_JOB;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditJobCommand;
import seedu.address.logic.commands.EditJobCommand.EditJobDescriptor;
import seedu.address.model.information.Address;
import seedu.address.model.information.CompanyName;
import seedu.address.model.information.Email;
import seedu.address.model.information.Name;
import seedu.address.model.information.Phone;
import seedu.address.model.information.Priority;
import seedu.address.model.information.Vacancy;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.EditJobDescriptorBuilder;

public class EditJobCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditJobCommand.MESSAGE_USAGE);

    private EditJobCommandParser parser = new EditJobCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_JOB_TITLE_IRAS, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditJobCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + JOB_TITLE_DESC_MAYBANK, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + JOB_TITLE_DESC_MAYBANK, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_JOB_TITLE_DESC, Name.MESSAGE_CONSTRAINTS); // invalid title
        assertParseFailure(parser, "1" + EMPTY_COMPANY_NAME_DESC, CompanyName.MESSAGE_CONSTRAINTS);
        // empty company name
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS); // invalid phone
        assertParseFailure(parser, "1" + INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parser, "1" + INVALID_ADDRESS_DESC, Address.MESSAGE_CONSTRAINTS); // invalid address
        assertParseFailure(parser, "1" + INVALID_JOB_PRIORITY_DESC, Priority.MESSAGE_CONSTRAINTS);
        // invalid priority
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag
        assertParseFailure(parser, "1" + INVALID_VACANCY_DESC, Vacancy.MESSAGE_CONSTRAINTS); // invalid vacancy

        // invalid phone followed by valid email
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC + EMAIL_DESC_IRAS, Phone.MESSAGE_CONSTRAINTS);

        // valid phone followed by invalid phone. The test case for invalid phone followed by valid phone
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + PHONE_DESC_MAYBANK + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Person} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + TAG_DESC_IRAS + TAG_DESC_MAYBANK + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_DESC_IRAS + TAG_EMPTY + TAG_DESC_MAYBANK, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY + TAG_DESC_IRAS + TAG_DESC_MAYBANK, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + EMPTY_COMPANY_NAME_DESC + INVALID_EMAIL_DESC
                        + VALID_ADDRESS_IRAS + VALID_PHONE_IRAS, CompanyName.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_JOB;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_MAYBANK + TAG_DESC_MAYBANK
                + COMPANY_NAME_DESC_MAYBANK + PRIORITY_DESC_MAYBANK + EMAIL_DESC_MAYBANK
                + ADDRESS_DESC_MAYBANK + JOB_TITLE_DESC_MAYBANK + TAG_DESC_IRAS + VACANCY_DESC_MAYBANK;

        EditJobDescriptor descriptor = new EditJobDescriptorBuilder().withJobTitle(VALID_JOB_TITLE_MAYBANK)
                .withCompanyName(VALID_COMPANY_NAME_MAYBANK).withPhone(VALID_PHONE_MAYBANK)
                .withEmail(VALID_EMAIL_MAYBANK).withAddress(VALID_ADDRESS_MAYBANK)
                .withTags(VALID_TAG_IRAS, VALID_TAG_MAYBANK).withPriority(VALID_PRIORITY_MAYBANK)
                .withVacancy(VALID_VACANCY_MAYBANK).build();
        EditJobCommand expectedCommand = new EditJobCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_JOB;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_IRAS + EMAIL_DESC_MAYBANK;

        EditJobDescriptor descriptor = new EditJobDescriptorBuilder().withPhone(VALID_PHONE_IRAS)
                .withEmail(VALID_EMAIL_MAYBANK).build();
        EditJobCommand expectedCommand = new EditJobCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        Index targetIndex;
        String userInput;
        EditJobDescriptor descriptor;

        // job title
        targetIndex = INDEX_THIRD_JOB;
        userInput = targetIndex.getOneBased() + JOB_TITLE_DESC_MAYBANK;
        descriptor = new EditJobDescriptorBuilder().withJobTitle(VALID_JOB_TITLE_MAYBANK).build();
        EditJobCommand expectedCommand = new EditJobCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // company name
        targetIndex = INDEX_SECOND_JOB;
        userInput = targetIndex.getOneBased() + COMPANY_NAME_DESC_IRAS;
        descriptor = new EditJobDescriptorBuilder().withCompanyName(VALID_COMPANY_NAME_IRAS).build();
        expectedCommand = new EditJobCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        targetIndex = INDEX_FIRST_JOB;
        userInput = targetIndex.getOneBased() + PHONE_DESC_MAYBANK;
        descriptor = new EditJobDescriptorBuilder().withPhone(VALID_PHONE_MAYBANK).build();
        expectedCommand = new EditJobCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        targetIndex = INDEX_SECOND_JOB;
        userInput = targetIndex.getOneBased() + EMAIL_DESC_IRAS;
        descriptor = new EditJobDescriptorBuilder().withEmail(VALID_EMAIL_IRAS).build();
        expectedCommand = new EditJobCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // address
        targetIndex = INDEX_THIRD_JOB;
        userInput = targetIndex.getOneBased() + ADDRESS_DESC_MAYBANK;
        descriptor = new EditJobDescriptorBuilder().withAddress(VALID_ADDRESS_MAYBANK).build();
        expectedCommand = new EditJobCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // priority
        targetIndex = INDEX_FIRST_JOB;
        userInput = targetIndex.getOneBased() + PRIORITY_DESC_MAYBANK;
        descriptor = new EditJobDescriptorBuilder().withPriority(VALID_PRIORITY_MAYBANK).build();
        expectedCommand = new EditJobCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // vacancy
        targetIndex = INDEX_THIRD_JOB;
        userInput = targetIndex.getOneBased() + VACANCY_DESC_IRAS;
        descriptor = new EditJobDescriptorBuilder().withVacancy(VALID_VACANCY_IRAS).build();
        expectedCommand = new EditJobCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        targetIndex = INDEX_SECOND_JOB;
        userInput = targetIndex.getOneBased() + TAG_DESC_IRAS;
        descriptor = new EditJobDescriptorBuilder().withTags(VALID_TAG_IRAS).build();
        expectedCommand = new EditJobCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_JOB;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_MAYBANK + ADDRESS_DESC_MAYBANK
                + EMAIL_DESC_MAYBANK + TAG_DESC_MAYBANK + PHONE_DESC_MAYBANK + VACANCY_DESC_MAYBANK
                + ADDRESS_DESC_MAYBANK + EMAIL_DESC_MAYBANK + PRIORITY_DESC_MAYBANK
                + PHONE_DESC_IRAS + ADDRESS_DESC_IRAS + EMAIL_DESC_IRAS + PRIORITY_DESC_IRAS
                + VACANCY_DESC_IRAS + TAG_DESC_IRAS;

        EditJobDescriptor descriptor = new EditJobDescriptorBuilder().withPhone(VALID_PHONE_IRAS)
                .withEmail(VALID_EMAIL_IRAS).withAddress(VALID_ADDRESS_IRAS).withPriority(VALID_PRIORITY_IRAS)
                .withTags(VALID_TAG_IRAS, VALID_TAG_MAYBANK).withVacancy(VALID_VACANCY_IRAS).build();
        EditJobCommand expectedCommand = new EditJobCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_JOB;
        String userInput = targetIndex.getOneBased() + INVALID_PHONE_DESC + PHONE_DESC_IRAS;
        EditJobDescriptor descriptor = new EditJobDescriptorBuilder().withPhone(VALID_PHONE_IRAS).build();
        EditJobCommand expectedCommand = new EditJobCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + EMAIL_DESC_MAYBANK + INVALID_PHONE_DESC + ADDRESS_DESC_MAYBANK
                + VACANCY_DESC_MAYBANK + PHONE_DESC_MAYBANK + PRIORITY_DESC_MAYBANK;
        descriptor = new EditJobDescriptorBuilder().withPhone(VALID_PHONE_MAYBANK).withEmail(VALID_EMAIL_MAYBANK)
                .withAddress(VALID_ADDRESS_MAYBANK).withPriority(VALID_PRIORITY_MAYBANK)
                .withVacancy(VALID_VACANCY_MAYBANK).build();
        expectedCommand = new EditJobCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD_JOB;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        EditJobDescriptor descriptor = new EditJobDescriptorBuilder().withTags().build();
        EditJobCommand expectedCommand = new EditJobCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}

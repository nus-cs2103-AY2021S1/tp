package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
//import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
//import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
//import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PAY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ROLE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PAY_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PAY_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.ROLE_DESC_CASHIER;
import static seedu.address.logic.commands.CommandTestUtil.ROLE_DESC_CHEF;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PAY_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PAY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROLE_CASHIER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROLE_CHEF;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
//import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_WORKER;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_WORKER;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_WORKER;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.WorkerEditCommand;
import seedu.address.logic.commands.WorkerEditCommand.EditWorkerDescriptor;
import seedu.address.model.tag.Role;
import seedu.address.model.worker.Address;
//import seedu.address.model.worker.Email;
import seedu.address.model.worker.Name;
import seedu.address.model.worker.Pay;
import seedu.address.model.worker.Phone;
import seedu.address.testutil.EditWorkerDescriptorBuilder;
//import seedu.address.model.tag.Tag;


public class WorkerEditCommandParserTest {

    private static final String ROLE_EMPTY = " " + PREFIX_ROLE;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, WorkerEditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", WorkerEditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS); // invalid phone
        assertParseFailure(parser, "1" + INVALID_PAY_DESC, Pay.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parser, "1" + INVALID_ADDRESS_DESC, Address.MESSAGE_CONSTRAINTS); // invalid address
        assertParseFailure(parser, "1" + INVALID_ROLE_DESC, Role.MESSAGE_CONSTRAINTS); // invalid tag

        // invalid phone followed by valid email
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC + PAY_DESC_AMY, Phone.MESSAGE_CONSTRAINTS);

        // valid phone followed by invalid phone. The test case for invalid phone followed by valid phone
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + PHONE_DESC_BOB + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_ROLE} alone will reset the tags of the {@code Worker} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + ROLE_DESC_CASHIER + ROLE_DESC_CHEF + ROLE_EMPTY, Role.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + ROLE_DESC_CASHIER + ROLE_EMPTY + ROLE_DESC_CHEF, Role.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + ROLE_EMPTY + ROLE_DESC_CASHIER + ROLE_DESC_CHEF, Role.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_PAY_DESC + VALID_ADDRESS_AMY + VALID_PHONE_AMY,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_WORKER;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_BOB + ROLE_DESC_CASHIER
                + PAY_DESC_AMY + ADDRESS_DESC_AMY + NAME_DESC_AMY + ROLE_DESC_CHEF;
        EditWorkerDescriptor descriptor = new EditWorkerDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_BOB).withPay(VALID_PAY_AMY).withAddress(VALID_ADDRESS_AMY)
                .withRoles(VALID_ROLE_CHEF, VALID_ROLE_CASHIER).build();
        WorkerEditCommand expectedCommand = new WorkerEditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_WORKER;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_BOB + PAY_DESC_AMY;

        EditWorkerDescriptor descriptor = new EditWorkerDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withPay(VALID_PAY_AMY).build();
        WorkerEditCommand expectedCommand = new WorkerEditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_WORKER;
        String userInput = targetIndex.getOneBased() + NAME_DESC_AMY;
        EditWorkerDescriptor descriptor = new EditWorkerDescriptorBuilder().withName(VALID_NAME_AMY).build();
        WorkerEditCommand expectedCommand = new WorkerEditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = targetIndex.getOneBased() + PHONE_DESC_AMY;
        descriptor = new EditWorkerDescriptorBuilder().withPhone(VALID_PHONE_AMY).build();
        expectedCommand = new WorkerEditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // pay
        userInput = targetIndex.getOneBased() + PAY_DESC_AMY;
        descriptor = new EditWorkerDescriptorBuilder().withPay(VALID_PAY_AMY).build();
        expectedCommand = new WorkerEditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // address
        userInput = targetIndex.getOneBased() + ADDRESS_DESC_AMY;
        descriptor = new EditWorkerDescriptorBuilder().withAddress(VALID_ADDRESS_AMY).build();
        expectedCommand = new WorkerEditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // rolss
        userInput = targetIndex.getOneBased() + ROLE_DESC_CASHIER;
        descriptor = new EditWorkerDescriptorBuilder().withRoles(VALID_ROLE_CASHIER).build();
        expectedCommand = new WorkerEditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_WORKER;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_AMY + ADDRESS_DESC_AMY + PAY_DESC_AMY
                + ROLE_DESC_CASHIER + PHONE_DESC_AMY + ADDRESS_DESC_AMY + PAY_DESC_AMY + ROLE_DESC_CHEF
                + PHONE_DESC_BOB + ADDRESS_DESC_BOB + PAY_DESC_BOB + ROLE_DESC_CASHIER;

        EditWorkerDescriptor descriptor = new EditWorkerDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withPay(VALID_PAY_BOB).withAddress(VALID_ADDRESS_BOB).withRoles(VALID_ROLE_CASHIER, VALID_ROLE_CHEF)
                .build();
        WorkerEditCommand expectedCommand = new WorkerEditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_WORKER;
        String userInput = targetIndex.getOneBased() + INVALID_PHONE_DESC + PHONE_DESC_BOB;
        EditWorkerDescriptor descriptor = new EditWorkerDescriptorBuilder().withPhone(VALID_PHONE_BOB).build();
        WorkerEditCommand expectedCommand = new WorkerEditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + PAY_DESC_BOB + INVALID_PHONE_DESC + ADDRESS_DESC_BOB
                + PHONE_DESC_BOB;
        descriptor = new EditWorkerDescriptorBuilder().withPhone(VALID_PHONE_BOB).withPay(VALID_PAY_BOB)
                .withAddress(VALID_ADDRESS_BOB).build();
        expectedCommand = new WorkerEditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetRoles_success() {
        Index targetIndex = INDEX_THIRD_WORKER;
        String userInput = targetIndex.getOneBased() + ROLE_EMPTY;

        EditWorkerDescriptor descriptor = new EditWorkerDescriptorBuilder().withRoles().build();
        WorkerEditCommand expectedCommand = new WorkerEditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}

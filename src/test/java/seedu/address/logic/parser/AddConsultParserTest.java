package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_DUPLICATE_PREFIXES;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DAY_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DAY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TIME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TYPE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.LOCATION_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TIME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.TYPE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DAY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TYPE_BOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalConsults.GROUP_CONSULT;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddConsultCommand;
import seedu.address.model.consultation.Address;
import seedu.address.model.consultation.Consultation;
import seedu.address.model.consultation.Day;
import seedu.address.model.consultation.Time;
import seedu.address.model.consultation.Type;
import seedu.address.testutil.ConsultationBuilder;



public class AddConsultParserTest {
    private AddConsultParser parser = new AddConsultParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Consultation expectedPerson = new ConsultationBuilder(GROUP_CONSULT).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + DAY_DESC_BOB
                        + TIME_DESC_BOB + LOCATION_DESC_BOB + TYPE_DESC_BOB,
                new AddConsultCommand(expectedPerson));

        assertParseSuccess(parser, NAME_DESC_BOB + DAY_DESC_BOB
                        + TIME_DESC_BOB + LOCATION_DESC_BOB + TYPE_DESC_BOB,
                new AddConsultCommand(expectedPerson));
    }

    @Test
    public void parse_duplicateFieldsPresent_failure() {
        // multiple names - name flagged as duplicate
        assertParseFailure(parser, NAME_DESC_AMY + NAME_DESC_BOB + DAY_DESC_BOB
                        + TIME_DESC_BOB + LOCATION_DESC_BOB + TYPE_DESC_BOB,
                String.format(MESSAGE_DUPLICATE_PREFIXES, PREFIX_NAME));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddConsultCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + DAY_DESC_BOB
                        + TIME_DESC_BOB + LOCATION_DESC_BOB + TYPE_DESC_BOB,
                expectedMessage);

        // missing day prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_DAY_BOB
                        + TIME_DESC_BOB + LOCATION_DESC_BOB + TYPE_DESC_BOB,
                expectedMessage);

        // missing time prefix
        assertParseFailure(parser, NAME_DESC_BOB + DAY_DESC_BOB
                        + VALID_TIME_BOB + LOCATION_DESC_BOB + TYPE_DESC_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_DAY_BOB
                        + VALID_TIME_BOB + VALID_LOCATION_BOB + VALID_TYPE_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid day
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_DAY_DESC
                        + TIME_DESC_BOB + LOCATION_DESC_BOB + TYPE_DESC_BOB,
                Day.MESSAGE_CONSTRAINTS);

        // invalid time
        assertParseFailure(parser, NAME_DESC_BOB + DAY_DESC_BOB
                        + INVALID_TIME_DESC + LOCATION_DESC_BOB + TYPE_DESC_BOB,
                Time.MESSAGE_CONSTRAINTS);

        // invalid location
        assertParseFailure(parser, NAME_DESC_BOB + DAY_DESC_BOB
                        + TIME_DESC_BOB + INVALID_ADDRESS_DESC + TYPE_DESC_BOB,
                Address.MESSAGE_CONSTRAINTS);

        // invalid type
        assertParseFailure(parser, NAME_DESC_BOB + DAY_DESC_BOB
                        + TIME_DESC_BOB + LOCATION_DESC_BOB + INVALID_TYPE_DESC,
                Type.MESSAGE_CONSTRAINTS);


        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + DAY_DESC_BOB
                        + TIME_DESC_BOB + LOCATION_DESC_BOB + TYPE_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddConsultCommand.MESSAGE_USAGE));
    }
}

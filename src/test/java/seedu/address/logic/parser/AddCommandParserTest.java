package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.ID_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ID_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ID_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_SPECIES_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.SPECIES_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.SPECIES_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SPECIES_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalAnimals.AMY;
import static seedu.address.testutil.TypicalAnimals.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.model.animal.Email;
import seedu.address.model.animal.Id;
import seedu.address.model.animal.Name;
import seedu.address.model.animal.Animal;
import seedu.address.model.animal.Species;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.AnimalBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Animal expectedAnimal = new AnimalBuilder(BOB).withTags(VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + ID_DESC_BOB + EMAIL_DESC_BOB
                + SPECIES_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedAnimal));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + ID_DESC_BOB + EMAIL_DESC_BOB
                + SPECIES_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedAnimal));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + ID_DESC_AMY + ID_DESC_BOB + EMAIL_DESC_BOB
                + SPECIES_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedAnimal));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + ID_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB
                + SPECIES_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedAnimal));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, NAME_DESC_BOB + ID_DESC_BOB + EMAIL_DESC_BOB + SPECIES_DESC_AMY
                + SPECIES_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedAnimal));

        // multiple tags - all accepted
        Animal expectedAnimalMultipleTags = new AnimalBuilder(BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();

        assertParseSuccess(parser, NAME_DESC_BOB + ID_DESC_BOB + EMAIL_DESC_BOB + SPECIES_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, new AddCommand(expectedAnimalMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Animal expectedAnimal = new AnimalBuilder(AMY).withTags().build();
        assertParseSuccess(parser, NAME_DESC_AMY + ID_DESC_AMY + EMAIL_DESC_AMY + SPECIES_DESC_AMY,
                new AddCommand(expectedAnimal));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + ID_DESC_BOB + EMAIL_DESC_BOB + SPECIES_DESC_BOB,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_ID_BOB + EMAIL_DESC_BOB + SPECIES_DESC_BOB,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + ID_DESC_BOB + VALID_EMAIL_BOB + SPECIES_DESC_BOB,
                expectedMessage);

        // missing address prefix
        assertParseFailure(parser, NAME_DESC_BOB + ID_DESC_BOB + EMAIL_DESC_BOB + VALID_SPECIES_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_ID_BOB + VALID_EMAIL_BOB + VALID_SPECIES_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + ID_DESC_BOB + EMAIL_DESC_BOB + SPECIES_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_ID_DESC + EMAIL_DESC_BOB + SPECIES_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Id.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + ID_DESC_BOB + INVALID_EMAIL_DESC + SPECIES_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Email.MESSAGE_CONSTRAINTS);

        // invalid species
        assertParseFailure(parser, NAME_DESC_BOB + ID_DESC_BOB + EMAIL_DESC_BOB + INVALID_SPECIES_DESC
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Species.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + ID_DESC_BOB + EMAIL_DESC_BOB + SPECIES_DESC_BOB
                + INVALID_TAG_DESC + VALID_TAG_FRIEND, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + ID_DESC_BOB + EMAIL_DESC_BOB + INVALID_SPECIES_DESC,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + ID_DESC_BOB + EMAIL_DESC_BOB
                + SPECIES_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}

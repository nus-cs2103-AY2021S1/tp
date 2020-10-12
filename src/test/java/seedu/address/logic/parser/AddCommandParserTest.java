package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ID_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ID_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ID_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MEDICAL_CONDITION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_SPECIES_DESC;
import static seedu.address.logic.commands.CommandTestUtil.MEDICAL_CONDITION_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.MEDICAL_CONDITION_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.SPECIES_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.SPECIES_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEDICAL_CONDITION_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEDICAL_CONDITION_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SPECIES_BOB;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalAnimals.AMY;
import static seedu.address.testutil.TypicalAnimals.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.model.animal.Animal;
import seedu.address.model.animal.Id;
import seedu.address.model.animal.Name;
import seedu.address.model.animal.Species;
import seedu.address.model.medicalcondition.MedicalCondition;
import seedu.address.testutil.AnimalBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Animal expectedAnimal = new AnimalBuilder(BOB).withMedicalConditions(VALID_MEDICAL_CONDITION_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + ID_DESC_BOB
                + SPECIES_DESC_BOB + MEDICAL_CONDITION_DESC_FRIEND, new AddCommand(expectedAnimal));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + ID_DESC_BOB
                + SPECIES_DESC_BOB + MEDICAL_CONDITION_DESC_FRIEND, new AddCommand(expectedAnimal));

        // multiple ids - last id accepted
        assertParseSuccess(parser, NAME_DESC_BOB + ID_DESC_AMY + ID_DESC_BOB
                + SPECIES_DESC_BOB + MEDICAL_CONDITION_DESC_FRIEND, new AddCommand(expectedAnimal));

        // multiple species - last species accepted
        assertParseSuccess(parser, NAME_DESC_BOB + ID_DESC_BOB + SPECIES_DESC_AMY
                + SPECIES_DESC_BOB + MEDICAL_CONDITION_DESC_FRIEND, new AddCommand(expectedAnimal));

        // multiple medicalConditions - all accepted
        Animal expectedAnimalMultipleMedicalConditions = new AnimalBuilder(BOB)
                .withMedicalConditions(VALID_MEDICAL_CONDITION_FRIEND, VALID_MEDICAL_CONDITION_HUSBAND)
                .build();

        assertParseSuccess(parser, NAME_DESC_BOB + ID_DESC_BOB + SPECIES_DESC_BOB
                + MEDICAL_CONDITION_DESC_HUSBAND + MEDICAL_CONDITION_DESC_FRIEND,
                new AddCommand(expectedAnimalMultipleMedicalConditions));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero medicalConditions
        Animal expectedAnimal = new AnimalBuilder(AMY).withMedicalConditions().build();
        assertParseSuccess(parser, NAME_DESC_AMY + ID_DESC_AMY + SPECIES_DESC_AMY,
                new AddCommand(expectedAnimal));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + ID_DESC_BOB + SPECIES_DESC_BOB,
                expectedMessage);

        // missing id prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_ID_BOB + SPECIES_DESC_BOB,
                expectedMessage);

        // missing species prefix
        assertParseFailure(parser, NAME_DESC_BOB + ID_DESC_BOB + VALID_SPECIES_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_ID_BOB + VALID_SPECIES_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + ID_DESC_BOB + SPECIES_DESC_BOB
                + MEDICAL_CONDITION_DESC_HUSBAND + MEDICAL_CONDITION_DESC_FRIEND, Name.MESSAGE_CONSTRAINTS);

        // invalid id
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_ID_DESC + SPECIES_DESC_BOB
                + MEDICAL_CONDITION_DESC_HUSBAND + MEDICAL_CONDITION_DESC_FRIEND, Id.MESSAGE_CONSTRAINTS);

        // invalid species
        assertParseFailure(parser, NAME_DESC_BOB + ID_DESC_BOB + INVALID_SPECIES_DESC
                + MEDICAL_CONDITION_DESC_HUSBAND + MEDICAL_CONDITION_DESC_FRIEND, Species.MESSAGE_CONSTRAINTS);

        // invalid medicalCondition
        assertParseFailure(parser, NAME_DESC_BOB + ID_DESC_BOB + SPECIES_DESC_BOB
                + INVALID_MEDICAL_CONDITION_DESC + VALID_MEDICAL_CONDITION_FRIEND,
                MedicalCondition.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + ID_DESC_BOB + INVALID_SPECIES_DESC,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + ID_DESC_BOB
                + SPECIES_DESC_BOB + MEDICAL_CONDITION_DESC_HUSBAND + MEDICAL_CONDITION_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}

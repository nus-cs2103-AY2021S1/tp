package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ID_DESC_ARCHIE;
import static seedu.address.logic.commands.CommandTestUtil.ID_DESC_BAILEY;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ID_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MEDICAL_CONDITION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_SPECIES_DESC;
import static seedu.address.logic.commands.CommandTestUtil.MEDICAL_CONDITION_DESC_OBESE;
import static seedu.address.logic.commands.CommandTestUtil.MEDICAL_CONDITION_DESC_ARTHRITIS;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_ARCHIE;
import static seedu.address.logic.commands.CommandTestUtil.SPECIES_DESC_ARCHIE;
import static seedu.address.logic.commands.CommandTestUtil.SPECIES_DESC_BAILEY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_ARCHIE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_BAILEY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEDICAL_CONDITION_OBESE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEDICAL_CONDITION_ARTHRITIS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_ARCHIE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SPECIES_ARCHIE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SPECIES_BAILEY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDICAL_CONDITION;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ANIMAL;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_ANIMAL;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_ANIMAL;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditAnimalDescriptor;
import seedu.address.model.animal.Id;
import seedu.address.model.animal.Name;
import seedu.address.model.animal.Species;
import seedu.address.model.medicalcondition.MedicalCondition;
import seedu.address.testutil.EditAnimalDescriptorBuilder;

public class EditCommandParserTest {

    private static final String MEDICAL_CONDITION_EMPTY = " " + PREFIX_MEDICAL_CONDITION;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_ARCHIE, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_ARCHIE, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_ARCHIE, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 k/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_ID_DESC, Id.MESSAGE_CONSTRAINTS); // invalid id
        assertParseFailure(parser, "1" + INVALID_SPECIES_DESC, Species.MESSAGE_CONSTRAINTS); // invalid address
        assertParseFailure(parser, "1" + INVALID_MEDICAL_CONDITION_DESC,
                MedicalCondition.MESSAGE_CONSTRAINTS); // invalid medicalCondition

        // invalid id followed by valid species
        assertParseFailure(parser, "1" + INVALID_ID_DESC + SPECIES_DESC_ARCHIE, Id.MESSAGE_CONSTRAINTS);

        // valid id followed by invalid id. The test case for invalid id followed by valid id
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + ID_DESC_BAILEY + INVALID_ID_DESC, Id.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_MEDICAL_CONDITION} alone will reset the
        // medicalConditions of the {@code Animal} being edited,
        // parsing it together with a valid medicalCondition results in error
        assertParseFailure(parser, "1" + MEDICAL_CONDITION_DESC_OBESE + MEDICAL_CONDITION_DESC_ARTHRITIS
                + MEDICAL_CONDITION_EMPTY, MedicalCondition.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + MEDICAL_CONDITION_DESC_OBESE
                + MEDICAL_CONDITION_EMPTY + MEDICAL_CONDITION_DESC_ARTHRITIS, MedicalCondition.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + MEDICAL_CONDITION_EMPTY
                + MEDICAL_CONDITION_DESC_OBESE + MEDICAL_CONDITION_DESC_ARTHRITIS, MedicalCondition.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_SPECIES_DESC + VALID_ID_ARCHIE,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_ANIMAL;
        String userInput = targetIndex.getOneBased() + ID_DESC_BAILEY + MEDICAL_CONDITION_DESC_ARTHRITIS
                + SPECIES_DESC_ARCHIE + NAME_DESC_ARCHIE + MEDICAL_CONDITION_DESC_OBESE;

        EditAnimalDescriptor descriptor = new EditAnimalDescriptorBuilder().withName(VALID_NAME_ARCHIE)
                .withId(VALID_ID_BAILEY).withSpecies(VALID_SPECIES_ARCHIE)
                .withMedicalConditions(VALID_MEDICAL_CONDITION_ARTHRITIS, VALID_MEDICAL_CONDITION_OBESE).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_ANIMAL;
        String userInput = targetIndex.getOneBased() + ID_DESC_BAILEY + SPECIES_DESC_ARCHIE;

        EditAnimalDescriptor descriptor = new EditAnimalDescriptorBuilder().withId(VALID_ID_BAILEY)
                .withSpecies(VALID_SPECIES_ARCHIE).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_ANIMAL;
        String userInput = targetIndex.getOneBased() + NAME_DESC_ARCHIE;
        EditAnimalDescriptor descriptor = new EditAnimalDescriptorBuilder().withName(VALID_NAME_ARCHIE).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // id
        userInput = targetIndex.getOneBased() + ID_DESC_ARCHIE;
        descriptor = new EditAnimalDescriptorBuilder().withId(VALID_ID_ARCHIE).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // species
        userInput = targetIndex.getOneBased() + SPECIES_DESC_ARCHIE;
        descriptor = new EditAnimalDescriptorBuilder().withSpecies(VALID_SPECIES_ARCHIE).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // medicalConditions
        userInput = targetIndex.getOneBased() + MEDICAL_CONDITION_DESC_OBESE;
        descriptor = new EditAnimalDescriptorBuilder().withMedicalConditions(VALID_MEDICAL_CONDITION_OBESE).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_ANIMAL;
        String userInput = targetIndex.getOneBased() + ID_DESC_ARCHIE + SPECIES_DESC_ARCHIE
                + MEDICAL_CONDITION_DESC_OBESE + ID_DESC_ARCHIE + SPECIES_DESC_ARCHIE + MEDICAL_CONDITION_DESC_OBESE
                + ID_DESC_BAILEY + SPECIES_DESC_BAILEY + MEDICAL_CONDITION_DESC_ARTHRITIS;

        EditAnimalDescriptor descriptor = new EditAnimalDescriptorBuilder().withId(VALID_ID_BAILEY)
                .withSpecies(VALID_SPECIES_BAILEY).withMedicalConditions(VALID_MEDICAL_CONDITION_OBESE,
                        VALID_MEDICAL_CONDITION_ARTHRITIS)
                .build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_ANIMAL;
        String userInput = targetIndex.getOneBased() + INVALID_ID_DESC + ID_DESC_BAILEY;
        EditAnimalDescriptor descriptor = new EditAnimalDescriptorBuilder().withId(VALID_ID_BAILEY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + INVALID_ID_DESC + SPECIES_DESC_BAILEY
                + ID_DESC_BAILEY;
        descriptor = new EditAnimalDescriptorBuilder().withId(VALID_ID_BAILEY)
                .withSpecies(VALID_SPECIES_BAILEY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetMedicalConditions_success() {
        Index targetIndex = INDEX_THIRD_ANIMAL;
        String userInput = targetIndex.getOneBased() + MEDICAL_CONDITION_EMPTY;

        EditAnimalDescriptor descriptor = new EditAnimalDescriptorBuilder().withMedicalConditions().build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}

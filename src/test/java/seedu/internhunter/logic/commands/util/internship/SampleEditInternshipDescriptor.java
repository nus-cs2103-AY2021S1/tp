package seedu.internhunter.logic.commands.util.internship;

import static seedu.internhunter.testutil.internship.SampleInternshipItems.LAZADA_DS;
import static seedu.internhunter.testutil.internship.SampleInternshipItems.SHOPEE_SWE;

import seedu.internhunter.logic.commands.edit.EditInternshipCommand;

/**
 * Contains the sample edit internship descriptors for the {@code EditInternshipCommand} tests.
 */
public class SampleEditInternshipDescriptor {

    public static final EditInternshipCommand.EditInternshipDescriptor EDIT_DESC_LAZADA_DS =
            new EditInternshipDescriptorBuilder(LAZADA_DS).build();

    public static final EditInternshipCommand.EditInternshipDescriptor EDIT_DESC_SHOPEE_SWE =
            new EditInternshipDescriptorBuilder(SHOPEE_SWE).build();
}

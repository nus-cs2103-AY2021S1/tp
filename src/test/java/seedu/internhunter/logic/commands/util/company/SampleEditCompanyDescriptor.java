package seedu.internhunter.logic.commands.util.company;

import static seedu.internhunter.testutil.company.SampleCompanyItems.GOLDMAN;
import static seedu.internhunter.testutil.company.SampleCompanyItems.GOOGLE;

import seedu.internhunter.logic.commands.edit.EditCompanyCommand;

/**
 * Contains the sample edit company descriptors for the {@code EditCompanyCommand} tests.
 */
public class SampleEditCompanyDescriptor {

    public static final EditCompanyCommand.EditCompanyDescriptor EDIT_DESC_GOLDMAN =
        new EditCompanyDescriptorBuilder(GOLDMAN).build();

    public static final EditCompanyCommand.EditCompanyDescriptor EDIT_DESC_GOOGLE =
        new EditCompanyDescriptorBuilder(GOOGLE).build();
}

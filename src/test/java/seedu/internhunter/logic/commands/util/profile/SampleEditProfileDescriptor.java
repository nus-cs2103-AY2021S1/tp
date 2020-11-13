package seedu.internhunter.logic.commands.util.profile;

import static seedu.internhunter.testutil.profile.SampleProfileItems.HACKATHON_ACHIEVEMENT;
import static seedu.internhunter.testutil.profile.SampleProfileItems.HTML_SKILL;

import seedu.internhunter.logic.commands.edit.EditProfileCommand;

/**
 * Contains the sample edit profile descriptors for the {@code EditProfileCommand} tests.
 */
public class SampleEditProfileDescriptor {

    public static final EditProfileCommand.EditProfileItemDescriptor EDIT_DESC_HTML_SKILL =
        new EditProfileItemDescriptorBuilder(HTML_SKILL).build();

    public static final EditProfileCommand.EditProfileItemDescriptor EDIT_DESC_HACKATHON_ACHIEVEMENT =
        new EditProfileItemDescriptorBuilder(HACKATHON_ACHIEVEMENT).build();
}

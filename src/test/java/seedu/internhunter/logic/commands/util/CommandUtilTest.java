package seedu.internhunter.logic.commands.util;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.internhunter.logic.commands.util.CommandUtil.getApplication;
import static seedu.internhunter.logic.commands.util.CommandUtil.getCommandResult;
import static seedu.internhunter.logic.commands.util.CommandUtil.getCompany;
import static seedu.internhunter.logic.commands.util.CommandUtil.getFullListIndex;
import static seedu.internhunter.logic.commands.util.CommandUtil.getProfileItem;
import static seedu.internhunter.testutil.application.SampleApplicationItems.FACEBOOK_ACCEPTED;
import static seedu.internhunter.testutil.company.SampleCompanyItems.GOLDMAN;
import static seedu.internhunter.testutil.internship.SampleInternshipItems.FACEBOOK_SWE;
import static seedu.internhunter.testutil.internship.SampleInternshipItems.GOLDMAN_FE;
import static seedu.internhunter.testutil.internship.SampleInternshipItems.GOOGLE_SWE;
import static seedu.internhunter.testutil.internship.SampleInternshipItems.LAZADA_DS;
import static seedu.internhunter.testutil.profile.SampleProfileItems.MS_HACKATHON_ACHIEVEMENT;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import seedu.internhunter.commons.core.index.Index;
import seedu.internhunter.logic.commands.CommandResult;
import seedu.internhunter.logic.commands.delete.DeleteCompanyCommand;
import seedu.internhunter.logic.commands.exceptions.CommandException;
import seedu.internhunter.model.Model;
import seedu.internhunter.model.ModelManager;
import seedu.internhunter.model.UserPrefs;
import seedu.internhunter.model.application.ApplicationItem;
import seedu.internhunter.model.company.CompanyItem;
import seedu.internhunter.model.item.ItemList;
import seedu.internhunter.model.profile.ProfileItem;
import seedu.internhunter.testutil.application.ApplicationItemBuilder;
import seedu.internhunter.testutil.company.CompanyItemBuilder;
import seedu.internhunter.testutil.profile.ProfileItemBuilder;
import seedu.internhunter.ui.tabs.TabName;

public class CommandUtilTest {

    private static final String FEEDBACK = "feedback";
    private static final String HELLO_WORLD = "hello world";
    private static final String OK = "Ok";
    private static final String ACCEPTED = "accepted";
    private static final String VALID_PHONE_NUMBER = "91918282";

    private Model model;
    private Model modelWithData;
    private ApplicationItemBuilder applicationItemBuilder;
    private ProfileItemBuilder profileItemBuilder;
    private CompanyItemBuilder companyItemBuilder;

    @BeforeEach
    public void setUp() {
        model = new ModelManager();
        applicationItemBuilder = new ApplicationItemBuilder();
        profileItemBuilder = new ProfileItemBuilder();
        companyItemBuilder = new CompanyItemBuilder();
        initializeData();
    }

    private void initializeData() {
        ItemList<ApplicationItem> applicationItems = new ItemList<>();
        applicationItems.addItem(applicationItemBuilder.build());
        ItemList<ProfileItem> profileItemItems = new ItemList<>();
        profileItemItems.addItem(profileItemBuilder.build());
        ItemList<CompanyItem> companyItems = new ItemList<>();
        companyItems.addItem(companyItemBuilder.build());
        modelWithData = new ModelManager(companyItems, applicationItems, profileItemItems, new UserPrefs());
    }

    @Test
    public void getCommandResult4Parameters_dontSwitchTab_success() {
        CommandResult expected = new CommandResult(FEEDBACK);
        CommandResult result = getCommandResult(model, FEEDBACK, TabName.COMPANY, TabName.COMPANY,
            Index.fromOneBased(1));
        assertEquals(expected, result);
    }

    @Test
    public void getCommandResult4Parameters_switchTabCompanyToProfile_success() {
        CommandResult expected = new CommandResult(FEEDBACK, false, false, true, true);
        CommandResult result = getCommandResult(model, FEEDBACK, TabName.COMPANY, TabName.PROFILE,
            Index.fromOneBased(1));
        assertEquals(expected, result);
    }

    @Test
    public void getCommandResult4Parameters_switchTabCompanyToApplication_success() {
        CommandResult expected = new CommandResult(FEEDBACK, false, false, true, true);
        CommandResult result = getCommandResult(model, FEEDBACK, TabName.COMPANY, TabName.APPLICATION,
            Index.fromOneBased(1));
        assertEquals(expected, result);
    }

    @Test
    public void getCommandResult4Parameters_switchDisplay_success() {
        CommandResult expected = new CommandResult(FEEDBACK);
        model.setCompanyViewIndex(Index.fromOneBased(3));
        CommandResult result = getCommandResult(model, FEEDBACK, TabName.COMPANY, TabName.COMPANY,
            Index.fromOneBased(3));
        assertEquals(expected, result);
    }

    @Test
    public void getCommandResult4Parameters_deletionCausingIndexSwitchCompany_success() {
        // simulate the process of deletion
        modelWithData.addCompany(new CompanyItemBuilder(GOLDMAN).build());
        modelWithData.setCompanyViewIndex(Index.fromOneBased(2)); // simulate the index changing.
        modelWithData.deleteCompany(GOLDMAN);
        // execute getCommandResult. Expected the Index to be changed to 1 instead.
        getCommandResult(model, FEEDBACK, TabName.COMPANY, TabName.COMPANY, Index.fromOneBased(2));

        assertEquals(Index.fromOneBased(1), model.getCompanyViewIndex());
    }

    @Test
    public void getCommandResult4Parameters_deletionCausingIndexSwitchApplication_success() {
        // simulate the process of deletion
        modelWithData.addApplication(new ApplicationItemBuilder(FACEBOOK_ACCEPTED).build());
        modelWithData.setApplicationViewIndex(Index.fromOneBased(2)); // simulate the index changing.
        modelWithData.deleteApplication(FACEBOOK_ACCEPTED);
        // execute getCommandResult. Expected the Index to be changed to 1 instead.
        getCommandResult(model, FEEDBACK, TabName.APPLICATION, TabName.APPLICATION, Index.fromOneBased(2));

        assertEquals(Index.fromOneBased(1), model.getApplicationViewIndex());
    }

    @Test
    public void getCommandResult4Parameters_deletionCausingIndexSwitchProfile_success() {
        // simulate the process of deletion
        modelWithData.addProfileItem(new ProfileItemBuilder(MS_HACKATHON_ACHIEVEMENT).build());
        modelWithData.setProfileViewIndex(Index.fromOneBased(2)); // simulate the index changing.
        modelWithData.deleteProfileItem(MS_HACKATHON_ACHIEVEMENT);

        // execute getCommandResult. Expected the Index to be changed to 1 instead.
        getCommandResult(model, FEEDBACK, TabName.PROFILE, TabName.PROFILE, Index.fromOneBased(2));

        assertEquals(Index.fromOneBased(1), model.getProfileViewIndex());
    }

    @Test
    public void getCompany_equals_true() throws CommandException {
        CompanyItem companyItem = companyItemBuilder.build();
        assertTrue(getCompany(modelWithData, Index.fromOneBased(1)).equals(companyItem));
    }

    @Test
    public void getCompany_equals_false() throws CommandException {
        CompanyItem companyItem = companyItemBuilder.withPhone(VALID_PHONE_NUMBER).build();
        assertFalse(getCompany(modelWithData, Index.fromOneBased(1)).equals(companyItem));
    }

    @Test
    public void getCompany_invalidIndex_throwCommandException() {
        assertThrows(CommandException.class, () -> getCompany(modelWithData, Index.fromOneBased(10)));
        assertThrows(CommandException.class, () -> getCompany(modelWithData, Index.fromZeroBased(1)));
    }

    @Test
    public void getCompany_validIndex_noExceptionThrown() {
        assertDoesNotThrow(() -> getCompany(modelWithData, Index.fromZeroBased(0)));
        assertDoesNotThrow(() -> getCompany(modelWithData, Index.fromOneBased(1)));
    }

    @Test
    public void getApplication_equals_true() throws CommandException {
        ApplicationItem applicationItem = applicationItemBuilder.build();
        assertTrue(getApplication(modelWithData, Index.fromOneBased(1)).equals(applicationItem));
    }

    @Test
    public void getApplication_equals_false() throws CommandException {
        ApplicationItem applicationItem = applicationItemBuilder.withStatus(ACCEPTED).build();
        assertFalse(getApplication(modelWithData, Index.fromOneBased(1)).equals(applicationItem));
    }

    @Test
    public void getApplication_invalidIndex_throwCommandException() {
        assertThrows(CommandException.class, () -> getApplication(modelWithData, Index.fromOneBased(10)));
        assertThrows(CommandException.class, () -> getApplication(modelWithData, Index.fromZeroBased(1)));
    }

    @Test
    public void getApplication_validIndex_noExceptionThrown() {
        assertDoesNotThrow(() -> getApplication(modelWithData, Index.fromZeroBased(0)));
        assertDoesNotThrow(() -> getApplication(modelWithData, Index.fromOneBased(1)));
    }

    @Test
    public void getProfile_equals_true() throws CommandException {
        ProfileItem profileItem = profileItemBuilder.build();
        assertTrue(getProfileItem(modelWithData, Index.fromOneBased(1)).equals(profileItem));
    }

    @Test
    public void getProfile_equals_false() throws CommandException {
        ProfileItem profileItem = profileItemBuilder.withDescriptors(HELLO_WORLD).build();
        assertFalse(getProfileItem(modelWithData, Index.fromOneBased(1)).equals(profileItem));
    }

    @Test
    public void getProfile_invalidIndex_throwCommandException() {
        assertThrows(CommandException.class, () -> getProfileItem(modelWithData, Index.fromOneBased(10)));
        assertThrows(CommandException.class, () -> getProfileItem(modelWithData, Index.fromZeroBased(1)));
    }

    @Test
    public void getProfile_validIndex_noExceptionThrown() {
        assertDoesNotThrow(() -> getProfileItem(modelWithData, Index.fromOneBased(1)));
        assertDoesNotThrow(() -> getProfileItem(modelWithData, Index.fromZeroBased(0)));
    }

    @Test
    public void getFullListIndex_equals_success() {
        ProfileItem profileItem = profileItemBuilder.withTitle(HELLO_WORLD).withDescriptors(OK).build();
        modelWithData.addProfileItem(profileItem);
        ApplicationItem applicationItem = applicationItemBuilder.withInternshipItem(LAZADA_DS)
            .withStatus(ACCEPTED).build();
        modelWithData.addApplication(applicationItem);
        CompanyItem companyItem = companyItemBuilder.withCompanyName(HELLO_WORLD).withAddress(OK).build();
        modelWithData.addCompany(companyItem);

        assertEquals(Index.fromOneBased(2), getFullListIndex(companyItem, modelWithData.getCompanyItemList()));
        assertEquals(Index.fromOneBased(2), getFullListIndex(applicationItem, modelWithData.getApplicationItemList()));
        assertEquals(Index.fromOneBased(2), getFullListIndex(profileItem, modelWithData.getProfileItemList()));
    }

    @Nested
    class SetIndexWhenDeletionTest {

        @Test
        public void setIndexWhenDeletion_applicationViewIndexAtLastPosition_success() throws CommandException {
            ApplicationItem applicationItem1 = applicationItemBuilder.withInternshipItem(FACEBOOK_SWE).build();
            ApplicationItem applicationItem2 = applicationItemBuilder.withInternshipItem(LAZADA_DS).build();
            ApplicationItem applicationItem3 = applicationItemBuilder.withInternshipItem(GOLDMAN_FE).build();
            ApplicationItem applicationItemLast = applicationItemBuilder.withInternshipItem(GOOGLE_SWE).build();
            CompanyItem companyItem = companyItemBuilder.withInternships(FACEBOOK_SWE, LAZADA_DS, GOLDMAN_FE).build();
            model.addCompany(companyItem);
            model.addApplication(applicationItem1);
            model.addApplication(applicationItem2);
            model.addApplication(applicationItem3);
            model.addApplication(applicationItemLast);
            model.setApplicationViewIndex(Index.fromOneBased(4)); // added 4 items
            DeleteCompanyCommand deleteCompanyCommand = new DeleteCompanyCommand(Index.fromOneBased(1));
            deleteCompanyCommand.execute(model); // delete the first company
            // expected the index to be 1
            assertEquals(Index.fromOneBased(1), model.getApplicationViewIndex());
        }

        @Test
        public void setIndexWhenDeletion_applicationViewIndexAtFirstPosition_success() throws CommandException {
            ApplicationItem applicationItem1 = applicationItemBuilder.withInternshipItem(FACEBOOK_SWE).build();
            ApplicationItem applicationItem2 = applicationItemBuilder.withInternshipItem(LAZADA_DS).build();
            ApplicationItem applicationItem3 = applicationItemBuilder.withInternshipItem(GOLDMAN_FE).build();
            ApplicationItem applicationItemFirst = applicationItemBuilder.withInternshipItem(GOOGLE_SWE).build();
            CompanyItem companyItem = companyItemBuilder.withInternships(FACEBOOK_SWE, LAZADA_DS, GOLDMAN_FE).build();
            model.addCompany(companyItem);
            model.addApplication(applicationItemFirst);
            model.addApplication(applicationItem1);
            model.addApplication(applicationItem2);
            model.addApplication(applicationItem3);
            model.setApplicationViewIndex(Index.fromOneBased(4)); // added 4 items
            DeleteCompanyCommand deleteCompanyCommand = new DeleteCompanyCommand(Index.fromOneBased(1));
            deleteCompanyCommand.execute(model); // delete the first company
            // expected the index to be 1
            assertEquals(Index.fromOneBased(1), model.getApplicationViewIndex());
        }

        @Test
        public void setIndexWhenDeletion_applicationViewIndexAtMiddlePosition_success() throws CommandException {
            ApplicationItem applicationItem1 = applicationItemBuilder.withInternshipItem(FACEBOOK_SWE).build();
            ApplicationItem applicationItem2 = applicationItemBuilder.withInternshipItem(LAZADA_DS).build();
            ApplicationItem applicationItem3 = applicationItemBuilder.withInternshipItem(GOLDMAN_FE).build();
            ApplicationItem applicationItemThird = applicationItemBuilder.withInternshipItem(GOOGLE_SWE).build();
            CompanyItem companyItem = companyItemBuilder.withInternships(FACEBOOK_SWE, LAZADA_DS, GOLDMAN_FE).build();
            model.addCompany(companyItem);
            model.addApplication(applicationItem1);
            model.addApplication(applicationItem2);
            model.addApplication(applicationItemThird);
            model.addApplication(applicationItem3);
            model.setApplicationViewIndex(Index.fromOneBased(4)); // added 4 items
            DeleteCompanyCommand deleteCompanyCommand = new DeleteCompanyCommand(Index.fromOneBased(1));
            deleteCompanyCommand.execute(model); // delete the first company
            // expected the index to be 1
            assertEquals(Index.fromOneBased(1), model.getApplicationViewIndex());
        }
    }

}

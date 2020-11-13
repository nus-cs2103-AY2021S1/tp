package seedu.internhunter.logic.commands.add;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.internhunter.commons.core.Messages.MESSAGE_ADD_SUCCESS;
import static seedu.internhunter.commons.core.Messages.MESSAGE_DUPLICATE_ITEM;
import static seedu.internhunter.model.util.ItemUtil.COMPANY_NAME;
import static seedu.internhunter.testutil.Assert.assertThrows;
import static seedu.internhunter.testutil.company.SampleCompanyItems.FACEBOOK;
import static seedu.internhunter.testutil.company.SampleCompanyItems.GOOGLE;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.internhunter.commons.core.index.Index;
import seedu.internhunter.logic.commands.CommandResult;
import seedu.internhunter.logic.commands.exceptions.CommandException;
import seedu.internhunter.model.company.CompanyItem;
import seedu.internhunter.testutil.company.CompanyItemBuilder;
import seedu.internhunter.ui.tabs.TabName;

public class AddCompanyCommandTest {

    @Test
    public void constructor_nullCompany_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCompanyCommand(null));
    }

    @Test
    public void execute_companyAcceptedByModel_addSuccessful() throws Exception {
        AddCompanyCommandTest.ModelStubAcceptingCompanyAdded modelStub = new AddCompanyCommandTest
                .ModelStubAcceptingCompanyAdded();
        CompanyItem validCompany = new CompanyItemBuilder().build();

        CommandResult commandResult = new AddCompanyCommand(validCompany).execute(modelStub);

        assertEquals(String.format(MESSAGE_ADD_SUCCESS, COMPANY_NAME, validCompany),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validCompany), modelStub.companies);
    }

    @Test
    public void execute_duplicateCompany_throwsCommandException() {
        CompanyItem validCompany = GOOGLE;
        AddCompanyCommand addCompanyCommand = new AddCompanyCommand(validCompany);
        ModelStub modelStub = new AddCompanyCommandTest.ModelStubAcceptingCompanyAdded();
        modelStub.addCompany(validCompany);

        assertThrows(CommandException.class,
                String.format(MESSAGE_DUPLICATE_ITEM, COMPANY_NAME), () -> addCompanyCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        AddCompanyCommand addCompanyGoogleCommand = new AddCompanyCommand(GOOGLE);
        AddCompanyCommand addCompanyFacebookCommand = new AddCompanyCommand(FACEBOOK);

        // same object -> returns true
        assertTrue(addCompanyGoogleCommand.equals(addCompanyGoogleCommand));

        // same values -> returns true
        AddCompanyCommand addCompanyGoogleCommandCopy = new AddCompanyCommand(GOOGLE);
        assertTrue(addCompanyGoogleCommand.equals(addCompanyGoogleCommandCopy));

        // different types -> returns false
        assertFalse(addCompanyGoogleCommand.equals(1));

        // null -> returns false
        assertFalse(addCompanyGoogleCommand.equals(null));

        // different company -> returns false
        assertFalse(addCompanyGoogleCommand.equals(addCompanyFacebookCommand));
    }


    /**
     * A Model stub that always accepts the company being added.
     */
    private class ModelStubAcceptingCompanyAdded extends ModelStub {
        final ArrayList<CompanyItem> companies = new ArrayList<>();

        @Override
        public boolean hasCompany(CompanyItem companyItem) {
            requireNonNull(companyItem);
            return companies.stream().anyMatch(companyItem::isSameItem);
        }

        @Override
        public void addCompany(CompanyItem companyItem) {
            requireNonNull(companyItem);
            companies.add(companyItem);
        }

        @Override
        public int getFilteredCompanyListSize() {
            return companies.size();
        }

        @Override
        public void setCompanyViewIndex(Index index) {
            // do nothing
        }

        @Override
        public TabName getTabName() {
            return TabName.valueOf("COMPANY");
        }
    }
}

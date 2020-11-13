package seedu.internhunter.logic.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import seedu.internhunter.model.Model;
import seedu.internhunter.model.company.CompanyItem;
import seedu.internhunter.model.internship.InternshipItem;
import seedu.internhunter.model.profile.ProfileItem;
import seedu.internhunter.model.profile.ProfileItemCategory;

/**
 * Generates a list of matching internships based on the user skills stored in the profile.
 */
public class MatchCommand extends Command {

    public static final String COMMAND_WORD = "match";
    public static final String SHOWING_MATCH_COMMAND_MESSAGE = "Displayed list of matching internships.";
    public static final String NO_MATCHING_INTERNSHIPS_MESSAGE = "There are no internships that matches your "
            + "current skill set.";

    /**
     * Executes the MatchCommand and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return Feedback message of the operation result for display.
     */
    @Override
    public CommandResult execute(Model model) {
        List<ProfileItem> profileItemList = model.getProfileItemList();
        List<CompanyItem> companyItemList = model.getCompanyItemList();
        List<String> skillList = getSkillList(profileItemList);
        List<InternshipItem> fullInternshipList = getInternshipList(companyItemList);
        List<InternshipItem> matchingInternships = getMatchingInternships(fullInternshipList, skillList);
        return getMatchingInternshipsCommandResult(matchingInternships);
    }

    /**
     * Retrieves the skill list that the user holds.
     *
     * @param profileItemList ProfileItemList of the user.
     * @return Skill list of the user.
     */
    private List<String> getSkillList(List<ProfileItem> profileItemList) {
        return profileItemList.stream()
                .filter(profileItem -> profileItem.getCategory() == ProfileItemCategory.SKILL)
                .map(ProfileItem::getTitleValue)
                .collect(Collectors.toList());
    }

    /**
     * Obtains the internship list of the user.
     *
     * @param companyItemList CompanyItemList of the user.
     * @return The full list of internships of the user.
     */
    private List<InternshipItem> getInternshipList(List<CompanyItem> companyItemList) {
        List<InternshipItem> internshipItems = new ArrayList<>();
        companyItemList.stream().map(CompanyItem::getInternships).forEach(internshipItems::addAll);
        return internshipItems;
    }

    /**
     * Filters the internship items and obtains only those internships that matches the skills of the user profile,
     * based on the {@link InternshipItem#matches(List)} method.
     *
     * @param fullInternshipList Full internship list of the user.
     * @param skillList Skill list of the user.
     * @return List of matching internships.
     */
    private List<InternshipItem> getMatchingInternships(List<InternshipItem> fullInternshipList,
            List<String> skillList) {
        return fullInternshipList
                .stream()
                .filter(internshipItem -> internshipItem.matches(skillList))
                .collect(Collectors.toList());
    }

    /**
     * Obtains the CommandResult with the correct message ot display to the user.
     *
     * @param matchingInternships Matching internship list.
     * @return CommandResult.
     */
    private CommandResult getMatchingInternshipsCommandResult(List<InternshipItem> matchingInternships) {
        if (matchingInternships.isEmpty()) {
            return new CommandResult(NO_MATCHING_INTERNSHIPS_MESSAGE);
        }
        CommandResult commandResult = new CommandResult(SHOWING_MATCH_COMMAND_MESSAGE);
        commandResult.setMatchingInternships(FXCollections.observableArrayList(matchingInternships));
        return commandResult;
    }

}

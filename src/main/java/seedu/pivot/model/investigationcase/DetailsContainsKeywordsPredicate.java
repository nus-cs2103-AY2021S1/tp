package seedu.pivot.model.investigationcase;

import java.util.List;
import java.util.function.Predicate;

import seedu.pivot.commons.util.StringUtil;
import seedu.pivot.model.investigationcase.caseperson.Address;
import seedu.pivot.model.investigationcase.caseperson.Email;
import seedu.pivot.model.investigationcase.caseperson.Gender;
import seedu.pivot.model.investigationcase.caseperson.Name;
import seedu.pivot.model.investigationcase.caseperson.Phone;
import seedu.pivot.model.investigationcase.caseperson.Suspect;
import seedu.pivot.model.investigationcase.caseperson.Victim;
import seedu.pivot.model.investigationcase.caseperson.Witness;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class DetailsContainsKeywordsPredicate implements Predicate<Case> {
    private final List<String> keywords;

    public DetailsContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Case investigationCase) {
        return keywords.stream()
                .anyMatch(keyword ->
                        StringUtil.containsWordIgnoreCase(investigationCase.getTitle().getAlphaNum(), keyword)
                                || StringUtil.containsWordIgnoreCase(investigationCase.getDescription().toString(),
                                keyword)
                                || StringUtil.containsWordIgnoreCase(investigationCase.getStatus().toString(), keyword)
                                || StringUtil.containsWordIgnoreCase(getDocumentsInfo(investigationCase), keyword)
                                || StringUtil.containsWordIgnoreCase(getSuspectsInfo(investigationCase), keyword)
                                || StringUtil.containsWordIgnoreCase(getVictimsInfo(investigationCase), keyword)
                                || StringUtil.containsWordIgnoreCase(getWitnessesInfo(investigationCase), keyword)
                );
    }

    /**
     * Obtains information of all documents in a case, with each word separated by an empty space.
     * @param investigationCase The case to obtain information from.
     * @return String containing all documents information (name and reference).
     */
    private String getDocumentsInfo(Case investigationCase) {
        StringBuilder documentsInfo = new StringBuilder();
        for (Document doc : investigationCase.getDocuments()) {
            documentsInfo.append(doc.getName()).append(" ");
            documentsInfo.append(doc.getReference().getFileName()).append(" ");
        }
        return documentsInfo.toString();
    }

    /**
     * Obtains information of all suspects in a case, with each word separated by an empty space.
     * @param investigationCase The case to obtain information from.
     * @return String containing all witnesses information.
     */
    private String getSuspectsInfo(Case investigationCase) {
        StringBuilder suspectsInfo = new StringBuilder();
        for (Suspect suspect : investigationCase.getSuspects()) {
            appendPersonDetails(suspectsInfo, suspect.getName(), suspect.getGender(),
                    suspect.getPhone(), suspect.getEmail(), suspect.getAddress());
        }
        return suspectsInfo.toString();
    }

    /**
     * Obtains information of all victims in a case, with each word separated by an empty space.
     * @param investigationCase The case to obtain information from.
     * @return String containing all witnesses information.
     */
    private String getWitnessesInfo(Case investigationCase) {
        StringBuilder witnessesInfo = new StringBuilder();
        for (Witness witness : investigationCase.getWitnesses()) {
            appendPersonDetails(witnessesInfo, witness.getName(), witness.getGender(),
                    witness.getPhone(), witness.getEmail(), witness.getAddress());
        }
        return witnessesInfo.toString();
    }

    /**
     * Obtains information of all victims in a case, with each word separated by an empty space.
     * @param investigationCase The case to obtain information from.
     * @return String containing all victims information.
     */
    private String getVictimsInfo(Case investigationCase) {
        StringBuilder victimsInfo = new StringBuilder();
        for (Victim victim : investigationCase.getVictims()) {
            appendPersonDetails(victimsInfo, victim.getName(), victim.getGender(),
                    victim.getPhone(), victim.getEmail(), victim.getAddress());
        }
        return victimsInfo.toString();
    }

    /**
     * Appends all fields of a person into the StringBuilder defined as first parameter,
     * with each field separated by an empty space.
     * @param builder The StringBuilder to append to.
     * @param name The Name of the CasePerson.
     * @param gender The Gender of the CasePerson.
     * @param phone The Phone of the CasePerson.
     * @param email The Email of the CasePerson.
     * @param address The Address of the CasePerson.
     */
    private void appendPersonDetails(StringBuilder builder, Name name, Gender gender,
                                     Phone phone, Email email, Address address) {
        builder.append(name).append(" ");
        builder.append(gender).append(" ");
        builder.append(phone).append(" ");
        builder.append(email).append(" ");
        builder.append(address).append(" ");
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DetailsContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((DetailsContainsKeywordsPredicate) other).keywords)); // state check
    }

}

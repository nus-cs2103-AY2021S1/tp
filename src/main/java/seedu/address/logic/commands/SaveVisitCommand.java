package seedu.address.logic.commands;

import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PATIENTS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.patient.Patient;
import seedu.address.model.visit.Visit;

public class SaveVisitCommand extends Command {
    public static final String MESSAGE_SAVE_VISIT_SUCCESS = "Saved visit report to Person: %1$s";
    private static final int NEW_REPORT = -1;

    private final Index index;
    private final Visit visitReport;

    private int reportIdx;
    private String medication;
    private String diagnosis;
    private String remarks;

    /**
     * Saves new record to Visit List.
     */
    public SaveVisitCommand(int index, int reportIdx, String date, String meds, String dg, String rmk) {
        CollectionUtil.requireAllNonNull(index, reportIdx, date);
        this.index = Index.fromOneBased(index);
        this.reportIdx = reportIdx;
        this.visitReport = new Visit(date);
        this.medication = meds;
        this.diagnosis = dg;
        this.remarks = rmk;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Patient> lastShownList = model.getFilteredPatientList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
        }

        Patient personToEdit = lastShownList.get(index.getZeroBased());
        visitReport.setPatientName(personToEdit.getName());
        visitReport.setFields(medication, diagnosis, remarks);
        Patient editedPerson = null;
        if (reportIdx == NEW_REPORT) {
            editedPerson = new Patient(personToEdit.getName(), personToEdit.getPhone(), personToEdit.getIcNumber(),
                    personToEdit.getVisitHistory().addVisit(visitReport), personToEdit.getAddress(),
                    personToEdit.getEmail(), personToEdit.getProfilePicture(), personToEdit.getSex(),
                    personToEdit.getBloodType(), personToEdit.getAllergies(), personToEdit.getColorTag());
        } else {
            editedPerson = new Patient(personToEdit.getName(), personToEdit.getPhone(), personToEdit.getIcNumber(),
                    personToEdit.getVisitHistory().editVisit(reportIdx, visitReport), personToEdit.getAddress(),
                    personToEdit.getEmail(), personToEdit.getProfilePicture(), personToEdit.getSex(),
                    personToEdit.getBloodType(), personToEdit.getAllergies(), personToEdit.getColorTag());

        }

        assert(!editedPerson.equals(null));
        model.setPatient(personToEdit, editedPerson);
        model.updateFilteredPatientList(PREDICATE_SHOW_ALL_PATIENTS);

        return new CommandResult(String.format(MESSAGE_SAVE_VISIT_SUCCESS, personToEdit));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SaveVisitCommand)) {
            return false;
        }

        // state check
        SaveVisitCommand e = (SaveVisitCommand) other;
        return index.equals(e.index)
                && medication.equals(e.medication)
                && diagnosis.equals(e.diagnosis)
                && remarks.equals(e.remarks)
                && visitReport.getVisitDate().equals(e.visitReport.getVisitDate());

    }

}


package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.LogicManager.FILE_OPS_ERROR_MESSAGE;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.attendance.Attendance;
import seedu.address.model.person.Person;



/**
 * Archives all current data of the students in a separate file at /data.
 */
public class ExportAttendanceCommand extends Command {
    public static final String COMMAND_WORD = "exportatt";
    public static final String MESSAGE_SUCCESS = "Attendance exported to %s";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        // get current date & time
        LocalDateTime time = LocalDateTime.now();
        LocalDate currentDate = time.toLocalDate();
        String currentTime = time.format(DateTimeFormatter.ofPattern("HHmm"));
        // csv file path
        String filePath = String.format("data/attendance_%s_%s.csv", currentDate, currentTime);
        ObservableList<Person> personList = model.getAddressBook().getPersonList();

        try {
            writeAttendancesToCsv(personList, filePath);
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, filePath));
    }

    private void writeAttendancesToCsv(ObservableList<Person> personList, String filePath) throws IOException {
        SortedSet<Attendance> allAttendances = new TreeSet<>();
        int numColumns = personList.size() + 1;
        String[] csvHeaders = new String[numColumns];
        csvHeaders[0] = "Date";
        // Create HashSet for each Person's attendance for faster runtime compared to TreeSet
        List<HashSet<Attendance>> attendanceHashSets = new ArrayList<>();
        for (int i = 0; i < personList.size(); i++) {
            Person person = personList.get(i);
            SortedSet<Attendance> attendanceTreeSet = person.getAttendances();
            allAttendances.addAll(attendanceTreeSet);
            HashSet<Attendance> attendanceHashSet = new HashSet<>(attendanceTreeSet);
            attendanceHashSets.add(attendanceHashSet);
            csvHeaders[i + 1] = person.getName().toString();
        }

        File file = new File(filePath);
        // make sure parent dir exists (else created)
        file.getParentFile().mkdirs();
        FileWriter out = new FileWriter(file);
        CSVPrinter csvPrinter = new CSVPrinter(out, CSVFormat.DEFAULT.withHeader(csvHeaders));
        for (Attendance attendance : allAttendances) {
            String[] row = new String[numColumns];
            row[0] = attendance.toString(); // Date
            for (int i = 0; i < personList.size(); i++) {
                HashSet<Attendance> attendanceHashSet = attendanceHashSets.get(i);
                if (attendanceHashSet.contains(attendance)) {
                    row[i + 1] = "ATTENDED";
                } else {
                    row[i + 1] = "ABSENT";
                }
            }
            // cast to suppress unclear if varargs or non-varargs call warning
            csvPrinter.printRecord((Object[]) row);
        }
        csvPrinter.flush();
    }
}



package tutorspet.logic.commands;

import static java.util.Objects.requireNonNull;
import static tutorspet.commons.core.Messages.MESSAGE_INVALID_MODULE_CLASS_DISPLAYED_INDEX;
import static tutorspet.commons.core.Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX;
import static tutorspet.commons.core.Messages.MESSAGE_MISSING_LINK;
import static tutorspet.commons.util.CollectionUtil.requireAllNonNull;
import static tutorspet.logic.parser.CliSyntax.PREFIX_CLASS_INDEX;
import static tutorspet.logic.parser.CliSyntax.PREFIX_STUDENT_INDEX;
import static tutorspet.logic.util.ModuleClassUtil.deleteStudentFromModuleClass;

import java.util.List;
import java.util.UUID;

import tutorspet.commons.core.index.Index;
import tutorspet.logic.commands.exceptions.CommandException;
import tutorspet.model.Model;
import tutorspet.model.moduleclass.ModuleClass;
import tutorspet.model.moduleclass.SameModuleClassPredicate;
import tutorspet.model.student.Student;
import tutorspet.model.student.StudentInUuidCollectionPredicate;

/**
 * Unlinks an existing student from an existing class.
 */
public class UnlinkCommand extends Command {

    public static final String COMMAND_WORD = "unlink";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Removes the student identified by the index number used in the displayed student list from "
            + "the class identified by the index number used in the displayed class list.\n"
            + "Parameters: "
            + PREFIX_STUDENT_INDEX + "STUDENT_INDEX (must be a positive integer) "
            + PREFIX_CLASS_INDEX + "CLASS_INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_STUDENT_INDEX + "1 "
            + PREFIX_CLASS_INDEX + "1";

    public static final String MESSAGE_SUCCESS = "Removed %1$s from %2$s.";

    private final Index moduleClassIndex;
    private final Index studentIndex;

    /**
     * @param moduleClassIndex in the filtered class list to unlink.
     * @param studentIndex in the filtered student list to unlink.
     */
    public UnlinkCommand(Index moduleClassIndex, Index studentIndex) {
        requireAllNonNull(studentIndex, moduleClassIndex);

        this.moduleClassIndex = moduleClassIndex;
        this.studentIndex = studentIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Student> lastShownStudentList = model.getFilteredStudentList();
        List<ModuleClass> lastShownModuleClassList = model.getFilteredModuleClassList();

        if (studentIndex.getOneBased() > lastShownStudentList.size()) {
            throw new CommandException(MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        if (moduleClassIndex.getOneBased() > lastShownModuleClassList.size()) {
            throw new CommandException(MESSAGE_INVALID_MODULE_CLASS_DISPLAYED_INDEX);
        }

        Student studentToUnlink = lastShownStudentList.get(studentIndex.getZeroBased());
        ModuleClass moduleClassToUnlink = lastShownModuleClassList.get(moduleClassIndex.getZeroBased());
        ModuleClass modifiedModuleClass = createModifiedModuleClass(moduleClassToUnlink, studentToUnlink);
        model.setModuleClass(moduleClassToUnlink, modifiedModuleClass);

        model.updateFilteredModuleClassList(new SameModuleClassPredicate(modifiedModuleClass));
        model.updateFilteredStudentList(new StudentInUuidCollectionPredicate(modifiedModuleClass.getStudentUuids()));

        String message = String.format(MESSAGE_SUCCESS, studentToUnlink.getName(), moduleClassToUnlink);
        model.commit(message);
        return new CommandResult(message);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof UnlinkCommand
                && studentIndex.equals(((UnlinkCommand) other).studentIndex)
                && moduleClassIndex.equals(((UnlinkCommand) other).moduleClassIndex));
    }

    private static ModuleClass createModifiedModuleClass(ModuleClass moduleClassToUnlink, Student studentToUnlink)
            throws CommandException {
        assert moduleClassToUnlink != null;
        assert studentToUnlink != null;

        UUID studentUuid = studentToUnlink.getUuid();

        if (!(moduleClassToUnlink.hasStudentUuid(studentUuid))) {
            throw new CommandException(MESSAGE_MISSING_LINK);
        }

        return deleteStudentFromModuleClass(moduleClassToUnlink, studentToUnlink);
    }
}

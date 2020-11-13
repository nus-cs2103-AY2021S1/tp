package seedu.address.logic.commands.budget;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INDEX_OUT_OF_BOUNDS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.expenditure.Date;
import seedu.address.model.expenditure.Expenditure;
import seedu.address.model.expenditure.Name;
import seedu.address.model.expenditure.Price;
import seedu.address.model.tag.Tag;
import seedu.address.state.expenditureindex.ExpenditureIndex;

public class EditExpenditureCommand extends Command {
    public static final String COMMAND_WORD = "edit";
    public static final String SYNTAX = COMMAND_WORD + " INDEX "
            + "(" + PREFIX_NAME + "NAME) "
            + "(" + PREFIX_PRICE + "PRICE) ";
    public static final String DESCRIPTION = "Edits an expenditure.";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n"
            + "Usage: " + SYNTAX + "\n"
            + "Description: " + DESCRIPTION + "\n"
            + "Parameters: INDEX\n"
            + "                            " + PREFIX_NAME + "NAME (Optional)\n"
            + "                            " + PREFIX_PRICE + "PRICE (Optional)\n"
            + "                            " + PREFIX_TAG + "TAG (Optional)\n"
            + "Example: " + COMMAND_WORD + " 1 " + PREFIX_NAME + "White Collared Shirt " + PREFIX_PRICE + "25"
            + PREFIX_TAG + "clothes\n"
            + "                     " + COMMAND_WORD + " 2 " + PREFIX_PRICE + "15 ";

    public static final String MESSAGE_SUCCESS = "Edited expenditure information.";

    private final ExpenditureIndex expenditureIndex;
    private final EditExpenditureDescriptor editExpenditureDescriptor;

    /**
     * Create an EditExpenditureCommand to edit the specified expenditure at {code: index}
     * @param index
     * @param editExpenditureDescriptor
     */
    public EditExpenditureCommand(ExpenditureIndex index, EditExpenditureDescriptor editExpenditureDescriptor) {
        requireNonNull(index);
        requireNonNull(editExpenditureDescriptor);

        this.expenditureIndex = index;
        this.editExpenditureDescriptor = editExpenditureDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.isIndexOutOfBound(expenditureIndex)) {
            throw new CommandException(MESSAGE_INDEX_OUT_OF_BOUNDS);
        }

        Expenditure toEdit = model.getExpenditureAtIndex(expenditureIndex);
        Expenditure editedExpenditure = createEditedExpenditure(toEdit, editExpenditureDescriptor);

        model.saveToHistory();
        model.editExpenditure(toEdit, editedExpenditure);
        return new CommandResult(String.format(MESSAGE_SUCCESS));
    }

    private static Expenditure createEditedExpenditure(Expenditure expenditureToEdit,
                                                       EditExpenditureDescriptor editExpenditureDescriptor) {
        Name name = editExpenditureDescriptor.getName().orElse(expenditureToEdit.getName());
        Price price = editExpenditureDescriptor.getPrice().orElse(expenditureToEdit.getPrice());
        Date date = editExpenditureDescriptor.getDate().orElse(expenditureToEdit.getCreatedOn());
        Set<Tag> tags = editExpenditureDescriptor.getTags().orElse(expenditureToEdit.getTags());

        return new Expenditure(name, price, date, tags);
    }

    public static class EditExpenditureDescriptor {
        private Name name;
        private Price price;
        private Date date;
        private Set<Tag> tags;

        public EditExpenditureDescriptor() {}

        /**
         * Constructor with given EditExpenditureDescriptor to copy from.
         * @param toCopy
         */
        public EditExpenditureDescriptor(EditExpenditureDescriptor toCopy) {
            setName(toCopy.name);
            setPrice(toCopy.price);
            setDate(toCopy.date);
            setTags(toCopy.tags);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setPrice(Price price) {
            this.price = price;
        }

        public Optional<Price> getPrice() {
            return Optional.ofNullable(price);
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public Optional<Date> getDate() {
            return Optional.ofNullable(date);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (!tags.isEmpty()) ? new HashSet<>(tags) : new HashSet<>();
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            EditExpenditureDescriptor that = (EditExpenditureDescriptor) o;
            return Objects.equals(name, that.name)
                    && Objects.equals(price, that.price);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EditExpenditureCommand
                && expenditureIndex.equals(((EditExpenditureCommand) other).expenditureIndex)
                && editExpenditureDescriptor.equals(((EditExpenditureCommand) other).editExpenditureDescriptor));
    }
}

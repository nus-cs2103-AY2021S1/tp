package seedu.resireg.model;

/**
 * See any one of the following:
 * @see Model#updateFilteredRoomList(ModelPredicate)
 * @see Model#updateFilteredStudentList(ModelPredicate)
 * @see Model#updateFilteredAllocationList(ModelPredicate)
 */
@FunctionalInterface
public interface ModelPredicate<T> {
    boolean test(T t, Model model);
}

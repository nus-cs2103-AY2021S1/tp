package seedu.address.testutil;

import seedu.address.commons.core.index.Index;
import seedu.address.model.person.client.ClientId;
import seedu.address.model.person.hairdresser.HairdresserId;

/**
 * A utility class containing a list of {@code Index} objects to be used in tests.
 */
public class TypicalIds {
    public static final Index INDEX_FIRST_PERSON = Index.fromOneBased(1);
    public static final Index INDEX_SECOND_PERSON = Index.fromOneBased(2);
    public static final Index INDEX_THIRD_PERSON = Index.fromOneBased(3);

    public static final HairdresserId ID_FIRST_HAIRDRESSER = new HairdresserId("1");
    public static final HairdresserId ID_SECOND_HAIRDRESSER = new HairdresserId("2");
    public static final HairdresserId ID_THIRD_HAIRDRESSER = new HairdresserId("3");

    public static final ClientId ID_FIRST_CLIENT = new ClientId("1");
    public static final ClientId ID_SECOND_CLIENT = new ClientId("2");
    public static final ClientId ID_THIRD_CLIENT = new ClientId("3");
}

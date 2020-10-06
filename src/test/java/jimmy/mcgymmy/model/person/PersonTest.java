package jimmy.mcgymmy.model.person;

import static jimmy.mcgymmy.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import jimmy.mcgymmy.testutil.PersonBuilder;

public class PersonTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Person person = new PersonBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> person.getTags().remove(0));
    }
    /*
    TODO: test item equality
     */
}

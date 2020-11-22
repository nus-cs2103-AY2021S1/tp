package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.testutil.TestUtil.basicEqualsTests;
import static seedu.address.testutil.TypicalClients.AMY;
import static seedu.address.testutil.TypicalClients.BOB;
import static seedu.address.ui.testutil.GuiTestAssert.assertCardDisplaysClient;

import org.junit.jupiter.api.Test;

import guitests.guihandles.ClientCardHandle;
import seedu.address.model.client.Client;
import seedu.address.testutil.ClientBuilder;

public class ClientListCardTest extends GuiUnitTest {

    @Test
    public void display() {
        Client client1 = new ClientBuilder().withName("client1").build();
        ClientListCard clientCard = new ClientListCard(client1, 1);
        uiPartExtension.setUiPart(clientCard);
        assertCardDisplay(clientCard, client1, 1);

        Client client2 = new ClientBuilder().withName("client2").build();
        clientCard = new ClientListCard(client2, 2);
        uiPartExtension.setUiPart(clientCard);
        assertCardDisplay(clientCard, client2, 2);
    }

    @Test
    public void constructor_badClient_throwsException() {
        // null client
        assertThrows(NullPointerException.class, () -> new ClientListCard(null, 0),
                "Card is being created with a null client");
    }

    @Test
    public void equals() {
        ClientListCard amyClientListCard = new ClientListCard(AMY, 0);

        // basic equals tests
        basicEqualsTests(amyClientListCard);

        // same person, same index -> returns true
        ClientListCard copy = new ClientListCard(AMY, 0);
        assertEquals(copy, amyClientListCard);

        // different person, same index -> returns false
        assertNotEquals(new ClientListCard(BOB, 0), amyClientListCard);

        // same person, different index -> returns false
        assertNotEquals(new ClientListCard(AMY, 1), amyClientListCard);
    }

    /**
     * Asserts that {@code clientListCard} displays the details of {@code expectedClient} correctly and matches
     * {@code expectedId}.
     */
    private void assertCardDisplay(ClientListCard clientListCard, Client expectedClient, int expectedId) {
        guiRobot.pauseForHuman();

        ClientCardHandle clientCardHandle = new ClientCardHandle(clientListCard.getRoot());

        // verify id is displayed correctly
        assertEquals(expectedId + ". ", clientCardHandle.getId());

        // verify person details are displayed correctly
        assertCardDisplaysClient(expectedClient, clientCardHandle);
    }
}

package seedu.address.ui.testutil;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import guitests.guihandles.ClientCardHandle;
import guitests.guihandles.ClientListPanelHandle;
import guitests.guihandles.ResultDisplayHandle;
import seedu.address.model.client.Client;

/**
 * A set of assertion methods useful for writing GUI tests.
 */
public class GuiTestAssert {
    /**
     * Asserts that {@code actualCard} displays the same values as {@code expectedCard}.
     */
    public static void assertCardEquals(ClientCardHandle expectedCard, ClientCardHandle actualCard) {
        assertEquals(expectedCard.getId(), actualCard.getId());
        assertEquals(expectedCard.getAddress(), actualCard.getAddress());
        assertEquals(expectedCard.getEmail(), actualCard.getEmail());
        assertEquals(expectedCard.getName(), actualCard.getName());
        assertEquals(expectedCard.getPhone(), actualCard.getPhone());
    }

    /**
     * Asserts that {@code actualCard} displays the details of {@code expectedClient}.
     */
    public static void assertCardDisplaysClient(Client expectedClient, ClientCardHandle actualCard) {
        assertEquals(expectedClient.getName().fullName, actualCard.getName());
        assertEquals(expectedClient.getPhone().value, actualCard.getPhone());
        assertEquals(expectedClient.getEmail().value, actualCard.getEmail());
        assertEquals(expectedClient.getAddress().value, actualCard.getAddress());
    }

    /**
     * Asserts that the list in {@code clientListPanelHandle} displays the details of {@code clients} correctly and
     * in the correct order.
     */
    public static void assertListMatching(ClientListPanelHandle clientListPanelHandle, Client... clients) {
        for (int i = 0; i < clients.length; i++) {
            clientListPanelHandle.navigateToCard(i);
            assertCardDisplaysClient(clients[i], clientListPanelHandle.getClientCardHandle(i));
        }
    }

    /**
     * Asserts that the list in {@code clientListPanelHandle} displays the details of {@code clients} correctly and
     * in the correct order.
     */
    public static void assertListMatching(ClientListPanelHandle clientListPanelHandle, List<Client> clients) {
        assertListMatching(clientListPanelHandle, clients.toArray(new Client[0]));
    }

    /**
     * Asserts the size of the list in {@code clientListPanelHandle} equals to {@code size}.
     */
    public static void assertListSize(ClientListPanelHandle clientListPanelHandle, int size) {
        int numberOfPeople = clientListPanelHandle.getListSize();
        assertEquals(size, numberOfPeople);
    }

    /**
     * Asserts the message shown in {@code resultDisplayHandle} equals to {@code expected}.
     */
    public static void assertResultMessage(ResultDisplayHandle resultDisplayHandle, String expected) {
        assertEquals(expected, resultDisplayHandle.getText());
    }
}

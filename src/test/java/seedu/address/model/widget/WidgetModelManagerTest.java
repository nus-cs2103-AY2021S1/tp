package seedu.address.model.widget;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.TestUtil.basicEqualsTests;
import static seedu.address.testutil.TypicalClients.AMY;
import static seedu.address.testutil.TypicalClients.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.model.client.Client;
import seedu.address.testutil.ClientBuilder;

public class WidgetModelManagerTest {

    @Test
    public void equals_basicTests() {
        // basic equals tests
        basicEqualsTests(WidgetModel.initWidget());
    }

    @Test
    public void setContent_sameClient_isEqual() {
        WidgetModel widgetModelManager1 = WidgetModel.initWidget();
        WidgetModel widgetModelManager2 = WidgetModel.initWidget();

        // set content with same object -> widget object should be the same
        Client bob = new ClientBuilder(BOB).build();
        widgetModelManager1.setWidgetClient(bob);
        widgetModelManager2.setWidgetClient(bob);
        assertEquals(widgetModelManager1.getWidgetClient(), widgetModelManager2.getWidgetClient());


        // set content to different client objects but of the same fields and values -> meaning same person but
        // duplicate entry
        Client bob1 = new ClientBuilder(BOB).build();
        widgetModelManager2.setWidgetClient(bob1);
        assertEquals(widgetModelManager1.getWidgetClient(), widgetModelManager2.getWidgetClient());

    }

    @Test
    public void setContent_diffClient_notEqual() {
        // diff client should not be the same.
        WidgetModel widgetModelManager1 = WidgetModel.initWidget();
        WidgetModel widgetModelManager2 = WidgetModel.initWidget();
        Client bob = new ClientBuilder(BOB).build();
        Client amy = new ClientBuilder(AMY).build();

        widgetModelManager1.setWidgetClient(bob);
        widgetModelManager2.setWidgetClient(amy);

        assertNotEquals(widgetModelManager1.getWidgetClient(), widgetModelManager2.getWidgetClient());
    }

}

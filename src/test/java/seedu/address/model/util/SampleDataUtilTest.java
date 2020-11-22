package seedu.address.model.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.model.util.SampleDataUtil.getSampleClients;
import static seedu.address.model.util.SampleDataUtil.getSampleTbmManager;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.ReadOnlyTbmManager;
import seedu.address.model.client.Client;

public class SampleDataUtilTest {

    @Test
    public void getSampleTbmManager_getSampleClients_containSameClients() {
        ReadOnlyTbmManager tbmManager = getSampleTbmManager();
        Client[] sampleClientArray = getSampleClients();
        ObservableList<Client> sampleClients = FXCollections.observableList(Arrays.asList(sampleClientArray));
        assertEquals(sampleClients, tbmManager.getClientList());
    }

}

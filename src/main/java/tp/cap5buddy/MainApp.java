package tp.cap5buddy;

import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import tp.cap5buddy.commons.LogsCenter;
import tp.cap5buddy.contacts.ContactList;
import tp.cap5buddy.logic.LogicManager;
import tp.cap5buddy.modules.ModuleList;
import tp.cap5buddy.storage.StorageManager;
import tp.cap5buddy.ui.gui.Ui;
import tp.cap5buddy.ui.gui.UiManager;


public class MainApp extends Application {

    protected static Ui userInterface;
    protected static LogicManager manager;
    protected static StorageManager storage;
    protected static ModuleList moduleList;
    protected static ContactList contactList;
    private static final Logger logger = LogsCenter.getLogger(tp.cap5buddy.MainApp.class);

    @Override
    public void init() {
        logger.info("=============================[ Initializing Module Tracker ]===========================");
        userInterface = new UiManager();
        storage = null;
        moduleList = null;
        contactList = null;
        manager = new LogicManager(storage, moduleList, contactList);
    }

    @Override
    public void start(Stage primaryStage) {
        logger.info("Starting Module tracker now!");
        userInterface.start(primaryStage);
    }
}

package tp.cap5buddy.ui.gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import seedu.address.MainApp;

import java.io.IOException;
import java.net.URL;

import static java.util.Objects.requireNonNull;

public abstract class UiPart {
    public static final String FXML_FILE_FOLDER = "/view/tp/";
    private final FXMLLoader fxmlLoader = new FXMLLoader();

    /**
     * Constructs a UiPart with the specified FXML file within {@link #FXML_FILE_FOLDER} and root object.
     * @see #UiPart(URL, AnchorPane)
     */
    public UiPart(String fxmlFileName, AnchorPane root) {
        this(getFxmlFileUrl(fxmlFileName), root);
    }
    /**
     * Constructs a UiPart with the specified FXML file URL and root object.
     * The FXML file must not specify the {@code fx:controller} attribute.
     */
    public UiPart(URL fxmlFileUrl, AnchorPane root) {
        loadFxmlFile(fxmlFileUrl, root);
    }

    /**
     * Returns the FXML file URL for the specified FXML file name within {@link #FXML_FILE_FOLDER}.
     */
    private static URL getFxmlFileUrl(String fxmlFileName) {
        requireNonNull(fxmlFileName);
        String fxmlFileNameWithFolder = FXML_FILE_FOLDER + fxmlFileName;
        URL fxmlFileUrl = MainApp.class.getResource(fxmlFileNameWithFolder);
        return requireNonNull(fxmlFileUrl);
    }
    /**
     * Loads the object hierarchy from a FXML document.
     * @param location Location of the FXML document.
     * @param root Specifies the root of the object hierarchy.
     */
    private void loadFxmlFile(URL location, AnchorPane root) {
        requireNonNull(location);
        fxmlLoader.setLocation(location);
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(root);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }
}

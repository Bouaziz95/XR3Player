/**
 * 
 */
package application.settings.window;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import tools.InfoTool;
import tools.JavaFXTools;

/**
 * @author GOXR3PLUS
 *
 */
public class GeneralSettingsController extends BorderPane {

    //-----------------------------------------------------

    @FXML
    private ToggleGroup sideBarSideGroup;

    // -------------------------------------------------------------

    /** The logger. */
    private final Logger logger = Logger.getLogger(getClass().getName());

    /**
     * Constructor.
     */
    public GeneralSettingsController() {

	// ------------------------------------FXMLLOADER ----------------------------------------
	FXMLLoader loader = new FXMLLoader(getClass().getResource(InfoTool.FXMLS + "GeneralSettingsController.fxml"));
	loader.setController(this);
	loader.setRoot(this);

	try {
	    loader.load();
	} catch (IOException ex) {
	    logger.log(Level.SEVERE, "", ex);
	}

    }

    /**
     * Called as soon as .fxml is initialized
     */
    @FXML
    private void initialize() {

	//sideBarSideGroup
	sideBarSideGroup.selectedToggleProperty().addListener(listener -> {

	    //Update the properties file
	    Main.dbManager.getPropertiesDb().updateProperty("General-SideBarSide",
		    Integer.toString(JavaFXTools.getIndexOfSelectedToggle(sideBarSideGroup)));

	    //Fix the side bar position
	    Main.sideBar.changeSide(
		    JavaFXTools.getIndexOfSelectedToggle(sideBarSideGroup) == 0 ? NodeOrientation.LEFT_TO_RIGHT : NodeOrientation.RIGHT_TO_LEFT);
	});

    }

    /**
     * @return the sideBarSideGroup
     */
    public ToggleGroup getSideBarSideGroup() {
	return sideBarSideGroup;
    }

}

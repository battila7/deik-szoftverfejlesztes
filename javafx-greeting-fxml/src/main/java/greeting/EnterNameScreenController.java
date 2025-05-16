package greeting;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.tinylog.Logger;

public class EnterNameScreenController {
    @FXML
    private Label greetingLabel;

    public EnterNameScreenController() {
        Logger.info("Hello from controller");
        Logger.info("{}", greetingLabel);
    }

    @FXML
    private void initialize() {
        Logger.info("initialized");
        Logger.info("{}", greetingLabel);
        greetingLabel.setText("Szia!");
    }
}

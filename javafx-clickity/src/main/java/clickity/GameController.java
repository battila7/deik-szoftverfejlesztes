package clickity;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class GameController {
    @FXML
    private Label greeting;

    public void setGreeting(String greeting) {
        this.greeting.setText(greeting);
    }
}

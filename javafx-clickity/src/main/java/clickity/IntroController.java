package clickity;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class IntroController {
    @FXML
    private Button goToGameButton;

    @FXML
    public void initialize() {
        goToGameButton.setOnAction(event -> {
            GameController gameController = ScreenSwitcher.INSTANCE.getControllerOf(
                    ScreenSwitcher.Screen.GAME
            );

            gameController.setGreeting("Búcsú a félévtől");

            ScreenSwitcher.INSTANCE.switchToScreen(ScreenSwitcher.Screen.GAME);
        });
    }
}

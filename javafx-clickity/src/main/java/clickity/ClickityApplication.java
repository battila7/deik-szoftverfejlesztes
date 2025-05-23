package clickity;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ClickityApplication extends Application {
    @Override
    public void start(Stage stage) {
        var initialEmptyRoot = new VBox();
        var scene = new Scene(initialEmptyRoot);

        ScreenSwitcher.INSTANCE.setScene(scene);
        ScreenSwitcher.INSTANCE.switchToScreen(ScreenSwitcher.Screen.INTRO);

        stage.setScene(scene);
        stage.show();
    }
}

package greeting;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GreetingApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(
            getClass().getResource("/fxml/enter-name-screen.fxml")
        );

        var scene = new Scene(root);
        scene.getStylesheets().add(
            "/css/styles.css"
        );

        stage.setScene(scene);
        stage.setTitle("Greeting");
        stage.show();
    }
}

package greeting;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GreetingApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        var root = new VBox();

        var scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("Greeting");
        stage.show();
    }
}

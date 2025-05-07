package greeting;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GreetingApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        var nameField = new TextField();
        var greetingLabel = new Label("Szia!");

        var greetingExpression = Bindings.format(
                "Szia, %s",
                nameField.textProperty()
        );

        var changeLabelButton = new Button(
            "Greet me!"
        );

        changeLabelButton.setOnAction(
                event -> {
                    greetingLabel.setText(
                            greetingExpression.getValue()
                    );
                }
        );

        var scene = new Scene(
                new VBox(
                    new HBox(
                            10,
                            new Label("Ide írd a neved"),
                            nameField
                    ),
                    greetingLabel,
                    changeLabelButton
                )
        );

        stage.setScene(scene);
        stage.setTitle("Greeting");
        stage.show();
    }
}

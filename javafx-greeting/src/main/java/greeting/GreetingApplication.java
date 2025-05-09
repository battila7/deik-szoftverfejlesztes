package greeting;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleIntegerProperty;
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
        var greetingLabel = new Label();

        // Hozzáadunk egy EventListenert, melyet a keretrendszer
        // minden egyes KeyTyped (azaz, billentyű-leütés) esemény
        // alkalmával meghív.
        //
        // Az EventListener egy funkcionális interfész, azaz meg tudjuk
        // valósítani egy lambdával.
        //
        // Ebben a lambdában tudjuk megvalósítani a logikánkat: hogy
        // a greetingLabel felirata megfelelő legyen.
        nameField.setOnKeyTyped(event -> {
            greetingLabel.setText(
                    "Szia, %s!".formatted(nameField.getText())
            );
        });

        var scene = new Scene(
                new VBox(
                    new HBox(
                            10,
                            new Label("Ide írd a neved"),
                            nameField
                    ),
                    greetingLabel
                )
        );

        stage.setScene(scene);
        stage.setTitle("Greeting");
        stage.show();
    }
}

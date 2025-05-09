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

        // A .map() helyett használhatjuk a Bindings utility class
        // format() metódusát is.
        //
        // A Bindings osztály csupa olyan metódust tartalmaz, melyek
        // megkönnyítik, magasabb absztrakciós szintre emelik az ObservableValue
        // típussal (és társaival) végzett munkát.
        //
        // Például, ahelyett, hogy .map()-pel formázunk Stringet, helyette
        // használhatjuk a sokkal direktebb Bindings.format() metódust.
        // Ennek érdekessége, hogy mind ObservableValue példányokat, mind
        // "egyszerű", nem-reaktív példányokat átadhatunk neki, mindet helyesen
        // fogja kezelni: azaz, ha egy ObservableValue értéke megváltozik, akkor
        // a Bindings.format által visszaadott valami is megváltozik.
        greetingLabel.textProperty().bind(
                Bindings.format(
                        "Szia, %s!",
                        nameField.textProperty()
                )
        );

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

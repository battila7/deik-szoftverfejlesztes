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

        // Bónusz!
        //
        // Az előző megoldásainkban nagyon idétlen volt, hogy amikor
        // valaki nem írt be semmit, akkor a "Szia, !" szöveg jelent
        // meg a címkén.
        //
        // Ezt kiküszöbölendő, készítsünk most egy olyan ObservableValue-t,
        // mely
        // - A "Szia!" szöveget tartalmazza, ha a szövegdoboz üres.
        // - A köszönést tartalmazza, ha valamit írtak a szövegdobozba.
        //
        // Ezt a viselkedést ismét a Bindings segédosztállyal tudjuk a legkönnyebben
        // elérni. Ez egy ObservableBooleanValue feltételt vár. Utána pedig van
        // egy then() és egy otherwise() ága. Amikor a feltétel értéke megváltozik,
        // mindig a megfelelő ág kerül majd kiértékelésre.
        greetingLabel.textProperty().bind(
                Bindings.when(
                        nameField.textProperty().isNotEmpty()
                ).then(
                        Bindings.format(
                                "Szia, %s!",
                                nameField.textProperty()
                        )
                ).otherwise(
                        "Szia!"
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

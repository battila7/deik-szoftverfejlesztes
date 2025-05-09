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

        // A TextField típusnak van egy textProperty mezője, mely
        // egy olyan Property, ami a szöveges mezőbe beírt szöveget
        // csomagolja be.
        //
        // Miért van
        //   StringProperty textProperty;
        // mezője a TextField típusnak, ahelyett, hogy
        //   String text;
        // lenne?
        //
        // A Property-típusú (amilyen a StringProperty is) mezőknek
        // az a nagy előnye az "egyszerű" mezőkkel szemben, hogy
        // fel lehet iratkozni arra, ha megváltoznak. Sőt, a különböző Propertyket
        // bindingok (kötések) útján össze is lehet kötni: amikor az egyik
        // megváltozik, akkor a másik is.
        //
        // Az alábbi kódrészlet sokkal magasabb absztrakciós szinten van, mint az
        // előző, EventHandler-alapú megoldás. Ahelyett, hogy a leütésekre reagálnánk
        // (melyek lehetnek a szövet nem befolyásoló leütések is, mint egy SHIFT),
        // közvetlenül arra reagálunk, hogy a TextField tartalma megváltozott.
        //
        // A subscribe metódus egy Consumert vár (ezt a funkcionális interfészt már ismerjük),
        // mely a mezőbe írt új szöveget kapja. Ha a megelőző értékre is kíváncsiak vagyunk,
        // akkor használjuk a subscribe BiConsumert váró túlterhelését.
        nameField.textProperty().subscribe(enteredName -> {
            greetingLabel.setText(
                    "Szia, %s!".formatted(enteredName)
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

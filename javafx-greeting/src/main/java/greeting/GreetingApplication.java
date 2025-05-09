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

        // Vegyük észre, hogy az előző kódrészlet úgy indult, hogy
        //   nameField
        // Ezzel szemben itt már a greetingLabel-lel dolgozunk.
        //
        // Míg a korábbi kód azt fejezte ki, hogy "amikor a nameField
        // textProperty által csomagolt érték megváltozik, akkor ez meg
        // az történjen", addig ez az új változat valami olyasmit mond,
        // hogy "a greetingLabel textProperty által csomagolt értéket
        // kössük a nameField textProperty értékéhez".
        //
        // Azaz, míg az előző megoldásaink manuálisan, lényegében imperatívan
        // szinkronizálták a címke feliratát a szöveges mező tartalmával,
        // addig most lényegében deklaratívan csak hozzákötjük a címke
        // feliratát a szöveges mező tartalmához.
        //
        // A Property-kötések ereje pont ebben a deklaratív, valamint reaktív
        // megközelítésben rejlik: amint a nameField.textProperty megváltozik,
        // minden, olyan property, melyet kötöttünk hozzá, szintén meg fog változni.
        // Nekünk semmilyen teendőnk nincs, egyszerűen csak létre kellett hoznunk a
        // kötést, onnantól az már automatikusan teszi a dolgát.
        //
        // Az alábbi kódrészlet egy egyirányú (unidirection) kötést hoz létre:
        // a nameField.textProperty megváltozása maga után vonja a greetingLabel.textProperty
        // megváltozását, de fordítva NEM.
        greetingLabel.textProperty().bind(
                nameField.textProperty()
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

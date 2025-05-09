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

        // A Propertyk másik legmenőbb tulajdonsága (azon túl, hogy
        // reaktívak), hogy deklaratívan származtathatók belőlük értékek!
        //
        // A greetingLabel címkénkre nem csak egy nevet, hanem egy teljes
        // köszönést szeretnénk írni. Azaz a korábbi megoldás:
        //   greetingLabel.textProperty.bind(nameField.textProperty())
        // nem elégséges.
        //
        // Mi a névből szeretnénk egy köszönést származtatni, mely, amikor
        // a név megváltozik, szintén megváltozik.
        //
        // Erre kínál lehetőséget a map(Function mapper) metódus, mely egy olyan
        // ObservableValue-t fog visszaadni, mely a mapper kimenetét csomagolja.
        // Na, de várjunk csak, mi az az ObservableValue?
        //
        // Az ObservableValue egy doboz, mely képes szólni, ha megváltozik a tartalma.
        // Eddig nem mondtuk ki, de valójában minden Property is ObservableValue egyben.
        // A Property csupán hozzáad még sok-sok funkcionalitást, hiszen egy ObservableValue
        // pontosan csak ennyi: egy doboz, ami szól, ha változik a tartalma.
        //
        // Erre az absztrakcióra azonban rengeteg dolgot lehet építeni, például
        // valami ilyesmit:
        //   ha megváltozik a doboz tartalma, akkor csináld vele ezt és az eredményt
        //   tedd bele egy másik, szólogatós dobozba
        // Pontosan ezt teszi az x.map(mapper):
        //   visszaad egy olyan dobozt, melynek tartalma az x tartalmával együtt változik,
        //   alkalmazva x tartalmára a mapper Functiont.
        //
        // Végül, amit a kódunk csinál, irtó egyszerű:
        //   - A nameFieldbe írt névből a map() segítségével létrehozunk egy
        //     új ObservableValue-t,mely a köszönést tartalmazza.
        //   - A greetingLabel címke feliratát kötjük ehhez az ObservableValue-hoz.
        //
        // Amikor a nameField.textProperty() értéke megváltozik, abban a pillanatban
        // megváltozik a map() által előállított ObservableValue értéke is, aminek
        // következtében pedig frissülni fog a címkénk.
        greetingLabel.textProperty().bind(
                nameField.textProperty().map(
                        enteredName -> "Szia, %s!".formatted(enteredName)
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

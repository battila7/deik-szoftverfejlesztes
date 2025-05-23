package clickity;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.EnumMap;
import java.util.Map;

import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;


/**
 * Allow switching between so-called applications screens
 * by replacing the root of a Scene.
 * <p>
 * The individual screens (see {@link Screen}) are loaded
 * when the only instance of this enum is constructed. Then,
 * the appropriate controllers and FXML component trees are reused.
 * If any of the FXMLs fail to load, a {@link ScreenFailedToLoadException}
 * is thrown.
 * <p>
 * Implements Joshua Bloch's enum singleton pattern
 * from the book Effective Java.
 */
public enum ScreenSwitcher {
    INSTANCE;

    /**
     * The screens of the application we can switch
     * between.
     * <p>
     * Each enum constant stores the path of the screen's
     * FXML description that we can load the screen's UI from.
     * <p>
     * Replace the constants with the actual screen of your application.
     */
    public enum Screen {
        INTRO("/intro.fxml"),
        GAME("/game.fxml");

        private final String fxml;

        Screen(String fxml) {
            this.fxml = fxml;
        }
    }

    /**
     * Exception thrown if FXML loading fails when loading a screen
     * at startup.
     */
    public static class ScreenFailedToLoadException extends RuntimeException {
        /**
         * Constructs a new instance.
         * @param screen the screen that failed to load
         * @param cause the causing exception behind the failure
         */
        public ScreenFailedToLoadException(Screen screen, Throwable cause) {
            super("Failed to load screen: " + screen, cause);
        }
    }

    /**
     * Helper type introduced to store a loaded FXML's
     * controller instance and the UI tree root.
     * @param controller instance of the FXML controller class
     * @param root the UI tree root
     */
    private record LoadedScreen(
        Object controller,
        Parent root
    ) {}

    private final Map<Screen, LoadedScreen> loadedScreens;
    private Scene scene;

    ScreenSwitcher() {
        this.loadedScreens = new EnumMap<>(Screen.class);
        // Loops over all the screens and reads the appropriate
        // FXML files.
        for (Screen screen : Screen.values()) {
            // Catch any checked/unchecked exceptions and
            // wrap them in a ScreenFailedToLoadException.
            try {
                var loader = new FXMLLoader(
                        this.getClass().getResource(
                                screen.fxml
                        )
                );

                Parent root = loader.load();
                Object controller = loader.getController();

                this.loadedScreens.put(
                        screen,
                        new LoadedScreen(
                                controller,
                                root
                        )
                );
            } catch (Exception e) {
                throw new ScreenFailedToLoadException(screen, e);
            }
        }
    }

    /**
     * Sets the underlying scene of the switcher. Must be
     * called in {@link javafx.application.Application#start(Stage)}
     * @param scene The underlying scene that will be used for switching.
     */
    public void setScene(Scene scene) {
        this.scene = requireNonNull(scene);
    }

    /**
     * Retrieves the controller instance of the selected screen.
     * <p>
     * Useful to manipulate a screen's controller before switching
     * to the screen.
     * <p>
     * Each invocation for the same screen will return the same
     * controller instance, as screen FXMLs are loaded once
     * at startup.
     * <p>
     * It's the caller's responsibility to ensure that the
     * expected return value of this method (denoted by T)
     * is actually the class of the screen's controller.
     * As we cannot enforce this at compile time, we
     * used {@code SuppressWarnings("unchecked")}.
     * @param screen the screen
     * @return the controller instance
     * @param <T> the type of the controller class
     */
    @SuppressWarnings("unchecked")
    public <T> T getControllerOf(Screen screen) {
        return (T) loadedScreens.get(requireNonNull(screen)).controller;
    }

    /**
     * Switches to the specified screen by replacing
     * the previously stored {@link Scene}'s root with the
     * screen's root.
     * @param screen the screen to switch to
     */
    public void switchToScreen(Screen screen) {
        var loadedScreen = loadedScreens.get(
                requireNonNull(screen)
        );

        if (isNull(scene)) {
            throw new IllegalStateException(
                    "Scene is null! You must call setScene before switching screens."
            );
        }

        scene.setRoot(loadedScreen.root);
    }
}

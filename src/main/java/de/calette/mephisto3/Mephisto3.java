package de.calette.mephisto3;

import callete.api.Callete;
import de.calette.mephisto3.resources.ResourceLoader;
import de.calette.mephisto3.ui.Center;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

/**
 * In the beginning, there was main...
 */
public class Mephisto3 extends Application {
  public static Image DEFAULT_BACKGROUND = new Image(ResourceLoader.getResource("radio_background.png"), 700, 395, false, true);

  public static final int WIDTH = 700;
  public static final int HEIGHT= 395;
  private boolean debug = true;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(final Stage primaryStage) {
    Callete.getGPIOService().setSimulationMode(debug);

    //force rendering of small fonts
    System.setProperty("prism.lcdtext", "false");

    //create root component with background
    StackPane rootStack = new StackPane();
    rootStack.getChildren().add(new Center());

    Scene scene = new Scene(rootStack, (double) WIDTH, (double) HEIGHT);
    scene.getStylesheets().add(ResourceLoader.getResource("theme.css"));
    primaryStage.setScene(scene);
    primaryStage.getScene().setRoot(rootStack);

    primaryStage.addEventFilter(KeyEvent.KEY_PRESSED, new Mephisto3KeyEventFilter());
    addDisposeListener(primaryStage);

    //apply debugging options on windows
    if (debug) {
//      CSSDebugger.dump(root);
    }
    else {
      primaryStage.initStyle(StageStyle.UNDECORATED);
    }

    //finally show the stage
    primaryStage.show();
  }

  //--------------------------- Helper --------------------------------------------

  private static void addDisposeListener(Stage primaryStage) {
    //ensures that the process is terminated on window dispose
    primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
      @Override
      public void handle(WindowEvent windowEvent) {
        Platform.exit();
        System.exit(0);
      }
    });
  }
}

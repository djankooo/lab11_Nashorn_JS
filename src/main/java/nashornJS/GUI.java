package nashornJS;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class GUI extends Application {

    private GridPane createPane() {
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(20, 20, 20, 20));
        pane.setHgap(10);
        pane.setVgap(10);
        return pane;
    }

    private void setUIControls(GridPane pane, Stage primaryStage) throws Exception {

    }

    @Override
    public void start(final Stage primaryStage) throws Exception {

        System.out.println( "Hello from GUI" );

        GridPane gridPane = createPane();

        setUIControls(gridPane, primaryStage);
        primaryStage.setScene(new Scene(gridPane, 1000, 500));
        primaryStage.show();
    }

    public static void main(String[] args) {

        launch(args);
    }

}

package nashornJS;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

public class GUI extends Application {

    private ImageView selectedImage = new ImageView();
    private FileChooser jsFileChooser = new FileChooser();
    private HBox jsHBox = new HBox();
    private TextField jsFilePathTextField = new TextField();
    private Button jsChooseFileButton = new Button("Browse");
    private Button jsSubmitFileButton = new Button("Set algoritm");
    private VBox vBox = new VBox();
    private BufferedImage currentImg;
    ComboBox<String> verGapComboBox = new ComboBox();







    private GridPane createPane() {
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(20, 20, 20, 20));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setGridLinesVisible(true);
        return pane;
    }

    private void setUIControls(VBox pane, Stage primaryStage) throws Exception {

        Image image = new Image(new FileInputStream("C:\\Users\\djankooo\\Desktop\\PWr\\PWJJ\\lab11_Nashorn\\nashorn.jpg"));
        selectedImage.setImage(image);

        verGapComboBox.getItems().addAll("grayscale", "sepia");

        jsFileChooser.setTitle("Open Resource File");

        jsFilePathTextField.setPrefWidth(image.getWidth()*0.75);
        jsChooseFileButton.setPrefWidth(image.getWidth()*0.25);
        jsSubmitFileButton.setPrefWidth(image.getWidth());
        verGapComboBox.setPrefWidth(image.getWidth());

        //jsHBox.getChildren().addAll(jsFilePathTextField, jsChooseFileButton);

        jsChooseFileButton.setOnAction(event -> {
            File file = jsFileChooser.showOpenDialog(primaryStage);
            if (file != null) {
                try {
                    jsFilePathTextField.setText(file.getCanonicalPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        jsSubmitFileButton.setOnAction(e ->
        {
            ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
            ScriptEngine engine = scriptEngineManager.getEngineByName("nashorn");
            try {



                engine.eval(new FileReader("src\\main\\java\\nashornJS\\ImageProcessorJS.js"));
                Invocable invocable = (Invocable) engine;

                BufferedImage buffImg = SwingFXUtils.fromFXImage(image, null);

                buffImg = (BufferedImage) invocable.invokeFunction(verGapComboBox.getValue(), buffImg, buffImg.getWidth(), buffImg.getHeight());

                Image image2 = SwingFXUtils.toFXImage(buffImg, null);

                selectedImage.setImage(image2);

            } catch (ScriptException | IOException e1) {
                e1.printStackTrace();
            } catch (NoSuchMethodException e1) {
                e1.printStackTrace();
            }


        });


        pane.getChildren().addAll(selectedImage,jsHBox, verGapComboBox, jsSubmitFileButton);

    }

    @Override
    public void start(final Stage primaryStage) throws Exception {

        System.out.println( "Hello from GUI" );
        GridPane gridPane = createPane();
        gridPane.add(vBox,0,0);
        setUIControls(vBox, primaryStage);
        primaryStage.setScene(new Scene(gridPane, 1000, 500));
        primaryStage.show();
    }

    public static void main(String[] args) {

        launch(args);
    }

}
